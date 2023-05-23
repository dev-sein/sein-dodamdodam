package com.app.dodamdodam.entity.recruitment;

import com.app.dodamdodam.entity.file.BoardFile;
import com.app.dodamdodam.type.FileType;
import lombok.*;

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

    public RecruitmentFile(RecruitmentBoard recruitmentBoard) {
        this.recruitmentBoard = recruitmentBoard;
    }

    @Builder
    public RecruitmentFile(Long id, String fileOriginalName, String fileUuid, String filePath, FileType fileType, Long fileSize, RecruitmentBoard recruitmentBoard) {
        super(id, fileOriginalName, fileUuid, filePath, fileType, fileSize);
        this.recruitmentBoard = recruitmentBoard;
    }

    public void setRecruitmentBoard(RecruitmentBoard recruitmentBoard) {
        this.recruitmentBoard = recruitmentBoard;
    }
}
