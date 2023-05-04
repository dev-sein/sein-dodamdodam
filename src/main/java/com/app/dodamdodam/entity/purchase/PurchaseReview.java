package com.app.dodamdodam.entity.purchase;

import com.app.dodamdodam.audit.Period;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "TBL_PURCHASE_REVIEW")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseReview extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String reviewContent;

    private Integer reviewGrade;

    @ManyToOne
    @JoinColumn(name = "PURCHASE_BOARD_ID")
    private PurchaseBoard purchaseBoard;

}
