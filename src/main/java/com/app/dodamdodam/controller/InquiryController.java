package com.app.dodamdodam.controller;

import com.app.dodamdodam.domain.InquiryDTO;
import com.app.dodamdodam.entity.inquiry.Inquiry;
import com.app.dodamdodam.service.inquiry.InquiryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/inquires/*")
@Slf4j
public class InquiryController {
    private final InquiryService inquiryService;

    @GetMapping("register")
    public String inquiryRegister(){ return "helpcenter/inquiry-register"; }

    @PostMapping("/register")
    public String inquiryRegisterFinish(@ModelAttribute InquiryDTO inquiryDTO) {
        inquiryService.register(inquiryDTO);
        return "redirect:/inquiry/success";
    }
}
