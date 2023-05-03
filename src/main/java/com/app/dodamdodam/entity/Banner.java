package com.app.dodamdodam.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@ToString
@Table(name = "TBL_BANNER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Banner {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private LocalDate bannerPeriod;
    private String bannerStatus;

}
