package com.app.dodamdodam.repository.board.free.like;

import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.free.FreeLike;
import com.app.dodamdodam.repository.board.free.FreeBoardQueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardLikeRepository extends JpaRepository<FreeLike, Long>, FreeBoardLikeQueryDsl{
}
