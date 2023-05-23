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
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final MemberService memberService;

    @GetMapping("home")
    public String main(HttpSession session, Model model){
        log.info("==================== main controller =====================");
//        log.info(session.getAttribute("member") + "");
        log.info("=========================================");
//        model.addAttribute("id", session.getId());
        return  "main/main";
    }

    @GetMapping("introduce")
    public String introduce(){
        return "introduce/introduce";
    }

    @GetMapping("helpcenter")
    public String helpcenter(){
        return "helpcenter/inquiry-home";
    }
}
