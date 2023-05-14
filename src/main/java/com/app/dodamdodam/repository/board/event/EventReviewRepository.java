package com.app.dodamdodam.repository.board.event;

import com.app.dodamdodam.entity.event.EventReview;
import com.app.dodamdodam.repository.board.event.EventReviewQueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventReviewRepository extends JpaRepository<EventReview, Long>, EventReviewQueryDsl {
}
