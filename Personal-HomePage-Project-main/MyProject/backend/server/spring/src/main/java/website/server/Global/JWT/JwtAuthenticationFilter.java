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
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MemberMapper memberMapper;

    /* 권한이 필요 없는 API Path 선언 */
    private static final List<String> PUBLIC_APIS = List.of(
            "/auth/login/email",
            "/auth/login/id",
            "/member/register",
            "/member/findID/option1",
            "/member/findID/option2"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        String nickname = null;
        String jwt = null;

        // 권한이 필요 없는 API 경로는 필터를 건너뜀
        if (isPublicApi(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            nickname = jwtService.extractUsername(jwt);
        }

        Member member = (nickname != null) ? memberMapper.findMemberByNickname(nickname) : null;
        if (nickname == null || member == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("User not found or unauthorized");
            response.getWriter().flush();
            return;
        }

        if (jwt != null && jwtService.validateToken(jwt)) {
            Collection<? extends GrantedAuthority> authorities =
                    Collections.singletonList(new SimpleGrantedAuthority(member.getRole()));
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(member, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else if (jwt != null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid or expired JWT token");
            response.getWriter().flush();
            return;
        }

        filterChain.doFilter(request, response);

    }

    /**
     * 권한 불필요 API들은 넘기는 메서드
     * @param request 사용자 요청
     * @return ture/false
     */
    private boolean isPublicApi(HttpServletRequest request) {
        String path = request.getRequestURI();
        return PUBLIC_APIS.stream().anyMatch(path::startsWith);
    }
}
