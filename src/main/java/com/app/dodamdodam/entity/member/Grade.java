package com.app.dodamdodam.entity.member;

import com.app.dodamdodam.audit.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_GRADE")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Grade {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String gradeTitle;
    @NotNull private int gradeStartNumber;
    @NotNull private int gradeEndNumber;
}
