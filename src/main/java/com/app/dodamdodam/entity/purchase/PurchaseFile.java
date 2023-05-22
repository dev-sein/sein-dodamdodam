package com.app.dodamdodam.entity.purchase;

import com.app.dodamdodam.entity.file.BoardFile;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true, exclude = "purchaseBoard")
@Table(name = "TBL_PURCHASE_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseFile extends BoardFile {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PURCHASE_BOARD_ID")
    private PurchaseBoard purchaseBoard;

    public void setPurchaseBoard(PurchaseBoard purchaseBoard) {
        this.purchaseBoard = purchaseBoard;
    }

    //    public PurchaseFile(Long id, String fileOriginalName, String fileUuid, String filePath, Long fileSize, PurchaseBoard purchaseBoard) {
//        super(id, fileOriginalName, fileUuid, filePath, fileSize);
//        this.purchaseBoard = purchaseBoard;
//    }
    @Builder
    public PurchaseFile(Long id, String fileOriginalName, String fileUuid, String filePath, Long fileSize, PurchaseBoard purchaseBoard) {
        super(id, fileOriginalName, fileUuid, filePath, fileSize);
        this.purchaseBoard = purchaseBoard;
    }



}
