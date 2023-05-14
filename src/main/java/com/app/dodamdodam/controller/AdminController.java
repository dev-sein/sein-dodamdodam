package com.app.dodamdodam.controller;

import com.app.dodamdodam.domain.InquiryDTO;
import com.app.dodamdodam.entity.inquiry.Inquiry;
import com.app.dodamdodam.service.inquiry.InquiryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admins/*")
@Slf4j
public class AdminController {
    private final InquiryService inquiryService;

    @GetMapping("inquiry/list") //문의 게시판 목록
    public String adminInquiryList(@RequestParam(value = "page", defaultValue = "1") Integer page) {
        return "admin/inquiry-list";
    }

    @ResponseBody
    @PostMapping("inquiry/list")
    public Page<InquiryDTO> adminInquiryGetListJson(@RequestParam(value = "page", defaultValue = "1") Integer page) {
        Pageable pageable = PageRequest.of(1, 10, Sort.Direction.DESC, "inquiryId");
        return inquiryService.showList(pageable);
    }

}
