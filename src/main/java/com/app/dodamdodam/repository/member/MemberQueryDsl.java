package com.app.dodamdodam.repository.member;

import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;

import java.util.List;

public interface MemberQueryDsl {
//    세션에 담긴 id 값으로 유저 정보 받아오기
    public Member findByMemberId(Long id);

//    세션에 담긴 id 값 받아와서 내가 작성한 모집 게시글 리스트 가져오기
    public List<RecruitmentBoard> findRecruitmentBoardByMemberId(Long memberId);

//    memberId로 point 내역 조회
    public List<Point> findPointByMemberId(Long memberId);
}
