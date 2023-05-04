package com.app.dodamdodam.entity.recruitment;

import com.app.dodamdodam.audit.Period;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "TBL_PURCHASE_REVIEW")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitmentReply extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String replyContent;


    @ManyToOne
    @JoinColumn(name = "PURCHASE_BOARD_ID")
    private RecruitmentBoard recruitmentBoard;

}
