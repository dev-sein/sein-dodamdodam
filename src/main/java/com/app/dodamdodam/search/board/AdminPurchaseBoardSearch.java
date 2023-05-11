package com.app.dodamdodam.search.board;

import lombok.Data;

@Data
public class AdminPurchaseBoardSearch {
    private String boardTitle;
    private String memberName;
    private Integer productPrice;
    private Long productCount;
}
