package com.app.dodamdodam.service.board.fileBoard;

import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.type.CategoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FreeBoardService {
    /* 자유 게시글 전체 목록 */
    public Page<FreeBoard> getAllFreeBoards(Pageable pageable);

    /* 자유 게시글 카테고리 별 분류해서 가져오기 */
    public Page<FreeBoard> getFreeBoardsByCategoryType(CategoryType categoryType, Pageable pageable);

    /* 내가 작성한 자유게시글 목록 가져오기(memberId로 자유게시글 조회) */
    public Page<FreeBoard> getFreeBoardsByMemberId(Pageable pageable, Long memberId);

    /* 자유 게시글 상세 */
    public Optional<FreeBoard> getFreeBoardById(Long boardId);

    /* 자유 게시글 수정 */
    public void updateFreeBoard(FreeBoard freeBoard);

    /* 자유 게시글 삭제 */
    public void deleteFreeBoard(FreeBoard freeBoard);


}
