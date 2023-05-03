package com.app.dodamdodam.entity.banner;

import com.app.dodamdodam.audit.Period;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @ToString
@Table(name = "TBL_BANNER_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BannerFile extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;



}
