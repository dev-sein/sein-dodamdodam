package com.app.dodamdodam.controller;

import com.app.dodamdodam.domain.ProductDTO;
import com.app.dodamdodam.domain.PurchaseBoardDTO;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.search.PurchaseBoardSearch;
import com.app.dodamdodam.service.board.purchase.PurchaseBoardService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/boards/purchase/*")
@RequiredArgsConstructor
public class PurchaseBoardController {
    private final PurchaseBoardService purchaseBoardService;

    @GetMapping("write")
    public String goPurchaseBoardWrite(){ return "/sell-board/sell-write"; }

    @PostMapping("write")
    public RedirectView getPurchaseWriteForm(PurchaseBoardDTO purchaseBoardDTO, ProductDTO productDTO, HttpSession session){
        Long memberId = (Long) session.getAttribute("id");

        purchaseBoardService.register(purchaseBoardDTO, productDTO, memberId);

        return new RedirectView("/sell-board/sell-list");
    }


    @GetMapping("list")
    public String goPurchaseBoardList(){
        return "/sell-board/sell-list";
    }
    
    @PostMapping("list")
    @ResponseBody
//    public Map<String, Object> getPurchaseBoardList(@RequestBody Map<String, Object> requestData){
    public Slice<PurchaseBoardDTO> getPurchaseBoardList(@RequestBody Map<String, Object> requestData){
//        Map<String, Object> result = new HashMap<>();
        
//        ("purchaseBoardSearch") PurchaseBoardSearch purchaseBoardSearch, @RequestParam("pageable") Pageable pageable
        PurchaseBoardSearch purchaseBoardSearch = (PurchaseBoardSearch) requestData.get("purchaseBoardSearch");
        Pageable pageable = (Pageable) requestData.get("pageable"); 
        
//        result.put("purchaseBoards", purchaseBoardService.getPurchaseBoardsWithSearch(purchaseBoardSearch, pageable));
        Slice<PurchaseBoardDTO> purchaseBoardDTOs = purchaseBoardService.getPurchaseBoardsWithSearch(purchaseBoardSearch, pageable);

        return purchaseBoardDTOs;
    }


}



















