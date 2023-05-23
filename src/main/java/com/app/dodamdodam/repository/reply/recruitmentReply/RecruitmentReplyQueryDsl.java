package com.app.dodamdodam.repository.reply.recruitmentReply;

import com.app.dodamdodam.entity.free.FreeReply;
import com.app.dodamdodam.entity.recruitment.RecruitmentReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruitmentReplyQueryDsl {
//    boardId로 댓글 리스트 가져오기
    public Page<RecruitmentReply> findRecruitmentRepliesByBoardId_QueryDSL(Pageable pageable, Long boardId);
}
