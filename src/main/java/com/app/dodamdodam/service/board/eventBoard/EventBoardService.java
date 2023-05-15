package com.app.dodamdodam.service.board.eventBoard;

import com.app.dodamdodam.domain.FreeBoardFileDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventBoardService {
    /* 이벤트 정보 게시글 전체 목록 */
    public List<FreeBoardFileDTO> getAllFreeBoards(Pageable pageable);

}