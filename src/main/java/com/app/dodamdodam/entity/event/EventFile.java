package com.app.dodamdodam.entity.event;

import com.app.dodamdodam.domain.EventBoardDTO;
import com.app.dodamdodam.entity.file.BoardFile;
import com.app.dodamdodam.type.FileRepresent;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true)
@Table(name = "TBL_EVENT_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventFile extends BoardFile {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_BOARD_ID")
    private EventBoard eventBoard;

    public EventFile(Long id, String fileOriginalName, String fileUuid, String filePath, Long fileSize, EventBoard eventBoard) {
        super(id, fileOriginalName, fileUuid, filePath, fileSize);
    }
    //    파일이 대표 파일인지 여부
    @Enumerated(EnumType.STRING)
    private FileRepresent fileRepresent;

    @Builder
    public EventFile(Long id, String fileOriginalName, String fileUuid, String filePath, Long fileSize, EventBoard eventBoard, FileRepresent fileRepresent) {
        super(id, fileOriginalName, fileUuid, filePath, fileSize);
        this.eventBoard = eventBoard;
        this.fileRepresent = fileRepresent;
    }

    public void setFileRepresent(FileRepresent fileRepresent) {
        this.fileRepresent = fileRepresent;
    }

    public void setEventBoard(EventBoard eventBoard) {
        this.eventBoard = eventBoard;
    }
}
