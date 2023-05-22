package com.app.dodamdodam.domain;

import com.app.dodamdodam.entity.embeddable.Address;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.type.MemberStatus;
import com.app.dodamdodam.type.MemberType;
import com.app.dodamdodam.type.Role;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MemberDTO implements Serializable {
    private Long id;
    private String memberId;
    private String memberPassword;
    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private Address address;
    private MemberStatus memberStatus;
    private Integer memberPoint;
    private Integer participationCount;
    private LocalDateTime createdDate;
    private MemberType memberType;
    private Role memberRole;

    @QueryProjection
    @Builder
    public MemberDTO(Long id, String memberId, String memberPassword, String memberName, String memberEmail, String memberPhone, Address address, MemberStatus memberStatus, Integer memberPoint, Integer participationCount, LocalDateTime createdDate, Role memberRole, MemberType memberType) {
        this.id = id;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.address = address;
        this.memberStatus = memberStatus;
        this.memberPoint = memberPoint;
        this.participationCount = participationCount;
        this.createdDate = createdDate;
        this.memberType = memberType;
        this.memberRole = memberRole;
        this.memberType = memberType;
    }

    public MemberDTO(Member member) {
        this.memberName = member.getMemberName();
        this.memberEmail = member.getMemberEmail();
        this.memberPhone = member.getMemberPhone();
    }


}
