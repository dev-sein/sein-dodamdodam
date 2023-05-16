package com.app.dodamdodam.controller;

import com.app.dodamdodam.domain.PurchaseBoardDTO;
import com.app.dodamdodam.search.PurchaseBoardSearch;
import com.app.dodamdodam.service.board.purchase.PurchaseBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/boards/purchase/*")
@RequiredArgsConstructor
public class PurchaseBoardController {
    private final PurchaseBoardService purchaseBoardService;

//    @GetMapping("list")
//    @ResponseBody
//    public String getPurchaseBoardList(@RequestParam("purchaseBoardSearch") PurchaseBoardSearch purchaseBoardSearch, @RequestParam("pageable") Pageable pageable){
//        model.addAttribute("purchaseBoards", purchaseBoardService.getPurchaseBoardsWithSearch(purchaseBoardSearch, pageable));
//        return "/sell-board/sell-list.html";
//    }
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



















