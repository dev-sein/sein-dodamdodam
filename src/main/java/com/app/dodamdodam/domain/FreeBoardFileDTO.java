package com.app.dodamdodam.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FreeBoardFileDTO {
    private Long id;
    private String boardTitle;
    private String boardContent;
    private MemberDTO memberDTO;
    private List<FreeFileDTO> freeFileDTOS;

    @QueryProjection
    public FreeBoardFileDTO(Long id, String boardTitle, String boardContent, MemberDTO memberDTO, List<FreeFileDTO> freeFileDTOS) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.memberDTO = memberDTO;
        this.freeFileDTOS = freeFileDTOS;
    }
}
