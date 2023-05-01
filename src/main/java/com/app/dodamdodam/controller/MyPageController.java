package com.app.dodamdodam.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {
    /*마이 페이지 메인*/
    @GetMapping("mypage-main")
    public String mypageMain() {
        return"mypage/myPage-Main";
    }
}
