package com.app.dodamdodam.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class RecruitmentMemberDTO {

    private Long recruitmentBoardId;
    private List<MemberDTO> memberDTOS;

    @QueryProjection
    public RecruitmentMemberDTO(Long recruitmentBoardId, List<MemberDTO> memberDTOS) {
        this.recruitmentBoardId = recruitmentBoardId;
        this.memberDTOS = memberDTOS;
    }
}

