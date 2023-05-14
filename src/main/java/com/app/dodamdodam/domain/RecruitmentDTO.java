package com.app.dodamdodam.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class RecruitmentDTO {

    private Long id;
    private MemberDTO member;

    @QueryProjection
    public RecruitmentDTO(Long id, MemberDTO member) {
        this.id = id;
        this.member = member;
    }
}

