package com.app.dodamdodam.repository.board.event.reply;

import com.app.dodamdodam.entity.event.EventReply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface EventReplyQueryDsl {
    // 댓글 저장 save
    // 댓글 수정 memberId (화면 쪽에서 검사하기 때문에 따로 만들 필요 x)
    // 댓글 삭제 memberId (화면 쪽에서 검사하기 때문에 따로 만들 필요 x)
    // 전체 조회 ( 페이징 )
    public Slice<EventReply> findAllByEventWithPaging_QueryDsl(Long eventBoardId, Pageable pageable);
    // 댓글 갯수
    public Long getReplyCount_QueryDsl(Long eventBoardId);
    // 삭제
    public void deleteByEventBoardId(Long eventBoardId);
 }
