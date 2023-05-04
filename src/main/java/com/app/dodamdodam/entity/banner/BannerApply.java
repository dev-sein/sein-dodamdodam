package com.app.dodamdodam.entity.banner;

import com.app.dodamdodam.audit.Period;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.type.BannerType;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@ToString(exclude = "member")
@Table(name = "TBL_BANNER_APPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class BannerApply extends Period {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private LocalDate bannerRegisterDate;
    private int period;
    // 수락대기, 수락, 수락거절
    @ColumnDefault("'APPLYING'")
    @Enumerated(EnumType.STRING)
    private BannerType bannerStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}