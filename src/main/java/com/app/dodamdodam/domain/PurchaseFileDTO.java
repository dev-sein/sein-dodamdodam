package com.app.dodamdodam.domain;

import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PurchaseFileDTO {
    private Long id;
    private String fileOriginalName;
    private String fileUuid;
    private String filePath;
    private Long fileSize;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private PurchaseBoard purchaseBoard;

    @QueryProjection
    public PurchaseFileDTO(Long id, String fileOriginalName, String fileUuid, String filePath, Long fileSize, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.fileOriginalName = fileOriginalName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
