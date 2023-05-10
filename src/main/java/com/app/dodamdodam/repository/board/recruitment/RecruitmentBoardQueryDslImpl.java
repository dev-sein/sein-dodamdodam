package com.app.dodamdodam.repository.board.recruitment;

import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.recruitment.QRecruitmentBoard;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.repository.board.free.FreeBoardQueryDsl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.free.QFreeBoard.freeBoard;
import static com.app.dodamdodam.entity.purchase.QPurchaseBoard.purchaseBoard;
import static com.app.dodamdodam.entity.recruitment.QRecruitmentBoard.recruitmentBoard;
public class RecruitmentBoardQueryDslImpl implements RecruitmentBoardQueryDsl {
    @Autowired
    private JPAQueryFactory query;

    /* 내가 작성한 모집게시글 목록 가져오기*/
    @Override
    public Page<RecruitmentBoard> findRecruitmentBoardListByMemberId(Pageable pageable, Long memberId) {
//        List<PurchaseBoard> purchaseBoards = query.select(purchaseBoard).from(purchaseBoard).where(purchaseBoard.member.id.eq(memberId)).orderBy(purchaseBoard.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
//        Long count = query.select(purchaseBoard.count()).from(purchaseBoard).where(purchaseBoard.member.id.eq(memberId)).fetchOne();
//
//        return new PageImpl<>(purchaseBoards, pageable, count);

        List<RecruitmentBoard> recruitmentBoards = query.select(recruitmentBoard).from(recruitmentBoard).where(recruitmentBoard.member.id.eq(memberId)).orderBy(recruitmentBoard.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        Long count = query.select(recruitmentBoard.count()).from(recruitmentBoard).where(recruitmentBoard.member.id.eq(memberId)).fetchOne();

        return new PageImpl<>(recruitmentBoards, pageable, count);
    }

    /* 내가 참가한 모집게시글 목록 가져오기*/
    @Override
    public Page<RecruitmentBoard> findRecruitmentedBoardListByMemberId(Pageable pageable, Long memberId) {

        query.select(recruitmentBoard).from(recruitmentBoard).where(recruitmentBoard.recruitments.any().member.id.eq(memberId)).orderBy(recruitmentBoard.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();

        List<RecruitmentBoard> recruitmentBoards = query.select(recruitmentBoard).from(recruitmentBoard).where(recruitmentBoard.recruitments.any().member.id.eq(memberId)).orderBy(recruitmentBoard.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        Long count = query.select(recruitmentBoard.count()).from(recruitmentBoard).where(recruitmentBoard.recruitments.any().member.id.eq(memberId)).fetchOne();

        return new PageImpl<>(recruitmentBoards, pageable, count);
    }
}
