package com.app.dodamdodam.entity.apply;

import com.app.dodamdodam.audit.Period;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@ToString(exclude = "recruitmentBoard")
@Table(name = "TBL_APPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class Apply extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
//    수락대기, 수락, 수락거절
    @ColumnDefault("'APPLYING'")
    @Enumerated(EnumType.STRING)
    private ApplyType applyStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private RecruitmentBoard recruitmentBoard;


}
