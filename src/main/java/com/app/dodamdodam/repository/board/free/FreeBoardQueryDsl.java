package com.app.dodamdodam.repository.board.free;

import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FreeBoardQueryDsl {
//    세션에 담긴 id 값 받아와서 내가 작성한 자유 게시글 리스트 가져오기
    public List<FreeBoard> findFreeBoardListByMemberId(Pageable pageable, Long memberId);
}
