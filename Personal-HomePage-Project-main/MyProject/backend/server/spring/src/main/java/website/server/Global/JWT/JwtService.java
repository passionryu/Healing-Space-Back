package website.server.Global.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Date;

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
     * @param email 토큰의 주체
     * @return 엑세스 토큰
     */
    public String generateAccessToken(String email,String nickname,Long userNumber) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userNumber",userNumber)
                .claim("nickname", nickname)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * 리프레시 토큰 생성 메서드
     * @param email 토큰의 주체
     * @return 리프레시 토큰
     */
    public String generateRefreshToken(String email,String nickname,Long userNumber) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userNumber",userNumber)
                .claim("nickname", nickname)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * 엑세스 토큰 추출 메서드
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
     * jwt 토큰에서 유저 네임 추출
     * @param token JWT 토큰
     * @return 사용자의 이름
     */
    public String extractUsername(String token) {
        return extractClaims(token).get("nickname").toString();
    }
    public Claims extractClaims(String token) {
        return Jwts.parser()// JWT 파서 객체 생성
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    /* BLACK LIST CODE */
    /* BLACK LIST CODE */
    /* BLACK LIST CODE */

    // 토큰 검증 (블랙리스트 체크 포함)
    public boolean validateToken(String token) {
        if (isTokenBlacklisted(token)) {
            return false;  // 블랙리스트에 있으면 유효하지 않음
        }
        // 여기서 JWT 토큰의 유효성 검사를 추가적으로 할 수 있습니다 (예: Expiration, Signature 등)
        return true;
    }

    // 토큰이 블랙리스트에 있는지 확인
    public boolean isTokenBlacklisted(String token) {
        return redisTemplate.hasKey("blacklist:" + token);
    }

}
