package com.app.dodamdodam.domain;

import com.app.dodamdodam.type.CategoryType;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Component
public class FreeBoardDTO {
    private Long id;
    private String boardTitle;
    private String boardContent;
    private MemberDTO memberDTO;
    private CategoryType freeCategory;
    private int likeCount;

    private List<FreeBoardFileDTO> freeBoardFileDTOS;
    private List<FreeBoardReplyDTO> freeBoardReplyDTOS;


    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Builder
    public FreeBoardDTO(Long id, String boardTitle, String boardContent, MemberDTO memberDTO, CategoryType freeCategory, int likeCount, List<FreeBoardFileDTO> freeBoardFileDTOS, List<FreeBoardReplyDTO> freeBoardReplyDTOS, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.memberDTO = memberDTO;
        this.freeCategory = freeCategory;
        this.likeCount = likeCount;
        this.freeBoardFileDTOS = freeBoardFileDTOS;
        this.freeBoardReplyDTOS = freeBoardReplyDTOS;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
