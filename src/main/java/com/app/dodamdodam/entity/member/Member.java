package com.app.dodamdodam.entity.member;

import com.app.dodamdodam.audit.Period;
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
    @ColumnDefault("'general'")
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;
    @ColumnDefault("'0'")
    private Integer memberPoint;
    @ColumnDefault("0")
    private Integer participationCount;

}