package com.app.dodamdodam.controller;

import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    private final HttpSession httpSession;

    @GetMapping("join")
    public String goToJoinForm(MemberDTO memberDTO){
        return "/member/join";
    }

    @PostMapping("join")
    public RedirectView join(MemberDTO memberDTO){
        memberService.join(memberDTO, passwordEncoder);
        return new RedirectView("/member/login");
    }

    @GetMapping("login")
    public void goToLoginForm() {};

//    @GetMapping("/")
//    public String index(Model model) {
//        String memberName = (String) httpSession.getAttribute("name");
//
//        if (memberName != null) {
//            model.addAttribute("memberName", memberName);
//        }
//        return "index";
//    }

}
