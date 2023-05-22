package com.app.dodamdodam.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomLogginFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = request.getParameter("memberId");
        String password = request.getParameter("memberPassword");

//        원하는 로그 출력
        log.info("Username: " + username);
        log.info("Password: " + password);

//        System.out.println("Username: " + username);
//        System.out.println("Password: " + password);

        filterChain.doFilter(request, response);




    }
}
