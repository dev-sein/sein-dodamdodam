package com.app.dodamdodam.domain;

import com.app.dodamdodam.type.PointStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PointDTO {
    private Long id;
    private Integer pointAmount;
    private PointStatus pointStatus;
    private String memberName;
    private String memberId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @QueryProjection
    public PointDTO(Long id, Integer pointAmount, PointStatus pointStatus, String memberName, String memberId, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.pointAmount = pointAmount;
        this.pointStatus = pointStatus;
        this.memberName = memberName;
        this.memberId = memberId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
