package com.app.dodamdodam.service.point;

import com.app.dodamdodam.domain.PointDTO;
import com.app.dodamdodam.domain.RecruitmentBoardFileDTO;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.repository.point.PointRepository;
import com.app.dodamdodam.search.point.AdminPointSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {
    private final PointRepository pointRepository;

    @Override
    public Page<PointDTO> showList(Pageable pageable) {
        Page<Point> pointPage = pointRepository.findAllWithPaging(pageable);
        List<PointDTO> pointDTOS = pointPage.get().map(this::toPointDTO).collect(Collectors.toList());

        return new PageImpl<>(pointDTOS, pageable, pointPage.getTotalElements());
    }

    /* 관리자 포인트 검색 */
    @Override
    public Page<PointDTO> showAdminPointWithSearch_QueryDSL(Pageable pageable, AdminPointSearch adminPointSearch) {
        Page<Point> pointPage = pointRepository.findPointMemberIdWithSearch_QueryDSL(adminPointSearch, pageable);
        List<PointDTO> pointDTOS = pointPage.getContent().stream()
                .map(this::toPointDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(pointDTOS, pageable, pointPage.getTotalElements());
    }


}
