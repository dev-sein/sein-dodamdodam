package com.app.dodamdodam.controller;

import com.app.dodamdodam.provider.UserDetail;
import com.app.dodamdodam.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
//@RequestMapping("")
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final MemberService memberService;

    @GetMapping("/main")
    public String gotoMain(@AuthenticationPrincipal UserDetail userDetail, HttpSession session, Model model){
//        userDetail.getMemberEmail();
        log.info("=========================================");
//        log.info(userDetail.getId() + "");
        log.info(session.getId() + "");
        log.info("=========================================");
        model.addAttribute("id", session.getId());
        return "/main/main";
    }
}
