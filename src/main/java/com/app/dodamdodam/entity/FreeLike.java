package com.app.dodamdodam.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_FREE_BOARD_LIKE")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeLike {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
}
