package com.app.dodamdodam.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@NoArgsConstructor
public class EventFileDTO {
    private Long id;
    private String fileOriginalName;
    private String fileUuid;
    private String filePath;
    private Long fileSize;

    @Builder
    public EventFileDTO(Long id, String fileOriginalName, String fileUuid, String filePath, Long fileSize) {
        this.id = id;
        this.fileOriginalName = fileOriginalName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}
