package com.app.dodamdodam.controller;

import com.app.dodamdodam.domain.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class OAuthController {
    @GetMapping("/")
    public RedirectView oAuthLogin(HttpSession session){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        log.info("==================================================");
        log.info(memberDTO.toString());
        return new RedirectView("/main");
    }
//    @GetMapping("/")
//    public String oAuthLogin(Model model, HttpSession session){
//        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
//         model.addAttribute(memberDTO);
//
//        log.info("==================================================");
//        log.info(memberDTO.toString());
//        return "/member/join";
//    }
}
