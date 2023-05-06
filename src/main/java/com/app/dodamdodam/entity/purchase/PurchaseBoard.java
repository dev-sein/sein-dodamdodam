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
//    @Id
//    @GeneratedValue
//    @EqualsAndHashCode.Include
//    private Long id;
//    private Integer purchasePrice;
//    @NotNull private Integer purchaseCount;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "purchaseBoard")
    private Product product;
//    private Integer purchasePrice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseBoard")
    private List<PurchaseFile> purchaseFiles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseBoard")
    private List<PurchaseReview> purchaseReviews;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public PurchaseBoard(Product product) {
        this.product = product;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public PurchaseBoard(Product product, List<PurchaseFile> purchaseFiles, Member member) {
        this.product = product;
        this.purchaseFiles = purchaseFiles;
        this.member = member;
    }
}
