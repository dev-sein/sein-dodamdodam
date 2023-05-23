package com.app.dodamdodam.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class RecruitmentBoardDTO {
    /*게시글 */
    private Long id;
    private String boardTitle;
    private String boardContent;
    
    /*모집 게시글*/
    private String recruitmentSubtitle;
    private String recruitmentAddress;
    private String recruitmentAddressDetail;
    private LocalDate recruitmentDate;
    private int recruitmentPeopleCount;
    private String recruitmentOpenChatting;

    /*댓글 정보*/
    private List<RecruitmentReplyDTO> recruitmentReplyDTOS;

    /*파일*/
    private List<RecruitmentFileDTO> recruitmentFileDTOS;

    /*멤버 정보 */
    private MemberDTO memberDTO;

    /*만든날짜, 업뎃일*/
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Builder
    public RecruitmentBoardDTO(Long id, String boardTitle, String boardContent, String recruitmentSubtitle, String recruitmentAddress, String recruitmentAddressDetail, LocalDate recruitmentDate, int recruitmentPeopleCount, String recruitmentOpenChatting, List<RecruitmentReplyDTO> recruitmentReplyDTOS, List<RecruitmentFileDTO> recruitmentFileDTOS, MemberDTO memberDTO, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.recruitmentSubtitle = recruitmentSubtitle;
        this.recruitmentAddress = recruitmentAddress;
        this.recruitmentAddressDetail = recruitmentAddressDetail;
        this.recruitmentDate = recruitmentDate;
        this.recruitmentPeopleCount = recruitmentPeopleCount;
        this.recruitmentOpenChatting = recruitmentOpenChatting;
        this.recruitmentReplyDTOS = recruitmentReplyDTOS;
        this.recruitmentFileDTOS = recruitmentFileDTOS;
        this.memberDTO = memberDTO;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
