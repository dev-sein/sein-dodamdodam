package com.app.dodamdodam.entity.event;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@ToString
@Table(name = "TBL_APPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventLike {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

}
