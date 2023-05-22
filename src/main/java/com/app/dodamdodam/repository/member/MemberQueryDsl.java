package com.app.dodamdodam.repository.member;

import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.search.member.AdminMemberSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MemberQueryDsl {
//    세션에 담긴 id 값으로 유저 정보 받아오기
    public List<MemberDTO> findByMemberId(Long id);

////    세션에 담긴 id 값 받아와서 내가 작성한 모집 게시글 리스트 가져오기
//    public List<RecruitmentBoard> findRecruitmentBoardByMemberId(Long memberId);
//
////    memberId로 point 내역 조회
//    public List<Point> findPointByMemberId(Long memberId);

//  이메일로 아이디 찾기
    public String findMemberIdByMemberEmail_QueryDSL(String memberEmail);

//  아이디 중복검사
    public boolean findCheckMemberIdByMemberEmail_QueryDSL(String memberEmail);

    //    로그인 후 회원 정보 조회
    public Optional<Member> findMemberByMemberId_QueryDSL(String memberId);

//    이메일로 회원 조회
    public Optional<Member> findMemberByMemberEmail_QueryDSL(String memberEmail);


//  관리자 멤버 게시판 검색 조회
    public Page<Member> findAdminMemberWithPaging_QueryDSL(AdminMemberSearch adminMemberSearch, Pageable pageable);

//  관리자 멤버 상세 조회(뱃지)
    public String findAdminMemberDetail_QueryDSL(Long memberId);

//  관리자 홈
    public Page<Member> findAdminMemberAllOrderByIdDesc();

//  관리자 회원 목록 가져오기
    public Page<Member> findAllMemberList_QueryDSL(Pageable pageable);

    /* 추가 */
}


