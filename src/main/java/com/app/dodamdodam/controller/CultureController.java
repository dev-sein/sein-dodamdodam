package com.app.dodamdodam.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/culture/*")
@RequiredArgsConstructor
@Slf4j
public class CultureController {

    /* 문화공간 게시판 */
    @GetMapping("culture-space")
    public String cultureSpace(){
        log.info("문화공간 게시판 들어옴");
        return "culture/culture-space";
    }
}
