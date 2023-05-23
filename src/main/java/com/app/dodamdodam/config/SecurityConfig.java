package com.app.dodamdodam.config;

import com.app.dodamdodam.filter.CustomLogginFilter;
import com.app.dodamdodam.service.member.MemberOAuthService;
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
//    관리자
    private static final String ADMIN_PATH = "/admins/**";
//    마이페이지
    private static final String MYPAGE_PATH = "/mypage/**";
//    판매 게시판
//    private static final String PURCHASE_PATH = "/purchase/**";
    private static final String PURCHASE_BOARD_LIST_PATH = "/purchase/list/**";
    private static final String PURCHASE_BOARD_DETAIL_PATH = "/purchase/detail/**";
    private static final String PURCHASE_BOARD_WRITE_PATH = "/purchase/write/**";

//    자유 게시판
//    private static final String FREE_PATH = "/free/**";
    private static final String FREE_BOARD_LIST_PATH = "/free/list/**";
    private static final String FREE_BOARD_DETAIL_PATH = "/free/detail/**";
    private static final String FREE_BOARD_WRITE_PATH = "/free/write-board";
    private static final String FREE_BOARD_UPDATE_PATH = "/free/update-board/**";

//    모집 게시판
//    private static final String RECRUITMENT_PATH = "/recruitment/**";
    private static final String RECRUITMENT_BOARD_LIST_PATH = "/recruitment/list/**";
    private static final String RECRUITMENT_BOARD_DETAIL_PATH = "/recruitment/detail/**";
    private static final String RECRUITMENT_BOARD_WRITE_PATH = "/recruitment/write-board";
    private static final String RECRUITMENT_BOARD_UPDATE_PATH = "/recruitment/update-board/**";
//    이벤트 게시판
//    private static final String EVENT_PATH = "/event/**";
    private static final String EVENT_BOARD_LIST_PATH = "/event/list/**";
    private static final String EVENT_BOARD_DETAIL_PATH = "/event/detail/**";
    private static final String EVENT_BOARD_WRITE_PATH = "/event/write";
    private static final String EVENT_BOARD_UPDATE_PATH = "/event/update/**";

//    문화공간 소개 게시판
//    private static final String CULTURE_PATH = "/culture/**";
    private static final String CULTURE_BOARD_LIST_PATH = "/culture/list/**";
    private static final String CULTURE_BOARD_DETAIL_PATH = "/culture/detail/**";
//    private static final String CULTURE_BOARD_WRITE_PATH = "/culture/write-board";
//    private static final String CULTURE_BOARD_UPDATE_PATH = "/culture/update-board/**";



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
    private final MemberOAuthService memberOAuthService;

//    비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
//        WebSecurity에서 관여하지 않을 경로
        return web -> web.ignoring()
                .mvcMatchers(IGNORE_FAVICON) //favicon은 필터에서 제외
                .antMatchers(PURCHASE_BOARD_LIST_PATH)
                .antMatchers(PURCHASE_BOARD_DETAIL_PATH)
//                .antMatchers(FREE_PATH) //임시
//                .antMatchers(RECRUITMENT_PATH) //임시
//                .antMatchers(EVENT_PATH) //임시
//                .antMatchers(CULTURE_PATH) //임시
                .antMatchers(ADMIN_PATH) //임시
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()); //static 경로도 필터에서 제외
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin() //일반 로그인
                .loginPage(LOGIN_PAGE) //로그인하는 페이지 경로
                .usernameParameter("memberId") //로그인 버튼 클릭 시 전달될 username 파라미터 명 수정
                .passwordParameter("memberPassword") //로그인 버튼 클릭 시 전달될 password 파라미터 명 수정
                .loginProcessingUrl(LOGIN_PROCESSING_URL) //form에서 submit을 통해 진행될 경로
                .successHandler(authenticationSuccessHandler) //성공 시
                .failureHandler(authenticationFailureHandler) //실패 시
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL)) //로그아웃 시 이동할 경로
                .logoutSuccessUrl(LOGOUT_SUCCESS_URL) //로그아웃 성공 시
                .invalidateHttpSession(Boolean.TRUE) //세션 초기화
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me") //자동 로그인 부분의 checkbox 파라미터명 설정
                .key(REMEMBER_ME_TOKEN_KEY)
                .tokenValiditySeconds(REMEMBER_ME_TOKEN_EXPIRED)
                .userDetailsService(userDetailsService)
                .authenticationSuccessHandler(authenticationSuccessHandler) // 인증 성공(로그인 성공)
                .and()
                .csrf().disable()

                .authorizeRequests() // 인가 설정(권한 설정)
//                .antMatchers(ADMIN_PATH).hasRole(Role.ADMIN.name())
                .antMatchers(MYPAGE_PATH).hasRole(Role.MEMBER.name())
                .antMatchers(PURCHASE_BOARD_WRITE_PATH).hasRole(Role.MEMBER.name())
                .antMatchers(RECRUITMENT_BOARD_WRITE_PATH).hasRole(Role.MEMBER.name())
                .antMatchers(FREE_BOARD_WRITE_PATH).hasRole(Role.MEMBER.name())
                .antMatchers(EVENT_BOARD_WRITE_PATH).hasRole(Role.MEMBER.name())
                .anyRequest().permitAll()

                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(memberOAuthService);
                log.info("-------------------------- security OAuth Login ------------------------------------");

        // 커스텀 필터 추가
//        http.addFilterAfter(new CustomLogginFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}













