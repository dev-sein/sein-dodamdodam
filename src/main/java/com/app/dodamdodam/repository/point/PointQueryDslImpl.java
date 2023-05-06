package com.app.dodamdodam.repository.point;

import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.point.Point;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.free.QFreeBoard.freeBoard;
import static com.app.dodamdodam.entity.point.QPoint.point;

public class PointQueryDslImpl implements PointQueryDsl {
    @Autowired
    private JPAQueryFactory query;

    @Override
    public List<Point> findPointByMemberId(Long memberId) {
        return query.select(point).from(point).where(point.member.id.eq(memberId)).orderBy(point.id.desc()).fetch();
    }
}
