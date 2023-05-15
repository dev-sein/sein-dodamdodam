package com.app.dodamdodam.repository.board.recruitment;

import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.recruitment.QRecruitmentBoard;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.repository.board.free.FreeBoardQueryDsl;
import com.app.dodamdodam.search.board.AdminRecruitmentSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.recruitment.QRecruitmentBoard.recruitmentBoard;
public class RecruitmentBoardQueryDslImpl implements RecruitmentBoardQueryDsl {
    @Autowired
    private JPAQueryFactory query;

    /* 내가 작성한 모집게시글 목록 가져오기*/
    @Override
    public Page<RecruitmentBoard> findRecruitmentBoardListByMemberId_QueryDSL(Pageable pageable, Long memberId) {
//        List<PurchaseBoard> purchaseBoards = query.select(purchaseBoard).from(purchaseBoard).where(purchaseBoard.member.id.eq(memberId)).orderBy(purchaseBoard.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
//        Long count = query.select(purchaseBoard.count()).from(purchaseBoard).where(purchaseBoard.member.id.eq(memberId)).fetchOne();
//
//        return new PageImpl<>(purchaseBoards, pageable, count);

        List<RecruitmentBoard> recruitmentBoards = query.select(recruitmentBoard).from(recruitmentBoard)
                .join(recruitmentBoard.member).fetchJoin()
                .leftJoin(recruitmentBoard.recruitmentFiles).fetchJoin()
//                .leftJoin(recruitmentBoard.recruitments).fetchJoin()  // join 3개는 안됨 따로 쿼리 추가로 작성
                .where(recruitmentBoard.member.id.eq(memberId))
                .orderBy(recruitmentBoard.id.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();


        Long count = query.select(recruitmentBoard.count()).from(recruitmentBoard).where(recruitmentBoard.member.id.eq(memberId)).fetchOne();

        return new PageImpl<>(recruitmentBoards, pageable, count);
    }

    /* 내가 참가한 모집게시글 목록 가져오기*/
    @Override
    public Page<RecruitmentBoard> findRecruitmentedBoardListByMemberId_QueryDSL(Pageable pageable, Long memberId) {

        query.select(recruitmentBoard).from(recruitmentBoard)
                .where(recruitmentBoard.recruitments.any().member.id.eq(memberId))
                .join(recruitmentBoard.member).fetchJoin()
                .leftJoin(recruitmentBoard.recruitmentFiles).fetchJoin()
                .orderBy(recruitmentBoard.id.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();

        List<RecruitmentBoard> recruitmentBoards = query.select(recruitmentBoard).from(recruitmentBoard).where(recruitmentBoard.recruitments.any().member.id.eq(memberId)).orderBy(recruitmentBoard.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        Long count = query.select(recruitmentBoard.count()).from(recruitmentBoard).where(recruitmentBoard.recruitments.any().member.id.eq(memberId)).fetchOne();

        return new PageImpl<>(recruitmentBoards, pageable, count);
    }

    /* 내가 작성한 모집글에 참가한 인원들(boardId로 참가한 인원들 확인) */
    @Override
    public RecruitmentBoard findRecruitmentBoardById_QueryDSL(Long boardId) {
        return query.select(recruitmentBoard).from(recruitmentBoard)
                .join(recruitmentBoard.member).fetchJoin()
                .leftJoin(recruitmentBoard.recruitments).fetchJoin()
                .where(recruitmentBoard.id.eq(boardId))
                .fetchOne();
    }

    /* 내가 참여한 모집게시글 전체 가져오기 */
    @Override
    public List<RecruitmentBoard> findAllRecruitmentedBoardListByMemberId_QueryDSL(Long memberId) {
        return query.select(recruitmentBoard).from(recruitmentBoard)
                .where(recruitmentBoard.recruitments.any().member.id.eq(memberId))
                .leftJoin(recruitmentBoard.recruitmentFiles).fetchJoin()
                .join(recruitmentBoard.member).fetchJoin()
                .orderBy(recruitmentBoard.id.desc())
                .fetch();
    }

    /* 내가 작성한 모집게시글 개수 가져오기 */
    @Override
    public Long findRecruitmentBoardListCountByMemberId_QueryDSL(Long memberId) {
        return query.select(recruitmentBoard.count()).from(recruitmentBoard)
                .where(recruitmentBoard.member.id.eq(memberId))
                .fetchOne();
    }

    @Override
    public Long findRecruitmentedBoardListCountByMemberId_QueryDSL(Long memberId) {
        return query.select(recruitmentBoard.count()).from(recruitmentBoard)
                .where(recruitmentBoard.recruitments.any().member.id.eq(memberId))
                .fetchOne();
    }

    //관리자 모집 게시판 검색
    @Override
    public Page<RecruitmentBoard> findAdminRecruitmentBoardWithPaging_QueryDSL(AdminRecruitmentSearch adminRecruitmentSearch, Pageable pageable) {
        BooleanExpression boardTitleEq = adminRecruitmentSearch.getBoardTitle() == null ? null : recruitmentBoard.boardTitle.eq(adminRecruitmentSearch.getBoardTitle());
        BooleanExpression memberNameEq = adminRecruitmentSearch.getMemberName() == null ? null : recruitmentBoard.member.memberName.eq(adminRecruitmentSearch.getMemberName());
        BooleanExpression recruitmentAddressEq = adminRecruitmentSearch.getRecruitmentAddress() == null ? null : recruitmentBoard.recruitmentAddress.eq(adminRecruitmentSearch.getRecruitmentAddress());

        List<RecruitmentBoard> recruitmentBoards = query.select(recruitmentBoard)
                .from(recruitmentBoard)
                .where(boardTitleEq, memberNameEq, recruitmentAddressEq)
                .orderBy(recruitmentBoard.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(recruitmentBoard.count()).from(recruitmentBoard).fetchOne();

        return new PageImpl<>(recruitmentBoards, pageable, count);
    }

    @Override
    public Page<RecruitmentBoard> findAllWithPaging(Pageable pageable) {
            List<RecruitmentBoard> recruitmentBoards = query.select(recruitmentBoard)
                    .from(recruitmentBoard)
                    .orderBy(recruitmentBoard.id.desc())
                    .offset(pageable.getOffset() -1)
                    .limit(pageable.getPageSize())
                    .fetch();
            Long count = query.select(recruitmentBoard.count())
                    .from(recruitmentBoard)
                    .fetchOne();

            return new PageImpl<>(recruitmentBoards, pageable, count);

    }
}
