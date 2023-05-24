package com.app.dodamdodam.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class RecruitmentBoardReplyDTO {
    private Long id;
    private String boardTitle;
    private String boardContent;
    private MemberDTO memberDTO;
    private List<RecruitmentReplyDTO> recruitmentReplyDTOS;

    @Builder
    public RecruitmentBoardReplyDTO(Long id, String boardTitle, String boardContent, MemberDTO memberDTO, List<RecruitmentReplyDTO> recruitmentReplyDTOS) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.memberDTO = memberDTO;
        this.recruitmentReplyDTOS = recruitmentReplyDTOS;
    }
}
