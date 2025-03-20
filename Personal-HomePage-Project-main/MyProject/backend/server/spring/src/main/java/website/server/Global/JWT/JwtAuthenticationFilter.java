package website.server.Global.JWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MemberMapper memberMapper;

    /* 권한이 필요 없는 API Path 선언 */
    private static final List<String> PUBLIC_APIS = List.of(

            "/healingmessage/list",
            "/healingmusic/list",
            "/healingmessage/**",
            "/healingmusic/**",
            "/blog",
            "/swagger-ui/**",
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

        // 로그 미출력
        // OPTIONS 요청은 필터를 건너뜀
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            log.info("OPTIONS request - skipping filter");
            filterChain.doFilter(request, response);
            return;
        }

        // 로그 출력
        String authorizationHeader = request.getHeader("Authorization");
        log.info("authorizationHeader check 1: {}",authorizationHeader);
        String nickname = null;
        String jwt = null;

        // 이동해서 로그 출력 완료
        // 권한이 필요 없는 API 경로는 필터를 건너뜀
        if (isPublicApi(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 조건 미 충족으로 로그 미 출력
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            nickname = jwtService.extractUsername(jwt);
            log.info("authorizationHeader check 2: {}",authorizationHeader);
            log.info("jwt : {}" , jwt);
            log.info("nickName : {}",nickname);
        }

        // 로그 출력 nickName : null,member : null
        Member member = (nickname != null) ? memberMapper.findMemberByNickname(nickname) : null;
        log.info("nickName : {}",nickname);
        log.info("member : {}",member);

        // 클라이언트 측에서 출력
        if (nickname == null || member == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("User not found or unauthorized at jwtAuthenticationFilter");
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
        //로그 출력 -> path /login/id이렇게 나와서 아마 ....그런건가
        return PUBLIC_APIS.stream().anyMatch(path::startsWith);
    }
}
