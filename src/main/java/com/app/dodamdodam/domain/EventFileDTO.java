package com.app.dodamdodam.domain;

import com.app.dodamdodam.entity.board.Board;
import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.type.FileRepresent;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.File;
import java.util.List;

@Data
@Builder
public class EventFileDTO {
    private Long id;
    private String fileOriginalName;
    private String fileUuid;
    private String filePath;
    private Long fileSize;

    private FileRepresent fileRepresent;

    private EventBoard eventBoard;

    private EventBoardDTO eventBoardDTO;

//    public void setFileRepresent(FileRepresent fileRepresent) {
//        this.fileRepresent = fileRepresent;
//    }
//
//    public void setBoard(EventBoard eventBoard) {
//        this.eventBoard = eventBoard;
//    }
//
//    public void setEventBoardDTO(EventBoardDTO eventBoardDTO) {
//        this.eventBoardDTO = eventBoardDTO;
//    }

    @QueryProjection

    public EventFileDTO(Long id, String fileOriginalName, String fileUuid, String filePath, Long fileSize, FileRepresent fileRepresent, EventBoard eventBoard, EventBoardDTO eventBoardDTO) {
        this.id = id;
        this.fileOriginalName = fileOriginalName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.fileRepresent = fileRepresent;
        this.eventBoard = eventBoard;
        this.eventBoardDTO = eventBoardDTO;
    }
}
