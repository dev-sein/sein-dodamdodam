package com.app.dodamdodam.service.point;

import com.app.dodamdodam.domain.PointDTO;
import com.app.dodamdodam.entity.point.Point;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Service
public interface PointService {
    //관리자 : 포인트 목록
    public Page<PointDTO> showList(Pageable pageable);

    default PointDTO toPointDTO(Point point){
    return PointDTO.builder().id(point.getId()).memberId(point.getMember().getMemberId()).
            memberName(point.getMember().getMemberName()).pointAmount(point.getPointAmount()).
            pointStatus(point.getPointStatus()).createdDate(point.getCreatedDate()).updatedDate(point.getUpdatedDate()).build();
    }
}
