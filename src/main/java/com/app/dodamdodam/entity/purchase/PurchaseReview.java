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
//    @Id @GeneratedValue
//    @EqualsAndHashCode.Include
//    private Long id;
//    private String reviewContent;
    private Integer reviewGrade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PURCHASE_BOARD_ID")
    private PurchaseBoard purchaseBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public PurchaseReview(Long id, String replyContent, Member member, Integer reviewGrade, PurchaseBoard purchaseBoard, Member member1) {
        super(id, replyContent, member);
        this.reviewGrade = reviewGrade;
        this.purchaseBoard = purchaseBoard;
        this.member = member;
    }

    public PurchaseReview(Integer reviewGrade, PurchaseBoard purchaseBoard, Member member) {
        this.reviewGrade = reviewGrade;
        this.purchaseBoard = purchaseBoard;
        this.member = member;
    }

    public PurchaseReview(String replyContent, Member member, Integer reviewGrade, PurchaseBoard purchaseBoard, Member member1) {
        super(replyContent, member);
        this.reviewGrade = reviewGrade;
        this.purchaseBoard = purchaseBoard;
        this.member = member1;
    }

    public PurchaseReview(String replyContent, Integer reviewGrade, PurchaseBoard purchaseBoard, Member member) {
        super(replyContent);
        this.reviewGrade = reviewGrade;
        this.purchaseBoard = purchaseBoard;
        this.member = member;
    }
}
