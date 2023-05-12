package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.repository.point.PointRepository;
import com.app.dodamdodam.search.point.AdminPointSearch;
import com.app.dodamdodam.type.PointStatus;
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
        pointRepository.findPointByMemberId_QueryDSL(2L).stream().map(Point::toString).forEach(log::info);
    }

    @Test //관리자 포인트 멤버 정보 조회
    public void findPointMemberIdWithSearch(){
        AdminPointSearch pointSearch = new AdminPointSearch();
        pointSearch.setMemberId("test1234");
        Page<Point> pointPage = pointRepository.findPointMemberIdWithSearch_QueryDSL(pointSearch, PageRequest.of(0, 10));
        log.info("i"+pointPage.getContent());
    }

    /* 포인트 충전 */
    @Test
    public void saveTest(){
        Point point = new Point(50000, PointStatus.CHARGE);
        point.setMember(memberRepository.findById(2L).get());
        memberRepository.findById(2L).ifPresent(member -> {
            point.setMember(member);
            member.setMemberPoint(member.getMemberPoint() + 50000);
        });
        pointRepository.save( point);
    }

    /* 포인트 사용 */
    @Test
    public void saveUseTest(){
        Point point = new Point(50000, PointStatus.USE);
        point.setMember(memberRepository.findById(2L).get());
        memberRepository.findById(2L).ifPresent(member -> {
            point.setMember(member);
            member.setMemberPoint(member.getMemberPoint() - 50000);
        });
        pointRepository.save( point);
    }
}
