package com.app.dodamdodam.repository.board.recruitment;

import com.app.dodamdodam.entity.recruitment.RecruitmentFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentFileRepository extends JpaRepository<RecruitmentFile, Long>, RecruitmentFileQueryDsl {
}
