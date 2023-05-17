package com.app.dodamdodam.repository.member;

import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.inquiry.Inquiry;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.search.member.AdminMemberSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.free.QFreeBoard.freeBoard;
import static com.app.dodamdodam.entity.inquiry.QInquiry.inquiry;
import static com.app.dodamdodam.entity.member.QGrade.grade;
import static com.app.dodamdodam.entity.member.QMember.member;

public class MemberQueryDslImpl implements MemberQueryDsl{
    @Autowired
    private JPAQueryFactory query;

    @Override
    public List<MemberDTO> findByMemberId(Long id) {
        return null;
    }

    /* 이메일로 회원 아이디 조회(메일로 쏴줘야 함) */
    @Override
    public String findMemberIdByMemberEmail_QueryDSL(String memberEmail) {
        return query.select(member.memberId).from(member).where(member.memberEmail.eq(memberEmail)).fetchOne();
    }

    @Override
    public boolean findCheckMemberIdByMemberEmail_QueryDSL(String memberEmail) {

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

    @Override //관리자 멤버-등급 조회
    public String findAdminMemberDetail_QueryDSL(Long memberId) {
        return query.select(grade.gradeTitle).from(member).join(grade).fetchJoin()
                .on(member.participationCount.between(grade.gradeStartNumber, grade.gradeEndNumber))
                .where(member.id.eq(memberId))
                .fetchOne();
    }

    @Override
    public Page<Member> findAdminMemberAllOrderByIdDesc() {
        return null;
    }

    @Override //관리자 멤버 목록 조회
    public Page<Member> findAllMemberList_QueryDSL(Pageable pageable) {
        List<Member> members = query.select(member)
                .from(member)
                .orderBy(member.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(member.count())
                .from(member)
                .fetchOne();

        return new PageImpl<>(members, pageable, count);
    }


}
