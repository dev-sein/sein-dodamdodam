package com.app.dodamdodam.repository.reply.recruitmentReply;

import com.app.dodamdodam.entity.recruitment.RecruitmentReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentReplyRepository extends JpaRepository<RecruitmentReply, Long>, RecruitmentReplyQueryDsl {
}