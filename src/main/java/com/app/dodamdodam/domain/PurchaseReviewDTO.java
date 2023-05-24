package com.app.dodamdodam.domain;

import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class PurchaseReviewDTO {
    private Long id;
    private String replyContent;
    private Integer reviewGrade;
    private PurchaseBoard purchaseBoard;
    private Integer reviewGradeAverage;
    private Integer reviewCount;


    public PurchaseReviewDTO(Long id, String replyContent, Integer reviewGrade, PurchaseBoard purchaseBoard, Integer reviewGradeAverage, Integer reviewCount) {
        this.id = id;
        this.replyContent = replyContent;
        this.reviewGrade = reviewGrade;
        this.purchaseBoard = purchaseBoard;
        this.reviewGradeAverage = reviewGradeAverage;
        this.reviewCount = reviewCount;
    }
}
