package com.app.dodamdodam.config;

import com.app.dodamdodam.filter.CustomLogginFilter;
import com.app.dodamdodam.type.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    private static final String MAIN_PATH = "/main/**";
    private static final String ADMIN_PATH = "/admin/**";
//    private static final String BOARD_PATH = "/board/**";
    private static final String IGNORE_FAVICON = "/favicon.ico";
    private static final String LOGIN_PAGE = "/member/login";
    private static final String LOGIN_PROCESSING_URL = "/member/login";
    private static final String LOGOUT_URL = "/member/logout";
    private static final String LOGOUT_SUCCESS_URL = "/member/login";
    private static final String REMEMBER_ME_TOKEN_KEY = "have a nice day";
    private static final int REMEMBER_ME_TOKEN_EXPIRED = 86400 * 14;

    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final UserDetailsService userDetailsService;

//    비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
//        WebSecurity에서 관여하지 않을 경로
        return web -> web.ignoring()
                .mvcMatchers(IGNORE_FAVICON) //favicon은 필터에서 제외
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()); //static 경로도 필터에서 제외
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(MAIN_PATH).authenticated()
                .antMatchers(ADMIN_PATH).hasRole(Role.ADMIN.name())
//                .antMatchers(BOARD_PATH).hasRole(Role.MEMBER.name())
                .and()
                .csrf().disable()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler) //인가 실패
                .authenticationEntryPoint(authenticationEntryPoint) //인증 실패

                .and()
                .formLogin() // 일반 로그인
                .loginPage(LOGIN_PAGE) // 로그인하는 페이지 경로
                .usernameParameter("memberId") // 로그인 버튼 클릭 시 전달될 username 파라미터 명 수정
                .passwordParameter("memberPassword") // 로그인 버튼 클릭 시 전달될 password 파라미터 명 수정
                .loginProcessingUrl(LOGIN_PROCESSING_URL) // form에서 submit을 통해 진행될 경로
                .successHandler(authenticationSuccessHandler) // 성공 시
                .failureHandler(authenticationFailureHandler) // 실패 시
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL)) // 로그아웃 시 이동할 경로
                .logoutSuccessUrl(LOGOUT_SUCCESS_URL) // 로그아웃 성공 시
                .invalidateHttpSession(Boolean.TRUE) // 세션 초기화
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me") // 자동 로그인 부분의 checkbox 파라미터
                .key(REMEMBER_ME_TOKEN_KEY) // 자동로그인 임의로 암호화
                .tokenValiditySeconds(REMEMBER_ME_TOKEN_EXPIRED)
                .userDetailsService(userDetailsService)
                .authenticationSuccessHandler(authenticationSuccessHandler); // 인증 성공(로그인 성공)

        // 커스텀 필터 추가
        http.addFilterAfter(new CustomLogginFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}













