package com.app.dodamdodam.entity.purchase;

import com.querydsl.core.annotations.QueryProjection;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PurchaseBoardDTO {
    private Long id;
    private String boardTitle;
    private String boardContent;
    private String productName;
    private Integer productPrice;
    private Long productCount;
    private String memberId;
    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private String memberAddress;
    private String memberAddressDetail;

    @QueryProjection
    public PurchaseBoardDTO(Long id, String boardTitle, String boardContent, String productName, Integer productPrice, Long productCount, String memberId, String memberName, String memberEmail, String memberPhone, String memberAddress, String memberAddressDetail) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCount = productCount;
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.memberAddress = memberAddress;
        this.memberAddressDetail = memberAddressDetail;
    }
}
