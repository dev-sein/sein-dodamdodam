package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.repository.point.PointRepository;
import com.app.dodamdodam.search.point.AdminPointSearch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Rollback(false)
@Transactional
@Slf4j
public class PointRepositoryTests {
    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private MemberRepository memberRepository;

    /*포인트 목록 가져오기*/
    @Test
    public void findPointListByIdTest(){
        pointRepository.findPointByMemberId(2L).stream().map(Point::toString).forEach(log::info);
    }

//    @Test //포인트 페이징 조회
//    public void findAllPointWithSearchTest(){
//        AdminPointSearch pointSearch = new AdminPointSearch();
////        pointSearch.setPointAmount(10000);
//        pointSearch.setMemberId("test1");
//        Page<Point> pointPage = pointRepository.findPointWithSearch(pointSearch, PageRequest.of(0, 2));
////        pointPage.stream().map(point -> point.toString()).forEach(log::info);
////        log.info("========="+pointPage.map(pointPage::toString).forEach(log::info));
//        log.info("=========="+pointPage.getContent()); //==251만 가져옴, 351도 10000 포인트임
//        //size가 2일 때 251 반환, size가 1일때 351 반환으로 총 결과값이 두개이지만 하나씩만 반환됨.
//        log.info("=========="+pointPage.getTotalElements()); //==101 반환 (point 전체 행의 수)
//    }

//    @Test
//    public void findPointMemberIdWithSearch(){
//        AdminPointSearch pointSearch = new AdminPointSearch();
//        pointSearch.setMemberId("test3");
//        Page<Point> pointPage = pointRepository.findPointMemberIdWithSearch(pointSearch, PageRequest.of(0, 10));
//        log.info("id반환"+pointPage.getContent());
//        log.info("id반환"+pointPage.getTotalElements());
//    }
}
