package com.app.dodamdodam.domain;

import com.app.dodamdodam.type.CategoryType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class FreeBoardFileDTO {
    private Long id;
    private String boardTitle;
    private String boardContent;
    private CategoryType freeCategory;
    private MemberDTO memberDTO;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private int likeCount;
    private List<FreeFileDTO> freeFileDTOS;

    @QueryProjection
    public FreeBoardFileDTO(Long id, String boardTitle, String boardContent, CategoryType freeCategory, MemberDTO memberDTO, LocalDateTime createdDate, LocalDateTime updatedDate, int likeCount, List<FreeFileDTO> freeFileDTOS) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.freeCategory = freeCategory;
        this.memberDTO = memberDTO;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.likeCount = likeCount;
        this.freeFileDTOS = freeFileDTOS;
    }
}
