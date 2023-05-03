package com.app.dodamdodam.entity;

import com.app.dodamdodam.audit.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_FREE_BOARD_FILE")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeBoardFile extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String freeFileOriginalName;
    @NotNull private String freeFileUuid;
    @NotNull private String freeFilePath;
    @NotNull private String freeFileSize;
}
