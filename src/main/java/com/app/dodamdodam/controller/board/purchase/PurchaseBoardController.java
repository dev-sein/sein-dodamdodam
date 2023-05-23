package com.app.dodamdodam.controller.board.purchase;

import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.domain.ProductDTO;
import com.app.dodamdodam.domain.PurchaseBoardDTO;
import com.app.dodamdodam.domain.PurchaseDTO;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.search.PurchaseBoardSearch;
import com.app.dodamdodam.service.board.purchase.PurchaseBoardService;
import com.app.dodamdodam.service.member.MemberService;
import com.sun.net.httpserver.HttpsServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
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
    private final MemberService memberService;

    @GetMapping("write")
    public String goPurchaseBoardWrite(){ return "sell-board/sell-write"; }

    @ResponseBody
    @PostMapping("write")
    public void getPurchaseWriteForm(@RequestBody PurchaseBoardDTO purchaseBoardDTO, HttpSession session){
//        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
//        Long memberId = memberDTO.getId();
        Long memberId = (Long) session.getAttribute("memberId");
        log.info(memberId + "");
        purchaseBoardService.register(purchaseBoardDTO, memberId);

//        return new RedirectView("/purchase/list");
    }

    
    @GetMapping("list")
    public String goPurchaseBoardList(HttpSession session, Model model){
        log.info("@@@");

//        if (session.getAttribute("memberId") != null) {
//            Long memberId = (Long) session.getAttribute("memberId");
//            MemberDTO member = memberService.getMemberInfo(memberId);
//            model.addAttribute("member", member);
//        }

        return "/sell-board/sell-list";
    }
    
    @GetMapping("list-content/{page}")
    @ResponseBody
    public Slice<PurchaseBoardDTO> getPurchaseBoardList(PurchaseBoardSearch purchaseBoardSearch, @PathVariable("page") Integer page){
        log.info("@@@@@@");

        PageRequest pageRequest = PageRequest.of(page - 1, 12);
        log.info("purchaseBoardSearch.toString() ========================");
        log.info(purchaseBoardSearch + "");
        log.info(pageRequest + "");
        Slice<PurchaseBoardDTO> purchaseBoardDTOs = purchaseBoardService.getPurchaseBoardsWithSearch(purchaseBoardSearch, pageRequest);

        log.info("purchaseBoardDTOs.toString() =============================");
        log.info(purchaseBoardDTOs.toString());

        return purchaseBoardDTOs;
    }

    @GetMapping("detail/{boardId}")
    public String getPurchaseBoardDetail(@PathVariable("boardId") Long boardId, Model model){
        Map<String, Object> result = purchaseBoardService.getPurchaseBoard(boardId);
        PurchaseBoardDTO purchaseBoardDTO = (PurchaseBoardDTO) result.get("purchaseBoardDTO");
        Long purchaseCount = (Long) result.get("purchaseCount");
        Long purchaseReviewCount = (Long) result.get("purchaseReviewCount");
//        PurchaseDTO purchaseDTO =

        log.info("#################################################");
        log.info(purchaseBoardDTO.toString());
        log.info(String.valueOf(purchaseCount));
        log.info(String.valueOf(purchaseReviewCount));


        model.addAttribute("purchaseBoardDTO", purchaseBoardDTO);
        model.addAttribute("purchaseCount", purchaseCount);
        model.addAttribute("purchaseReviewCount", purchaseReviewCount);
//        model.addAttribute("purchaseDTO", purchaseDTO);

        return "sell-board/sell-detail";
    }


}



















