package com.app.dodamdodam.repository.board.free.like;

import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.search.FreeBoardSearch;
import com.app.dodamdodam.search.board.AdminFreeBoardSearch;
import com.app.dodamdodam.type.CategoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FreeBoardLikeQueryDsl {
//    boardId, memberId 받아와서 좋아요 취소하기
    public void deleteByBoardIdAndMemberId_QueryDSL(Long boardId, Long memberId);

//    boardId, memberId 받아와서 좋아요 누른 게시글인지 확인
    public boolean checkLikeByBoardIdAndMemberId_QueryDSL(Long boardId, Long memberId);
}
