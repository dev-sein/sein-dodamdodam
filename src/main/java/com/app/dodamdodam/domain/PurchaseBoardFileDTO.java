package com.app.dodamdodam.domain;

import com.app.dodamdodam.type.CategoryType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PurchaseBoardFileDTO {
    private Long id;
    private String boardTitle;
    private String boardContent;
    private MemberDTO memberDTO;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private List<PurchaseFileDTO> purchaseFileDTOS;

    @QueryProjection
    public PurchaseBoardFileDTO(Long id, String boardTitle, String boardContent, MemberDTO memberDTO, LocalDateTime createdDate, LocalDateTime updatedDate, List<PurchaseFileDTO> purchaseFileDTOS) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.memberDTO = memberDTO;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.purchaseFileDTOS = purchaseFileDTOS;
    }
}
