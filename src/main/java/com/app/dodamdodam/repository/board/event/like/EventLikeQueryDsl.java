package com.app.dodamdodam.repository.board.event.like;

import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.event.EventLike;
import com.app.dodamdodam.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventLikeQueryDsl {
    public Member findMemberByBoardLike(Long eventBoardId, Long memberId);

    public Long getEventLikeCount(Long eventBoardId);

    public void deleteByMemberIdAndEventBoardId(Long eventBoardId, Long memberId);

    public Page<EventLike> findByLikeMemberIdWithPaging_QueryDsl(Pageable pageable, Long memberId);

    //    포스트 객체로 포스트 파일을 찾아내서 리스트로 반환하는 메소드
    public boolean findByEventBoardAndMember(Long memberId, Long boardId);

    //    포스트 객체로 포스트 파일을 찾아내서 전체 삭제하는 메소드
    public void deleteByEventBoardAndMember(Long memberId, Long boardId);

    //    전체 라이크 삭제하는 메소드
    public void deleteByEventBoard(EventBoard eventBoard);

    //    전체 라이크 리스트 반환하는 메소드
    public List<EventLike> findByEventBoard(EventBoard eventBoard);


}
