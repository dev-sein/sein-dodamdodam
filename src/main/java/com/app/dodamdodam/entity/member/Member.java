package com.app.dodamdodam.entity.member;

import com.app.dodamdodam.audit.Period;
import com.app.dodamdodam.entity.embeddable.Address;
import com.app.dodamdodam.entity.recruitment.Recruitment;
import com.app.dodamdodam.type.MemberStatus;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Embedded
    @NotNull private Address address;

    @ColumnDefault("'GENERAL'")
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;
    @ColumnDefault("'0'")
    private Integer memberPoint;
    @ColumnDefault("0")
    private Integer participationCount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Recruitment> recruitments = new ArrayList<>();

    public Member(String memberId, String memberPassword, String memberName, String memberEmail, String memberPhone, Address address, MemberStatus memberStatus) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.address = address;
        this.memberStatus = memberStatus;
    }

    public void setMemberPoint(Integer memberPoint) {
        this.memberPoint = memberPoint;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }
}