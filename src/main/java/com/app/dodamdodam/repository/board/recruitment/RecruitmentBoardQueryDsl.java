package com.app.dodamdodam.repository.board.recruitment;

import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.search.board.AdminRecruitmentSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecruitmentBoardQueryDsl {
//    세션에 담긴 id 값 받아와서 내가 작성한 모집 게시글 리스트 가져오기
    public Page<RecruitmentBoard> findRecruitmentBoardListByMemberId(Pageable pageable, Long memberId);

//    세션에 담긴 id 값 받아와서 내가 참여한 모집 게시글 리스트 가져오기
    public Page<RecruitmentBoard> findRecruitmentedBoardListByMemberId(Pageable pageable, Long memberId);

    //관리자 도담 게시판 검색
    public Page<RecruitmentBoard> findAdminRecruitmentBoardWithPaging_QueryDSL (AdminRecruitmentSearch adminRecruitmentSearch, Pageable pageable);
}
