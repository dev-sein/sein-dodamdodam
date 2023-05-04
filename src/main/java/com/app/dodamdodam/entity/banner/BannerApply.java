package com.app.dodamdodam.entity.banner;

import com.app.dodamdodam.audit.Period;
import com.app.dodamdodam.entity.member.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@ToString
@Table(name = "TBL_BANNER_APPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BannerApply extends Period {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private LocalDate bannerRegisterDate;
    private int period;
    private String bannerStatus;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}