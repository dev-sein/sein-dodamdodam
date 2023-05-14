package com.app.dodamdodam.service.member;

import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.repository.board.free.FreeBoardRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.repository.point.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private FreeBoardRepository freeBoardRepository;

    /* 로그인 된 유저 정보 */
    @Override
    public Optional<Member> getMemberInfo(Long memberId) {
        return memberRepository.findById(memberId);
    }

    /* 내 포인트 내역 */
    @Override
    public List<Point> getMyPointList(Long memberId) {
        return pointRepository.findPointByMemberId_QueryDSL(memberId);
    }

    /* 내가 작성한 자유 게시글 목록 */
    @Override
    public Page<FreeBoard> getMyFreeBoardList(Pageable pageable, Long memberId) {
        return freeBoardRepository.findFreeBoardListByMemberId_QueryDSL(pageable, memberId);
    }

    /* 내가 작성한 자유게시글 개수 */
    @Override
    public Long getMyFreeBoardListCount(Long memberId) {
        return freeBoardRepository.findFreeBoardListCountByMemberId_QueryDSL(memberId);
    }
}
