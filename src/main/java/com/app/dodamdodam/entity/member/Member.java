package com.app.dodamdodam.entity.member;

import com.app.dodamdodam.audit.Period;
import com.app.dodamdodam.entity.embeddable.Address;
import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.recruitment.Recruitment;
import com.app.dodamdodam.type.MemberStatus;
import com.app.dodamdodam.type.MemberType;
import com.app.dodamdodam.type.Role;
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
    private int participationCount;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @Enumerated(EnumType.STRING)
    private Role memberRole;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.REMOVE)
//    private List<Recruitment> recruitments = new ArrayList<>();

//    public Member(String memberId, String memberPassword, String memberName, String memberEmail, String memberPhone, Address address, MemberStatus memberStatus, MemberType memberType, Role memberRole) {
//        this.memberId = memberId;
//        this.memberPassword = memberPassword;
//        this.memberName = memberName;
//        this.memberEmail = memberEmail;
//        this.memberPhone = memberPhone;
//        this.address = address;
//        this.memberStatus = memberStatus;
//        this.memberType = memberType;
//        this.memberRole = memberRole;
//    }
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<EventBoard> eventBoards;


    @Builder
    public Member(Long id, String memberId, String memberPassword, String memberName, String memberEmail, String memberPhone, Address address, MemberStatus memberStatus, Integer memberPoint, int participationCount, MemberType memberType, Role memberRole, List<EventBoard> eventBoards) {
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
        this.memberType = memberType;
        this.memberRole = memberRole;
        this.eventBoards = eventBoards;
    }

//    @Builder
//    public Member(String memberName, String memberPhone, String memberEmail, Role memberRole, MemberStatus memberStatus, MemberType memberType) {
//        this.memberName = memberName;
//        this.memberPhone = memberPhone;
//        this.memberEmail = memberEmail;
//        this.memberRole = memberRole;
//        this.memberStatus = memberStatus;
//        this.memberType = memberType;
//    }

    public Member update(String memberName, String memberPhone, String memberEmail, MemberType memberType){
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.memberEmail = memberEmail;
        this.memberType = memberType;

        return this;
    }

    public String getRoleKey() {
        return this.memberRole.getSecurityRole();
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setMemberPoint(Integer memberPoint) {
        this.memberPoint = memberPoint;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public void setMemberStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    public void setParticipationCountPlus(){
        this.participationCount++;
    }

    public void setParticipationCountMinus(){
        this.participationCount--;
    }

    public Integer getParticipationCount() {
        return participationCount;
    }
}