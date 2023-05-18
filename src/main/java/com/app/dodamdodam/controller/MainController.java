package com.app.dodamdodam.controller;

import com.app.dodamdodam.provider.UserDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("")
@RequiredArgsConstructor
@Slf4j
public class MainController {

    @GetMapping("/main")
    public String gotoMain(@AuthenticationPrincipal UserDetail userDetail, Model model){
//        userDetail.getMemberEmail();
        model.addAttribute("id", userDetail.getId());
        return "/main/main";
    }
}
