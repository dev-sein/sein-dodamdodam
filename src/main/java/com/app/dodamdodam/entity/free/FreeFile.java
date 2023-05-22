package com.app.dodamdodam.entity.free;

import com.app.dodamdodam.entity.file.BoardFile;
import com.app.dodamdodam.type.FileType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true, exclude = "freeBoard")
@Table(name = "TBL_FREE_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeFile extends BoardFile {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FREE_BOARD_ID")
    @JsonBackReference
    private FreeBoard freeBoard;

    @Builder
    public FreeFile(Long id, String fileOriginalName, String fileUuid, String filePath, FileType fileType, Long fileSize, FreeBoard freeBoard) {
        super(id, fileOriginalName, fileUuid, filePath, fileType, fileSize);
        this.freeBoard = freeBoard;
    }

    public void setFreeBoard(FreeBoard freeBoard) {
        this.freeBoard = freeBoard;
    }
    /* 추가 */
}
