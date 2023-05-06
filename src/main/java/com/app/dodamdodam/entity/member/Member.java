package com.app.dodamdodam.entity.member;

import com.app.dodamdodam.audit.Period;
import com.app.dodamdodam.type.MemberStatus;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "TBL_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class Member extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String memberId;
    @NotNull private String memberPassword;
    @NotNull private String memberName;
    @NotNull private String memberEmail;
    @NotNull private String memberPhone;
    @NotNull private String memberAddress;
    @NotNull private String memberAddressDetail;
    @ColumnDefault("'GENERAL'")
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;
    @ColumnDefault("'0'")
    private Integer memberPoint;
    @ColumnDefault("0")
    private Integer participationCount;

    public Member(String memberId, String memberPassword, String memberName, String memberEmail, String memberPhone, String memberAddress, String memberAddressDetail) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.memberAddress = memberAddress;
        this.memberAddressDetail = memberAddressDetail;
    }

    public void setMemberPoint(Integer memberPoint) {
        this.memberPoint = memberPoint;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }
}