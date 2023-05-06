package com.app.dodamdodam.repository.point;

import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.point.Point;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PointQueryDsl {
//    memberId로 point 내역 조회
    public List<Point> findPointByMemberId(Long memberId);
}
