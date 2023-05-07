package com.app.dodamdodam.repository.member;

import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.domain.QMemberDTO;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.member.QMember;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.entity.point.QPoint;
import com.app.dodamdodam.entity.recruitment.QRecruitmentBoard;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.app.dodamdodam.entity.member.QMember.member;
import static com.app.dodamdodam.entity.point.QPoint.point;
import static com.app.dodamdodam.entity.recruitment.QRecruitmentBoard.recruitmentBoard;

public class MemberQueryDslImpl implements MemberQueryDsl{
    @Autowired
    private JPAQueryFactory query;

    @Override
    public List<MemberDTO> findByMemberId(Long id) {
//        return query.select().from(member).where(member.id.eq(id))
        return query.select(new QMemberDTO(
                member.memberId, member.memberPassword, member.memberName, member.memberEmail, member.memberPhone,
                member.memberAddress, member.memberAddressDetail, member.memberStatus, member.memberPoint,
                member.participationCount, member.recruitments.size())).
                from(member).
                where(member.id.eq(id)).fetch();
//                groupBy(member.memberId, member.memberPassword, member.memberName, member.memberEmail,
//                        member.memberPhone, member.memberAddress, member.memberAddressDetail, member.memberStatus,
//                        member.memberPoint, member.participationCount).fetch();
    }



}
