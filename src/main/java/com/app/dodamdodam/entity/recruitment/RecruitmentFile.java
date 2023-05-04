package com.app.dodamdodam.entity.recruitment;

import com.app.dodamdodam.entity.file.BoardFile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true)
@Table(name = "TBL_RECRUITMENT_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitmentFile extends BoardFile {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECRUITMENT_BOARD_ID")
    private RecruitmentBoard recruitmentBoard;
}
