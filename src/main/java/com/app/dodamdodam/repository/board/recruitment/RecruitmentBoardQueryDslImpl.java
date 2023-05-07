package com.app.dodamdodam.repository.board.recruitment;

import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.recruitment.QRecruitmentBoard;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.repository.board.free.FreeBoardQueryDsl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.free.QFreeBoard.freeBoard;
import static com.app.dodamdodam.entity.recruitment.QRecruitmentBoard.recruitmentBoard;

public class RecruitmentBoardQueryDslImpl implements RecruitmentBoardQueryDsl {
    @Autowired
    private JPAQueryFactory query;

    /* 내가 작성한 모집게시글 목록 가져오기*/
    @Override
    public List<RecruitmentBoard> findRecruitmentBoardListByMemberId(Pageable pageable, Long memberId) {
        return query.select(recruitmentBoard).from(recruitmentBoard).where(recruitmentBoard.member.id.eq(memberId)).orderBy(recruitmentBoard.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
    }
    
    /* 내가 참가한 모집게시글 목록 가져오기*/
    @Override
    public List<RecruitmentBoard> findRecruitmentedBoardListByMemberId(Pageable pageable, Long memberId) {
        return query.select(recruitmentBoard).from(recruitmentBoard).where(recruitmentBoard.recruitments.any().member.id.eq(memberId)).orderBy(recruitmentBoard.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
    }
}
