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


    @OneToOne
    private Product product;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;



}
