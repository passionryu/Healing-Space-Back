package website.server.Global.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
                        requestMatchers("/register").permitAll().
                        requestMatchers("/member/login").permitAll().
                        requestMatchers("/member/logout").permitAll().
                        requestMatchers("/member/register").permitAll().

                        //(사용자 용)한 회원 정보 조회
                                requestMatchers(HttpMethod.GET,"/api/member").permitAll().

                        /*Swagger-무권한 접근 허용*/
                                requestMatchers("/swagger-ui/**").permitAll().
                        requestMatchers("/v3/api-docs/**").permitAll().
                        anyRequest().authenticated());


        return http.build();
    }

}
