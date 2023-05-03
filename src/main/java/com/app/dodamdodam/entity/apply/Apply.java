package com.app.dodamdodam.entity.apply;

import com.app.dodamdodam.audit.Period;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "TBL_APPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Apply extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String applyStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private RecruitmentBoard recruitmentBoard;


}
