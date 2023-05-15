package com.app.dodamdodam.config;

import com.app.dodamdodam.service.member.MemberServiceImpl;
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
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    private static final String MAIN_PATH = "/main/**";
    private static final String ADMIN_PATH = "/admin/**";
    private static final String BOARD_PATH = "/board/**";
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

//    private final DefaultOAuth2UserService userService;x
    private final MemberServiceImpl memberService;

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
                .antMatchers(BOARD_PATH).hasRole(Role.MEMBER.name())
                .and()
                .csrf().disable()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler) //인가 실패
                .authenticationEntryPoint(authenticationEntryPoint); //인증 실패

        log.info(userDetailsService.toString());

        http
                .formLogin()
                .loginPage(LOGIN_PAGE)
                .usernameParameter("memberId")
                .passwordParameter("memberPassword")
                .loginProcessingUrl(LOGIN_PROCESSING_URL)
                .successHandler(authenticationSuccessHandler) // 성공
                .failureHandler(authenticationFailureHandler) // 실패
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL))
                .logoutSuccessUrl(LOGOUT_SUCCESS_URL)
                .invalidateHttpSession(Boolean.TRUE)
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me")
                .key(REMEMBER_ME_TOKEN_KEY)
                .tokenValiditySeconds(REMEMBER_ME_TOKEN_EXPIRED)
                .userDetailsService(userDetailsService)
                .authenticationSuccessHandler(authenticationSuccessHandler);

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/profile").permitAll()
                .antMatchers("/api/**").hasRole(Role.MEMBER.name())
                .anyRequest().authenticated().and()
                .logout().logoutSuccessUrl("/").and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(memberService);

        return http.build();
    }
}













