package com.app.dodamdodam.entity;

import com.app.dodamdodam.audit.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "TBL_RECRUITMENT_FILE")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitmentFile extends Period {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String recruitmentFileOriginalName;
    @NotNull
    private String recruitmentFileUuid;
    @NotNull
    private String recruitmentFilePath;
    @NotNull
    private String recruitmentFileSize;


}
