package com.app.dodamdodam.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @ToString
@Table(name = "TBL_RANDOM_KEY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RandomKey {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private Long randomKey;

}
