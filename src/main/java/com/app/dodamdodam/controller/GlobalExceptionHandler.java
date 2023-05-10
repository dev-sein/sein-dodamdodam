package com.app.dodamdodam.controller;

import com.app.dodamdodam.exception.CustomAuthenticationException;
import com.app.dodamdodam.exception.LoginFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(CustomAuthenticationException.class)
//    protected RedirectView handleCustomAuthenticationException(CustomAuthenticationException e, HttpSession session){
//        session.removeAttribute("id");
//        session.removeAttribute("memberId");
//        session.removeAttribute("role");
//        session.invalidate();
//        return new RedirectView("/member/login");
//    }
//
//    @ExceptionHandler(LoginFailedException.class)
//    protected RedirectView handleLoginFailedException(LoginFailedException e){
//        return new RedirectView("/member/login?check=false");
//    }
}
