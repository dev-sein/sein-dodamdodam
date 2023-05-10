package com.app.dodamdodam.entity.purchase;

import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.reply.Reply;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@ToString(exclude = "purchaseBoard")
@Table(name = "TBL_PURCHASE_REVIEW")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseReview extends Reply {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String reviewContent;
    private Integer reviewGrade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PURCHASE_BOARD_ID")
    private PurchaseBoard purchaseBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public PurchaseReview(String reviewContent, Integer reviewGrade, PurchaseBoard purchaseBoard, Member member) {
        this.reviewContent = reviewContent;
        this.reviewGrade = reviewGrade;
        this.purchaseBoard = purchaseBoard;
        this.member = member;
    }
}
