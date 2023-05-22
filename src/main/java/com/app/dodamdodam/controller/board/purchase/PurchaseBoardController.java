package com.app.dodamdodam.controller.board.purchase;

import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.domain.ProductDTO;
import com.app.dodamdodam.domain.PurchaseBoardDTO;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.search.PurchaseBoardSearch;
import com.app.dodamdodam.service.board.purchase.PurchaseBoardService;
import com.sun.net.httpserver.HttpsServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/purchase/*")
@RequiredArgsConstructor
@Slf4j
public class PurchaseBoardController {
    private final PurchaseBoardService purchaseBoardService;

    @GetMapping("write")
    public String goPurchaseBoardWrite(){ return "sell-board/sell-write"; }

    @ResponseBody
    @PostMapping("write")
    public RedirectView getPurchaseWriteForm(@RequestBody PurchaseBoardDTO purchaseBoardDTO, HttpSession session){
//        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
//        Long memberId = memberDTO.getId();
        Long memberId = (Long) session.getAttribute("id");
        log.info(memberId + "");
        purchaseBoardService.register(purchaseBoardDTO, memberId);

        return new RedirectView("/purchase/list");
    }

    
    @GetMapping("list")
    public String goPurchaseBoardList(HttpSession session, Model model){
//        sess
//        model.addAttribute()
        return "/sell-board/sell-list";
    }
    
    @PostMapping("list")
    @ResponseBody
    public Slice<PurchaseBoardDTO> getPurchaseBoardList(@RequestBody PurchaseBoardDTO purchaseBoardDTO, HttpSession session){


//        Slice<PurchaseBoardDTO> purchaseBoardDTOs = purchaseBoardService.getPurchaseBoardsWithSearch(purchaseBoardSearch, pageable);


//        return purchaseBoardDTOs;
        return null;
    }


}



















