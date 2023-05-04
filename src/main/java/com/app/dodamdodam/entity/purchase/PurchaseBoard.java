package com.app.dodamdodam.entity.purchase;

import com.app.dodamdodam.audit.Period;
import com.app.dodamdodam.entity.board.Board;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
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
}
