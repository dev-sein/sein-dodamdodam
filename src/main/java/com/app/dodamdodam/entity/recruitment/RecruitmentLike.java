package com.app.dodamdodam.entity.recruitment;

import com.app.dodamdodam.audit.Period;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

}
