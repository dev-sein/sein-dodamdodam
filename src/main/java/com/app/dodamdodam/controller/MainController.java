package com.app.dodamdodam.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class MainController {

    @GetMapping("home")
    public String main(Model model){

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
