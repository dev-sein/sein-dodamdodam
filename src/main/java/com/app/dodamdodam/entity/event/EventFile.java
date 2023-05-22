package com.app.dodamdodam.entity.event;

import com.app.dodamdodam.entity.file.BoardFile;
import com.app.dodamdodam.type.FileType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true, exclude = "eventBoard")
@Table(name = "TBL_EVENT_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventFile extends BoardFile {

    @ManyToOne(fetch = FetchType.LAZY)
    private EventBoard eventBoard;

    @Builder
    public EventFile(Long id, String fileOriginalName, String fileUuid, String filePath, FileType fileType, EventBoard eventBoard) {
        super(id, fileOriginalName, fileUuid, filePath, fileType);
        this.eventBoard = eventBoard;
    }

    public EventFile(String fileOriginalName, String fileUuid, String filePath, FileType fileType, EventBoard eventBoard) {
        super(fileOriginalName, fileUuid, filePath, fileType);
        this.eventBoard = eventBoard;
    }

    public void setEventBoard(EventBoard eventBoard) {
        this.eventBoard = eventBoard;

        if(!eventBoard.getEventFiles().contains(this)){
            eventBoard.getEventFiles().add(this);
        }
    }
}