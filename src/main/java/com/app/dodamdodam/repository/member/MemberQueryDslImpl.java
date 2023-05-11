package com.app.dodamdodam.repository.member;

import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.domain.QMemberDTO;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.member.Grade;
import com.app.dodamdodam.entity.member.QGrade;
import com.app.dodamdodam.entity.member.QMember;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.entity.point.QPoint;
import com.app.dodamdodam.entity.recruitment.QRecruitmentBoard;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.search.member.AdminMemberSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.dodamdodam.entity.free.QFreeBoard.freeBoard;
import static com.app.dodamdodam.entity.member.QGrade.grade;
import static com.app.dodamdodam.entity.member.QMember.member;
import static com.app.dodamdodam.entity.point.QPoint.point;
import static com.app.dodamdodam.entity.recruitment.QRecruitmentBoard.recruitmentBoard;

public class MemberQueryDslImpl implements MemberQueryDsl{
    @Autowired
    private JPAQueryFactory query;

    @Override
    public List<MemberDTO> findByMemberId(Long id) {
        return null;
//        return query.select(new QMemberDTO(
//                member.memberId, member.memberPassword, member.memberName, member.memberEmail, member.memberPhone,
//                member.memberAddress, member.memberAddressDetail, member.memberStatus, member.memberPoint,
//                member.participationCount, member.recruitments.size())).
//                from(member).
//                where(member.id.eq(id)).fetch();

//                groupBy(member.memberId, member.memberPassword, member.memberName, member.memberEmail,
//                        member.memberPhone, member.memberAddress, member.memberAddressDetail, member.memberStatus,
//                        member.memberPoint, member.participationCount).fetch();
    }

    @Override
    public String findMemberIdByMemberEmail(String memberEmail) {
        return query.select(member.memberId).from(member).where(member.memberEmail.eq(memberEmail)).fetchOne();
    }

    @Override
    public boolean findCheckMemberIdByMemberEmail(String memberEmail) {

        String result = null;
        result = query.select(member.memberId).from(member).where(member.memberEmail.eq(memberEmail)).fetchOne();

        if (result != null){
            return false;
        }

        return true;
    }

    @Override //관리자 멤버 검색
    public Page<Member> findAdminMemberWithPaging_QueryDSL(AdminMemberSearch adminMemberSearch, Pageable pageable) {
        BooleanExpression memberNameEq = adminMemberSearch.getMemberName() == null ? null : member.memberName.eq(adminMemberSearch.getMemberName());
        BooleanExpression memberEmailEq = adminMemberSearch.getMemberEmail() == null ? null : member.memberEmail.eq(adminMemberSearch.getMemberEmail());
        BooleanExpression memberPhoneEq = adminMemberSearch.getMemberPhone() == null ? null : member.memberPhone.eq(adminMemberSearch.getMemberPhone());
        BooleanExpression memberStatusEq = adminMemberSearch.getMemberStatus() == null ? null : member.memberStatus.eq(adminMemberSearch.getMemberStatus());

        List<Member> adminMembes = query.select(member)
                .from(member)
                .where(memberEmailEq, memberPhoneEq, memberNameEq, memberStatusEq)
                .orderBy(member.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(member.count()).from(member).fetchOne();

        return new PageImpl<>(adminMembes, pageable, count);
    }

    @Override
    public String findAdminMemberDetail_QueryDSL(Long memberId) {
//        query.select(grade.gradeTitle).from(member, grade)
//                .where(member.participationCount.between(grade.gradeStartNumber, grade.gradeEndNumber))
//                .fetchOne();
//


        return   query.select(grade.gradeTitle).from(grade).join(member)
                .on(member.participationCount.between(grade.gradeStartNumber, grade.gradeEndNumber))
                .fetchOne();
    }
}
