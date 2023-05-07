package com.app.dodamdodam.entity.purchase;

import com.app.dodamdodam.audit.Period;
import com.app.dodamdodam.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "TBL_PURCHASE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Purchase extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;



}
