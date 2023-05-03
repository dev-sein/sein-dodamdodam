package com.app.dodamdodam.entity;

import com.app.dodamdodam.audit.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "TBL_RECRUITMENT_BOARD")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitmentBoard extends Period {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String recruitmentTitle;
    @NotNull
    private String recruitmentSubtitle;
    @NotNull
    private LocalDate recruitmentDate;
    @NotNull
    private int recruitmentPeopleCount;
    @NotNull
    private String recruitmentContent;
    @NotNull
    private String recruitmentOpenChatting;
    @NotNull
    private String recruitmentPassword;
    @NotNull
    private String recruitmentStatus;
    @NotNull
    private String recruitmentAddress;
    @NotNull
    private String recruitmentAddressDetail;
}
