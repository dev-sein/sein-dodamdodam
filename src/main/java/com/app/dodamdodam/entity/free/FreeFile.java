package com.app.dodamdodam.entity.free;

import com.app.dodamdodam.entity.file.BoardFile;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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


    public FreeFile(String fileOriginalName, String fileUuid, String filePath, Long fileSize) {
        super(fileOriginalName, fileUuid, filePath, fileSize);
    }

    public void setFreeBoard(FreeBoard freeBoard) {
        this.freeBoard = freeBoard;
    }
}
