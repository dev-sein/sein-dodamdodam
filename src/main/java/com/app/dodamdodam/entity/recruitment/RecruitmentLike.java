package com.app.dodamdodam.entity.recruitment;

import com.app.dodamdodam.audit.Period;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "TBL_RECRUITMENT_LIKE")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitmentLike extends Period {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne
    @JoinColumn(name = "RECRUITMENT_BOARD_ID")
    private RecruitmentBoard recruitmentBoard;
}
