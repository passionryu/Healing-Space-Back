package website.server.Global.JWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import website.server.Domain.Member.Entity.Member;
import website.server.Domain.Member.Mapper.MemberMapper;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MemberMapper memberMapper;

    // TODO : 오류 일어날 가능성 높은 영역

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        String nickname = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            nickname = jwtService.extractUsername(jwt);
        }

        Member member = memberMapper.findMemberByNickname(nickname);
        if (nickname == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Unauthorized 상태 코드 반환
            return;
        }

        if (jwt != null && !jwt.isEmpty()) {
            if (jwtService.validateToken(jwt)) {
                // 유효한 토큰 처리 (예: 사용자 정보 설정)
                Collection<? extends GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(member.getRole()));

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(member, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                 // 블랙리스트에 있으면 Unauthorized
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid or expired JWT token");
                response.getWriter().flush();
                return;
            }
        }

        filterChain.doFilter(request, response);

    }
}
