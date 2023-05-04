package com.app.dodamdodam.entity.member;

import com.app.dodamdodam.audit.Period;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@ToString
@Table(name = "TBL_CALENDAR")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Calendar extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;


}
