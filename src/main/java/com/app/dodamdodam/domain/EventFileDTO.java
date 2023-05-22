package com.app.dodamdodam.domain;

import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.type.FileType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
public class EventFileDTO {
    private Long id;
    private String fileOriginalName;
    private String fileUuid;
    private String filePath;
    private Long fileSize;

    private FileType fileType;

    private EventBoard eventBoard;

    private EventBoardDTO eventBoardDTO;

    // 파일 데이터를 저장할 필드
    private MultipartFile fileData;

    // @QueryProjection 어노테이션을 사용하지 않는 생성자
    public EventFileDTO(Long id, String fileOriginalName, String fileUuid, String filePath, Long fileSize, FileType fileType, EventBoard eventBoard, EventBoardDTO eventBoardDTO, MultipartFile fileData) {
        this.id = id;
        this.fileOriginalName = fileOriginalName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.eventBoard = eventBoard;
        this.eventBoardDTO = eventBoardDTO;
        this.fileData = fileData;
    }
}