package com.app.dodamdodam.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("chatting/*")
@RequiredArgsConstructor
@Slf4j
public class ChattingController {

    @GetMapping("/chatting")
    public String chatGET(){

        log.info("@ChattingController, chat GET()");

        return "chatting";
    }

}
