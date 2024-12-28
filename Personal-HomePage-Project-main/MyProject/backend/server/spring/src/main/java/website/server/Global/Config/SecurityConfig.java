package website.server.Global.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // csrf 차단
                .csrf(AbstractHttpConfigurer::disable)

                // cors 설정
                //.cors(cors -> cors.configurationSource(configurationSource()))

                // 시큐리티 기본 로그인 비활성화
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)

                // 권한 url 설정
                .authorizeHttpRequests(req -> req.
                        /*무권한 접근 url*/
                        requestMatchers("/member/register").permitAll().
                        requestMatchers("/auth/login/email").permitAll().
                        requestMatchers("/auth/login/id").permitAll().
                        requestMatchers("/member/findID/option1").permitAll().
                        requestMatchers("/member/findID/option2").permitAll().
                        requestMatchers("/auth/logout").permitAll().
                        requestMatchers("/dew/diary").permitAll().

                        /*Swagger 무권한 접근 허용*/
                        requestMatchers("/swagger-ui/**").permitAll().
                        requestMatchers("/v3/api-docs/**").permitAll().
                        anyRequest().authenticated());

        return http.build();

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //권한계층 생성
    @Bean
    public RoleHierarchyImpl roleHierarchy() {
        return RoleHierarchyImpl.fromHierarchy("""
            ADMIN > USER > VISITOR
            """);
    }

    //권한 계층 등록
    @Bean
    public DefaultWebSecurityExpressionHandler customWebSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }

}
