package com.app.dodamdodam.repository.board.recruitment;

import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.search.board.AdminRecruitmentSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface RecruitmentBoardQueryDsl {
//    세션에 담긴 id 값 받아와서 내가 작성한 모집 게시글 리스트 가져오기
    public Page<RecruitmentBoard> findRecruitmentBoardListByMemberId_QueryDSL(Pageable pageable, Long memberId);

//    세션에 담긴 id 값 받아와서 내가 참여한 모집 게시글 리스트 가져오기
    public Page<RecruitmentBoard> findRecruitmentedBoardListByMemberId_QueryDSL(Pageable pageable, Long memberId);

//    모집글에 참가한 인원들 가져오기
    public RecruitmentBoard findRecruitmentedMembersByBoardId_QueryDSL(Long boardId);

//    내가 참여한 모집 게시글 전체 리스트 가져오기
    public List<RecruitmentBoard> findAllRecruitmentedBoardListByMemberId_QueryDSL(Long memberId);

//    내가 작성한 모집 게시글 전체 개수 가져오기
    public Long findRecruitmentBoardListCountByMemberId_QueryDSL(Long memberId);

//    내가 참여 모집 게시글 전체 개수 가져오기
    public Long findRecruitmentedBoardListCountByMemberId_QueryDSL(Long memberId);

//    관리자 도담 게시판 검색
    public Page<RecruitmentBoard> findAdminRecruitmentBoardWithPaging_QueryDSL (AdminRecruitmentSearch adminRecruitmentSearch, Pageable pageable);

//    관리자 도담 게시판 페이징
    public Page<RecruitmentBoard> findAllWithPaging(Pageable pageable);

//    내가 참여한 모집 게시글 날짜로 검색
    public List<RecruitmentBoard> findRecruitmentBoardListByMemberIdAndDate(Long memberId, LocalDate recruitmentDate);
}
