package com.app.dodamdodam.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@ToString
@Table(name = "TBL_POINT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private Integer pointAmount;
    @NotNull private String pointStatus;

}
