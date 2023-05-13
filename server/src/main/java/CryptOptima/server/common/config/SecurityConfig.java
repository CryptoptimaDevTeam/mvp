package CryptOptima.server.common.config;

import CryptOptima.server.security.filter.JwtAuthenticationFilter;
import CryptOptima.server.security.filter.JwtVerificationFilter;
import CryptOptima.server.security.handler.ManagerAuthenticationFailureHandler;
import CryptOptima.server.security.handler.ManagerAuthenticationSuccessHandler;
import CryptOptima.server.security.jwt.JwtTokenizer;
import CryptOptima.server.security.utils.ManagerAuthorityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    private final JwtTokenizer jwtTokenizer;
    private final ManagerAuthorityUtils authorityUtils; // JwtVerificationFilter에서 Authentication 객체를 생성할 때 authorities를 생성하기 위해 필요.

    public SecurityConfig(JwtTokenizer jwtTokenizer, ManagerAuthorityUtils authorityUtils) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors(Customizer.withDefaults())
                .formLogin().disable()
                .httpBasic().disable()
                .apply(new CustomFilterConfigurer())
                .and()
//                .oauth2Login(oauth2 -> oauth2
//                        .successHandler()
//                        .failureHandler())
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers(HttpMethod.POST,"/managers/login").permitAll()
                        .antMatchers(HttpMethod.POST, "/managers/**").hasRole("KING")
                        .antMatchers(HttpMethod.DELETE, "/managers/**").hasRole("KING")
                        .antMatchers(HttpMethod.PATCH, "/managers/**").hasAnyRole("KING","MASTER")
                        .antMatchers(HttpMethod.GET,"/managers").hasAnyRole("KING","MASTER","MANAGER")
                        .anyRequest().permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("POST","PATCH","GET","DELETE"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            // JwtAuthenticationFilter: UsernamePasswordAuthenticationFilter를 확장한 필터로, 로그인 인증 완료 시 JWT를 발급한다.
            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/managers/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new ManagerAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new ManagerAuthenticationFailureHandler());

            // JwtVerificationFilter: OncePerRequestFilter를 확장한 필터로, 요청 헤더에 담긴 JWT를 검증하고, 성공시 Authentication을 SecurityContext에 저장한다.
            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);

            builder
                    .addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }
}
