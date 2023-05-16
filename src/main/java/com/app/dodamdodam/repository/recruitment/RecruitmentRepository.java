package com.app.dodamdodam.repository.recruitment;

import com.app.dodamdodam.entity.recruitment.Recruitment;
import com.app.dodamdodam.repository.board.recruitment.RecruitmentBoardQueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long>{
}
