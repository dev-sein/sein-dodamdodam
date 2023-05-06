package com.app.dodamdodam.repository.member;

import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.entity.point.QPoint;
import com.app.dodamdodam.entity.recruitment.QRecruitmentBoard;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.app.dodamdodam.entity.point.QPoint.point;
import static com.app.dodamdodam.entity.recruitment.QRecruitmentBoard.recruitmentBoard;

public class MemberQueryDslImpl implements MemberQueryDsl{
    @Autowired
    private JPAQueryFactory query;

    @Override
    public Member findByMemberId(Long id) {
        return null;
    }

    @Override
    public List<RecruitmentBoard> findRecruitmentBoardByMemberId(Long memberId) {
        return query.select(recruitmentBoard).from(recruitmentBoard).where(recruitmentBoard.member.id.eq(memberId)).orderBy(recruitmentBoard.id.desc()).fetch();
    }

    @Override
    public List<Point> findPointByMemberId(Long memberId) {
        return query.select(point).from(point).where(point.member.id.eq(memberId)).orderBy(point.id.desc()).fetch();
    }
}
