package com.app.dodamdodam.repository.point;

import com.app.dodamdodam.entity.point.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long>, PointQueryDsl {
}
