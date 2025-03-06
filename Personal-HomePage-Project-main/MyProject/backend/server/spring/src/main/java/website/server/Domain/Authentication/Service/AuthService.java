package website.server.Domain.Authentication.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import website.server.Global.Exception.ErrorCode;
import website.server.Global.Exception.MemberException;
import website.server.Global.JWT.JwtTokenDto;
import website.server.Global.JWT.JwtService;
import website.server.Domain.Member.Entity.Member;
import website.server.Domain.Member.Mapper.MemberMapper;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Qualifier("redisConnectionFactory")
    @Autowired
    private LettuceConnectionFactory redisConnectionFactory;

    /**
     * 이메일 로그인 메서드
     * @param email 이메일
     * @param password 비밀번호
     * @return JWT 토큰
     */
    public JwtTokenDto email_Login(String email, String password) {

        /* 사용자 조회 */
        Member member = memberMapper.findMemberByEmail(email);

        /* 사용자 인증 */
        if (member == null) {
            throw new MemberException(ErrorCode.NOT_FOUND_MEMBER);
        }
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new MemberException(ErrorCode.PW_NOT_MATCH);
        }

        /* JWT 생성 */
        String AccessToken = jwtService.generateAccessToken(email,member.getNickName(),member.getUser_number());
        String RefreshToken =jwtService.generateRefreshToken(email, member.getNickName(), member.getUser_number());

        /* 로그인과 동시에 엑세스 토큰 레디스 DB에 업로드 */
        redisTemplate.opsForValue().set("auth:" + member.getUser_number(), AccessToken, Duration.ofHours(1));

        return new JwtTokenDto(AccessToken,RefreshToken);
    }

    /**
     * 아이디 로그인 메서드
     * @param nickName 아이디
     * @param password 비밀번호
     * @return JWT 토큰
     */
    public JwtTokenDto id_Login(String nickName, String password) {

        /* 사용자 조회 */
        Member member = memberMapper.findMemberByNickname(nickName);

        /* 사용자 인증 */
        if (member == null) {
            throw new MemberException(ErrorCode.NOT_FOUND_MEMBER);
        }
        if (!passwordEncoder.matches(password, member.getPassword())) {
           throw new MemberException(ErrorCode.PW_NOT_MATCH);
        }

        /* JWT 생성 */
        String AccessToken = jwtService.generateAccessToken(member.getEmail(), member.getNickName(),member.getUser_number());
        String RefreshToken =jwtService.generateRefreshToken(member.getEmail(), member.getNickName(), member.getUser_number());

        /* 로그인과 동시에 엑세스 토큰 레디스 DB에 업로드 */
        redisTemplate.opsForValue().set("auth:" + member.getUser_number(), AccessToken, Duration.ofHours(1));

        return new JwtTokenDto(AccessToken,RefreshToken);
    }

    /**
     * 로그아웃 메서드
     * @param request 사용자 요청
     */
    public void logout(HttpServletRequest request){

        // Get AccessToken & nickName
        String AccessToken = jwtService.extractAccessToken(request);
        String nickname = jwtService.extractUsername(AccessToken);

        // Extract token's expiration time
        Date expirationDate = jwtService.extractClaims(AccessToken).getExpiration();
        log.info("{}'s expirationDate : {}",nickname,expirationDate);

        // Set ttl(Remain time for AccessToken)
        long ttl = expirationDate.getTime() - System.currentTimeMillis();
        double ttlInMinutes = ttl / (1000.0 * 60); // 부동소수점 연산
        log.info("{}'s ttl : {} minutes", nickname, ttlInMinutes);

        // Upload to BlackList
        redisTemplate.opsForValue().set("blacklist:" + nickname, AccessToken, ttl, TimeUnit.MILLISECONDS);

    }

    /**
     * 회원 탈퇴 메서드
     * @param request 사용자 요청
     * @return 탈퇴 된 사용자의 고유 번호
     */
    public void delete(HttpServletRequest request){

        /* 사용자 고유 번호 조회 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* DB에서 해당 유저 삭제(회원 탈퇴) */
        memberMapper.delete(userNumber);
        log.info("{}'s member deleted",userNumber);
    }

}
