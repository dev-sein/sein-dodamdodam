package com.app.dodamdodam.domain;

import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class RecruitmentDTO {

    private Long id;
    private MemberDTO member;
//    private RecruitmentBoardDTO recruitmentBoardDTO;

    @QueryProjection
    public RecruitmentDTO(Long id, MemberDTO member) {
        this.id = id;
        this.member = member;
    }
}

