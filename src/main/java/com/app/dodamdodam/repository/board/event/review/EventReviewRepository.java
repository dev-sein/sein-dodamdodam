package com.app.dodamdodam.repository.board.event.review;

import com.app.dodamdodam.entity.event.EventReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventReviewRepository extends JpaRepository<EventReview, Long>, EventReviewQueryDsl {
}
