package com.app.dodamdodam.entity.purchase;

import com.app.dodamdodam.entity.board.Board;
import com.app.dodamdodam.entity.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString(callSuper = true, exclude = {"purchaseFiles", "purchaseReviews"})
@Table(name = "TBL_PURCHASE_BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseBoard extends Board {
//    @Id
//    @GeneratedValue
//    @EqualsAndHashCode.Include
//    private Long id;
//    private Integer purchasePrice;
//    @NotNull private Integer purchaseCount;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "purchaseBoard", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Product product;
//    private Integer purchasePrice;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "purchaseBoard",
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST}
    )
    private List<PurchaseFile> purchaseFiles = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "purchaseBoard",
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST}
    )
    private List<PurchaseReview> purchaseReviews = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public PurchaseBoard(String boardTitle, String boardContent) {
        super(boardTitle, boardContent);
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setMember(Member member) {
        this.member = member;
    }


}
