package com.app.dodamdodam.entity.purchase;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(exclude = "purchaseBoard")
@Table(name = "TBL_PRODUCT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String productName;
//    상품가격
    @NotNull private Integer productPrice;
//    상품개수
    @NotNull private Long productCount;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "PURCHASE_BOARD_ID")
//    private PurchaseBoard purchaseBoard;
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "PURCHASE_BOARD_ID")
    private PurchaseBoard purchaseBoard;

//    @OneToOne(fetch = FetchType.LAZY)
//    private Purchase purchase;
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
    private Purchase purchase;


    public Product(String productName, Integer productPrice, Long productCount) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCount = productCount;
    }

    public Product(String productName, Integer productPrice, Long productCount, PurchaseBoard purchaseBoard) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCount = productCount;
        this.purchaseBoard = purchaseBoard;
    }
}
