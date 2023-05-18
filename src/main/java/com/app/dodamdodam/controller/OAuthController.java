package com.app.dodamdodam.controller;

import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.provider.UserDetail;
import com.app.dodamdodam.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OAuthController {

    private final MemberService memberService;

    @GetMapping("/")
    public RedirectView oAuthLogin(HttpSession session){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        log.info("==================================================");
        log.info(memberDTO.toString());
//        Optional<Member> optionalMember = memberService.getMemberByMemberEmail(memberDTO.getMemberEmail());
//        optionalMember.ifPresent(member -> session.setAttribute("member", member));
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
