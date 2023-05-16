package com.app.dodamdodam.entity.point;

import com.app.dodamdodam.audit.Period;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.type.PointStatus;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "TBL_POINT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private Integer pointAmount;

    @Enumerated(EnumType.STRING)
    @NotNull private PointStatus pointStatus;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public Point(Integer pointAmount, PointStatus pointStatus) {
        this.pointAmount = pointAmount;
        this.pointStatus = pointStatus;
    }

    public void setMember(Member member) {
        this.member = member;
    }


    @Builder
    public Point(Long id, Integer pointAmount, PointStatus pointStatus) {
        this.id = id;
        this.pointAmount = pointAmount;
        this.pointStatus = pointStatus;
    }
}
