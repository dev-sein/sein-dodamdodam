package com.app.dodamdodam.domain;

import com.querydsl.core.annotations.QueryProjection;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    private Long id;
    private String productName;
    private Integer productPrice;
    private Long productCount;

    @QueryProjection
    public ProductDTO(Long id, String productName, Integer productPrice, Long productCount) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCount = productCount;
    }
}
