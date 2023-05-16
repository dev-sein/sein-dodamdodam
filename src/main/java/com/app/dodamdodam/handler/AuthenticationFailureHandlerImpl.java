package com.app.dodamdodam.handler;

import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.provider.UserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    private static final String REDIRECT_URL = "/member/login?check=false"; // 실패하면 다시 로그인 페이지로
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("exception message: {}", exception.getMessage());
        response.sendRedirect(REDIRECT_URL);
    }
}
