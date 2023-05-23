package com.app.dodamdodam.domain;

import com.app.dodamdodam.type.BannerType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BannerDTO {
    private Long id;
    private LocalDate bannerRegisterDate;
    private int period;
    private BannerType bannerStatus;
    private String bannerContent;
    private String fileOriginalName;
    private String fileUuid;
    private MemberDTO memberDTO;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Builder
    public BannerDTO(Long id, LocalDate bannerRegisterDate, int period, BannerType bannerStatus, String bannerContent, String fileOriginalName, String fileUuid, MemberDTO memberDTO, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.bannerRegisterDate = bannerRegisterDate;
        this.period = period;
        this.bannerStatus = bannerStatus;
        this.bannerContent = bannerContent;
        this.fileOriginalName = fileOriginalName;
        this.fileUuid = fileUuid;
        this.memberDTO = memberDTO;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
