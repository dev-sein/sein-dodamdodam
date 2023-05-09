package com.app.dodamdodam.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PurchaseBoardDTO {
    private Long id;
    private String boardTitle;
    private String boardContent;
    private ProductDTO productDTO;
    private MemberDTO memberDTO;
    private List<PurchaseFileDTO> purchaseFileDTOs;

    @QueryProjection
    public PurchaseBoardDTO(Long id, String boardTitle, String boardContent, ProductDTO productDTO, MemberDTO memberDTO, List<PurchaseFileDTO> purchaseFileDTOs) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.productDTO = productDTO;
        this.memberDTO = memberDTO;
        this.purchaseFileDTOs = purchaseFileDTOs;
    }
}
