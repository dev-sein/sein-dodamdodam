package com.app.dodamdodam.entity;

import com.app.dodamdodam.audit.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@ToString
@Table(name = "TBL_PURCHASE_BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseBoard extends Period {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String purchaseTitle;
    private Integer purchasePrice;
    @NotNull private Integer purchaseCount;


}
