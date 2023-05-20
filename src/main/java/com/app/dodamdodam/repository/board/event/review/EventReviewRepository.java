package com.app.dodamdodam.repository.board.event.review;

import com.app.dodamdodam.entity.event.EventReview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventReviewRepository extends JpaRepository<EventReview, Long>, EventReviewQueryDsl {
    //    최신순
    @Query("select e from EventReview e join e.member where e.id = :id order by e.createdDate desc")
    public Slice<EventReview> findAllByEventReview(Pageable pageable, Long id);

}
