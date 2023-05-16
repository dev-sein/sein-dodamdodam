package com.app.dodamdodam.handler;

import com.app.dodamdodam.provider.UserDetail;
import com.app.dodamdodam.type.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    private static final String REDIRECT_URL_FOR_MEMBER = "/main";
    private static final String REDIRECT_URL_FOR_ADMIN = "/admin/member/list";
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("SuccessHandler 진입");
        if(((UserDetail)authentication.getPrincipal()).getMemberRole().equals(Role.ADMIN)){
            log.info("ADMIN_SUCCESS");
            response.sendRedirect(REDIRECT_URL_FOR_ADMIN);
        }else if(((UserDetail)authentication.getPrincipal()).getMemberRole().equals(Role.MEMBER)){
            log.info("MEMBER_SUCCESS");
            log.info(authentication.getPrincipal().toString());
            response.sendRedirect(REDIRECT_URL_FOR_MEMBER);
        }
    }
}
