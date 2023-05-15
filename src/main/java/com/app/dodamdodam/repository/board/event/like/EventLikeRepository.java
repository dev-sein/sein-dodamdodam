package com.app.dodamdodam.repository.board.event.like;

import com.app.dodamdodam.entity.event.EventLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventLikeRepository extends JpaRepository<EventLike, Long>, EventLikeQueryDsl {
}
