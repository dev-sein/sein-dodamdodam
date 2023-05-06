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

    @Override
    public List<RecruitmentBoard> findRecruitmentBoardListByMemberId(Pageable pageable, Long memberId) {
        return query.select(recruitmentBoard).from(recruitmentBoard).where(recruitmentBoard.member.id.eq(memberId)).orderBy(recruitmentBoard.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
    }
}
