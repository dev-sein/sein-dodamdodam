package com.app.dodamdodam.repository.reply.recruitmentReply;

import com.app.dodamdodam.entity.recruitment.RecruitmentReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

import static com.app.dodamdodam.entity.free.QFreeReply.freeReply;
import static com.app.dodamdodam.entity.recruitment.QRecruitmentReply.recruitmentReply;

public class RecruitmentReplyQueryDslImpl implements RecruitmentReplyQueryDsl {
    @Autowired
    private JPAQueryFactory query;

    //    boardId로 그 board에 달린 댓글 가져오기
    @Override
    public Page<RecruitmentReply> findRecruitmentRepliesByBoardId_QueryDSL(Pageable pageable, Long boardId) {
        List<RecruitmentReply> recruitmentReplies = query.select(recruitmentReply).from(recruitmentReply)
                .join(recruitmentReply.member).fetchJoin()
                .where(recruitmentReply.recruitmentBoard.id.eq(boardId))
                .orderBy(recruitmentReply.id.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(recruitmentReply.count()).from(recruitmentReply).where(recruitmentReply.recruitmentBoard.id.eq(boardId)).fetchOne();

        return new PageImpl<>(recruitmentReplies, pageable, count);
    }
}