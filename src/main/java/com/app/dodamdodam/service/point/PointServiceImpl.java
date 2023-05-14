package com.app.dodamdodam.service.point;

import com.app.dodamdodam.domain.PointDTO;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.repository.point.PointRepository;
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
        Page<Point> pointPage = pointRepository.findAllWithPaging(PageRequest.of(1, 10));
        List<PointDTO> pointDTOS = pointPage.get().map(this::toPointDTO).collect(Collectors.toList());

        return new PageImpl<>(pointDTOS, pageable, pointPage.getTotalElements());
    }


}
