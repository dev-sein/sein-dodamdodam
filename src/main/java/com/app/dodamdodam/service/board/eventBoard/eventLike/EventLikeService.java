package com.app.dodamdodam.service.board.eventBoard.eventLike;

import org.springframework.stereotype.Service;

@Service
public interface EventLikeService {
    public void insertHeart(Long reviewBoardId, Long userId);
}
