package com.app.dodamdodam.controller;

import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
@Slf4j
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

    @GetMapping("find-id")
    public void goToFindIdForm() {};

    @GetMapping("find-password")
    public void goToFindPasswordForm() {};

    @GetMapping("password-change")
    public void goToChangePasswordForm() {};

    @ResponseBody
    @PostMapping("check-id")
    public void checkId(@RequestBody String currentId){
        memberService.checkMemberId(currentId);
    }

    /* 이메일 보내기 */
    @GetMapping("find-password-email-send")
    public String findPasswordEmailSend() {
        return "/join-login/find-password-email-send";
    }

    /* 비밀번호 이메일로 변경 */
    @PostMapping("find-password-email")
    public RedirectView findPasswordEmail(String memberEmail, RedirectAttributes redirectAttributes) {

//        if(memberService.overlapByMemberEmail(memberEmail) == 0) {
//            return new RedirectView("/member/find-password?result=fail");
//        }
//
//        String randomKey = memberService.randomKey();
//
//        //    비밀번호 변경 이메일 발송시 랜덤 키 값 컬럼에 저장
//        //    비밀번호 변경 완료 시 랜덤 키 컬럼 값 삭제
//        memberService.updateRandomKey(memberEmail, randomKey);
//
//        MailDTO mailDTO = new MailDTO();
//        mailDTO.setAddress(memberEmail);
//        mailDTO.setTitle("새 비밀번호 설정 링크입니다.");
//        mailDTO.setMessage("링크: http://localhost:10000/member/change-password?memberEmail=" + memberEmail + "&randomKey=" + randomKey);
//        memberService.sendMail(mailDTO);
//
//        redirectAttributes.addFlashAttribute("memberEmail", memberEmail);
        return new RedirectView("/member/find-password-email-send");
    }

}
