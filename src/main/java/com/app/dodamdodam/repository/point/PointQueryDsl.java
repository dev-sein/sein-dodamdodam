package com.app.dodamdodam.repository.point;

import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.search.point.AdminPointSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PointQueryDsl {
//    memberId로 point 내역 조회
    public List<Point> findPointByMemberId(Long memberId);

//    어드민-포인트 페이지 검색
    public Page<Point> findPointWithSearch(AdminPointSearch pointSearch, Pageable pageable);

//  어드민-포인트 아이디 검색(???) 수정중 
    public Page<Point> findPointMemberIdWithSearch(AdminPointSearch pointSearch, Pageable pageable);
}
