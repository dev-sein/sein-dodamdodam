package com.app.dodamdodam.entity;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @ToString
@Table(name = "TBL_PURCHASE_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseFile {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String purchaseFileOriginalName;
    @NotNull private String purchaseFileUuid;
    @NotNull private String purchaseFilePath;
    @NotNull private String purchaseFileSize;

}
