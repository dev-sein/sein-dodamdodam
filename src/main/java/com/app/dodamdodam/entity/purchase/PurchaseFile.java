package com.app.dodamdodam.entity.purchase;

import com.app.dodamdodam.entity.file.BoardFile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true)
@Table(name = "TBL_PURCHASE_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseFile extends BoardFile {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PURCHASE_BOARD_ID")
    private PurchaseBoard purchaseBoard;
}
