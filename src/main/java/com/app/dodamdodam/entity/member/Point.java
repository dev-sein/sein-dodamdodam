package com.app.dodamdodam.entity.member;

import com.app.dodamdodam.audit.Period;
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
    @NotNull private String pointStatus;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
