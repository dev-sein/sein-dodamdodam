package com.app.dodamdodam.controller;

import com.app.dodamdodam.domain.PurchaseBoardDTO;
import com.app.dodamdodam.search.PurchaseBoardSearch;
import com.app.dodamdodam.service.board.purchase.PurchaseBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/boards/purchase/*")
@RequiredArgsConstructor
public class PurchaseBoardController {
    private final PurchaseBoardService purchaseBoardService;

    @GetMapping("list")
    public List<PurchaseBoardDTO> getPurchaseBoardList(PurchaseBoardSearch purchaseBoardSearch, Pageable pageable){
        return purchaseBoardService.getPurchaseBoardsWithSearch(purchaseBoardSearch, pageable);
    }
}
