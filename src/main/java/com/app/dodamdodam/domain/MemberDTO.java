package com.app.dodamdodam.domain;

import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.recruitment.Recruitment;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.type.MemberStatus;
import com.querydsl.core.annotations.QueryProjection;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class MemberDTO {
    private Long id;

    private String memberId;
    private String memberPassword;
    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private String memberAddress;
    private String memberAddressDetail;
    private MemberStatus memberStatus;
    private Integer memberPoint;
    private Integer participationCount;

    private Integer recruitmentedCount;

    @QueryProjection
    public MemberDTO(String memberId, String memberPassword, String memberName, String memberEmail, String memberPhone, String memberAddress, String memberAddressDetail, MemberStatus memberStatus, Integer memberPoint, Integer participationCount, Integer recruitmentedCount) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.memberAddress = memberAddress;
        this.memberAddressDetail = memberAddressDetail;
        this.memberStatus = memberStatus;
        this.memberPoint = memberPoint;
        this.participationCount = participationCount;
        this.recruitmentedCount = recruitmentedCount;
    }
}
