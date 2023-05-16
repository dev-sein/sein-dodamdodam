package com.app.dodamdodam.domain;

import com.app.dodamdodam.type.RecruitmentType;
import com.querydsl.core.annotations.QueryProjection;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class RecruitmentBoardFileDTO {

    private Long id;

    private String boardTitle;
    private String boardContent;

    private String recruitmentSubtitle;
    private LocalDate recruitmentDate;
    private int recruitmentPeopleCount;
    private String recruitmentOpenChatting;
    private String recruitmentPassword;
    private RecruitmentType recruitmentStatus;
    private String recruitmentAddress;
    private String recruitmentAddressDetail;
    private MemberDTO memberDTO;
    private List<RecruitmentDTO> recruitmentDTOS;
    private List<RecruitmentFileDTO> recruitmentFileDTOS;


    @QueryProjection
    public RecruitmentBoardFileDTO(Long id, String boardTitle, String boardContent, String recruitmentSubtitle, LocalDate recruitmentDate, int recruitmentPeopleCount, String recruitmentOpenChatting, String recruitmentPassword, RecruitmentType recruitmentStatus, String recruitmentAddress, String recruitmentAddressDetail, MemberDTO memberDTO, List<RecruitmentDTO> recruitmentDTOS, List<RecruitmentFileDTO> recruitmentFileDTOS) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.recruitmentSubtitle = recruitmentSubtitle;
        this.recruitmentDate = recruitmentDate;
        this.recruitmentPeopleCount = recruitmentPeopleCount;
        this.recruitmentOpenChatting = recruitmentOpenChatting;
        this.recruitmentPassword = recruitmentPassword;
        this.recruitmentStatus = recruitmentStatus;
        this.recruitmentAddress = recruitmentAddress;
        this.recruitmentAddressDetail = recruitmentAddressDetail;
        this.memberDTO = memberDTO;
        this.recruitmentDTOS = recruitmentDTOS;
        this.recruitmentFileDTOS = recruitmentFileDTOS;
    }
}

