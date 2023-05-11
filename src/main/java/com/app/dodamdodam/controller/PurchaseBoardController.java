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

import java.util.List;

@Controller
@RequestMapping("/boards/purchase/*")
@RequiredArgsConstructor
public class PurchaseBoardController {
    private final PurchaseBoardService purchaseBoardService;

    @GetMapping("list")
    @ResponseBody
    public String getPurchaseBoardList(@RequestParam("purchaseBoardSearch") PurchaseBoardSearch purchaseBoardSearch, @RequestParam("pageable") Pageable pageable, Model model){
        model.addAttribute("purchaseBoards", purchaseBoardService.getPurchaseBoardsWithSearch(purchaseBoardSearch, pageable));
        return "/sell-board/sell-list.html";
    }
}
