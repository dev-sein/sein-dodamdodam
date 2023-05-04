package com.app.dodamdodam.entity.purchase;

import com.app.dodamdodam.entity.board.Board;
import com.app.dodamdodam.entity.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@ToString(callSuper = true)
@Table(name = "TBL_PURCHASE_BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseBoard extends Board {
    private Integer purchasePrice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseBoard")
    private List<PurchaseFile> purchaseFiles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseBoard")
    private List<PurchaseReview> purchaseReviews;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
