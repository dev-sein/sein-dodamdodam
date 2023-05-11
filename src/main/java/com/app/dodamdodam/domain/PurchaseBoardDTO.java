package com.app.dodamdodam.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
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
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @QueryProjection
    public PurchaseBoardDTO(Long id, String boardTitle, String boardContent, ProductDTO productDTO, MemberDTO memberDTO, List<PurchaseFileDTO> purchaseFileDTOs, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.productDTO = productDTO;
        this.memberDTO = memberDTO;
        this.purchaseFileDTOs = purchaseFileDTOs;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
