package com.app.dodamdodam.repository.point;

import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.search.point.PointSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.free.QFreeBoard.freeBoard;
import static com.app.dodamdodam.entity.point.QPoint.point;

@Slf4j
public class PointQueryDslImpl implements PointQueryDsl {
    @Autowired
    private JPAQueryFactory query;

    @Override
    public List<Point> findPointByMemberId(Long memberId) {
        return query.select(point).from(point).where(point.member.id.eq(memberId)).orderBy(point.id.desc()).fetch();
    }

    @Override
    public Page<Point> findAllPointWithSearch(PointSearch pointSearch, Pageable pageable) {
        BooleanExpression pointAmountEq = pointSearch.getPointAmount() == null ? null : point.pointAmount.eq(pointSearch.getPointAmount());
        BooleanExpression pointStatusEq = pointSearch.getPointStatus() == null ? null : point.pointStatus.eq(pointSearch.getPointStatus());

        List<Point> points = query.select(point)
                .from(point)
                .where(pointAmountEq, pointStatusEq)
                .orderBy(point.id.desc())
                .offset(pageable.getOffset() - 1)
                .limit(pageable.getPageSize())
                .fetch();

        log.info(pageable.getOffset()+"");
        log.info(pageable.getPageSize()+"");
        log.info(points.toString());
        Long count = query.select(point.count()).from(point).fetchOne();

        return new PageImpl<>(points, pageable, count);
    }
}
