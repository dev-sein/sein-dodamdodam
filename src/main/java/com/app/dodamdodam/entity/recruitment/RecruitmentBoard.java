package com.app.dodamdodam.entity.recruitment;

import com.app.dodamdodam.entity.board.Board;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "TBL_RECRUITMENT_BOARD")
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class RecruitmentBoard extends Board {
//    @Id
//    @GeneratedValue
//    @EqualsAndHashCode.Include
//    private Long id;
//    @NotNull
//    private String recruitmentTitle;
    @NotNull
    private String recruitmentSubtitle;
    @NotNull
    private LocalDate recruitmentDate;
    @NotNull
    private int recruitmentPeopleCount;
//    @NotNull
//    private String recruitmentContent;
    @NotNull
    private String recruitmentOpenChatting;
    @NotNull
    private String recruitmentPassword;
    @ColumnDefault("'APPLYING'")
    @Enumerated(EnumType.STRING)
    @NotNull private RecruitmentType recruitmentStatus;
    @NotNull
    private String recruitmentAddress;
    @NotNull
    private String recruitmentAddressDetail;


    @OneToOne
    private RecruitmentLike recruitmentLike;
}
