package com.app.dodamdodam.entity.event;

import com.app.dodamdodam.entity.file.BoardFile;
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
        this.eventBoard = eventBoard;
    }
}
