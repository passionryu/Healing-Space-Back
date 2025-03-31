package website.server.Global.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.accessexpiration}")
    private long ACCESS_TOKEN_EXPIRATION_TIME;

    @Value("${jwt.refreshexpiration}")
    private long REFRESH_TOKEN_EXPIRATION_TIME;

    /**
     * 엑세스 토큰 생성 메서드
     *
     * @return 엑세스 토큰
     */
    public String generateAccessToken(String phone_number,String nickname,Long userNumber) {
        return Jwts.builder()
                .setSubject(phone_number)
                .claim("userNumber",userNumber)
                .claim("nickName", nickname)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * 리프레시 토큰 생성 메서드
     *
     * @return 리프레시 토큰
     */
    public String generateRefreshToken(String phone_number,String nickname,Long userNumber) {
        return Jwts.builder()
                .setSubject(phone_number)
                .claim("userNumber",userNumber)
                .claim("nickName", nickname)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * 사용자 요청으로 부터 엑세스 토큰 추출
     * @param request 사용자 요청
     * @return 엑세스 토큰 반환
     */
    public String extractAccessToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    /**
     * 사용자 요청으로 부터 사용자 고유 번호 추출
     * @param request 사용자 요청
     * @return userNumber
     */
    public Long extractUserNumberFromRequest(HttpServletRequest request){

        String AccessToken = extractAccessToken(request);

        return extractUserNumberFromToken(AccessToken);
    }

    /**
     * 사용자 요청으로부터 유저 닉네임 추출
     * @param request
     * @return
     */
    public String extractNickNameFromRequest(HttpServletRequest request){

        String AccessToken = extractAccessToken(request);

        return extractUsername(AccessToken);
    }

    /**
     * jwt 토큰에서 유저 네임 추출
     * @param token JWT 토큰
     * @return 사용자의 이름
     */
    public String extractUsername(String token) {
        return extractClaims(token).get("nickName").toString();
    }
    public Claims extractClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("Failed to extract claims from token: {}", token, e);
            throw e; // 혹은 적절히 처리
        }
    }

    public Long extractUserNumberFromToken(String token) {
        return extractClaims_userNumber(token).get("userNumber", Long.class);
    }
    public Claims extractClaims_userNumber(String token) {
        return Jwts.parser()// JWT 파서 객체 생성
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    /* BLACK LIST CODE */
    /* BLACK LIST CODE */
    /* BLACK LIST CODE */
    /* BLACK LIST CODE */
    /* BLACK LIST CODE */

    /**
     * 토큰 검증 메서드 (블랙리스트 체크 포함)
     * @param token 사용자의 엑세스 토큰
     * @return true & false
     */
    public boolean validateToken(String token) {

        try {
            // 블랙리스트 체크
            if (isTokenBlacklisted(token)) {
                return false;
            }
            // JWT 자체 유효성 검사
            Claims claims = extractClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            log.error("Token validation failed: {}", token, e);
            return false;
        }
    }

    /**
     * 토큰이 블랙리스트에 있는지 확인
     * @param token 사용자의 엑세스 토큰
     * @return true & flase
     */
    public boolean isTokenBlacklisted(String token) {
        return redisTemplate.hasKey("blacklist:" + token);
    }

}
