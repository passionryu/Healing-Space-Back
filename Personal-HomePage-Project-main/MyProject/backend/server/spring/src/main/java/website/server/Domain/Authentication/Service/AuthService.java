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

        // Find member
        Member member = memberMapper.findMemberByEmail(email);

        // validation
        if (member == null) {return new JwtTokenDto("no member","x");}
        if (!passwordEncoder.matches(password, member.getPassword())) {return new JwtTokenDto("PW not match","x");}

        // Generate JWT token
        String AccessToken = jwtService.generateAccessToken(email,member.getNickName(),member.getUserNumber());
        String RefreshToken =jwtService.generateRefreshToken(email, member.getNickName(), member.getUserNumber());

        // Input AccessToken to Redis
        redisTemplate.opsForValue().set("auth:" + member.getUserNumber(), AccessToken, Duration.ofHours(1));

        // return JWT
        return new JwtTokenDto(AccessToken,RefreshToken);
    }

    /**
     * 아이디 로그인 메서드
     * @param nickName 아이디
     * @param password 비밀번호
     * @return JWT 토큰
     */
    public JwtTokenDto id_Login(String nickName, String password) {

        // Find member
        Member member = memberMapper.findMemberByNickname(nickName);
        log.info("find member {}" ,member);

        // validation
        if (member == null) {return new JwtTokenDto("no member","x");}
        if (!passwordEncoder.matches(password, member.getPassword())) {return new JwtTokenDto("PW not match","x");}

        // Generate JWt token
        String AccessToken = jwtService.generateAccessToken(member.getEmail(), member.getNickName(),member.getUserNumber());
        String RefreshToken =jwtService.generateRefreshToken(member.getEmail(), member.getNickName(), member.getUserNumber());
        log.info("at {}",AccessToken);
        log.info("rt {}",RefreshToken);

        // Input AccessToken to Redis
        redisTemplate.opsForValue().set("auth:" + member.getUserNumber(), AccessToken, Duration.ofHours(1));

        // return JWT
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

}
