package com.app.dodamdodam.service.member;

import com.app.dodamdodam.domain.InquiryDTO;
import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.domain.PurchaseBoardDTO;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.inquiry.Inquiry;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.banner.BannerApply;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.repository.banner.BannerRepository;
import com.app.dodamdodam.repository.board.free.FreeBoardRepository;
import com.app.dodamdodam.repository.board.purchase.PurchaseBoardRepository;
import com.app.dodamdodam.repository.board.recruitment.RecruitmentBoardRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.repository.point.PointRepository;
import com.app.dodamdodam.search.Inquiry.AdminInquirySearch;
import com.app.dodamdodam.search.member.AdminMemberSearch;
import com.app.dodamdodam.type.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private FreeBoardRepository freeBoardRepository;

    @Autowired
    private PurchaseBoardRepository purchaseBoardRepository;

    @Autowired
    private RecruitmentBoardRepository recruitmentBoardRepository;

    @Autowired
    private BannerRepository bannerRepository;

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

    /* 내가 작성한 판매게시글 개수 */
    @Override
    public Long getMyPurchaseBoardListCount(Long memberId) {
        return purchaseBoardRepository.findPurchaseBoardListCountByMemberId_QueryDSL(memberId);
    }

    /* 내가 작성한 모집게시글 개수 */
    @Override
    public Long getMyRecruitmentBoardListCount(Long memberId) {
        return recruitmentBoardRepository.findRecruitmentBoardListCountByMemberId_QueryDSL(memberId);
    }

    /* 내가 참가한 모집게시글 개수 */
    @Override
    public Long getMyRecruitmentedBoardListCount(Long memberId) {
        return recruitmentBoardRepository.findRecruitmentedBoardListCountByMemberId_QueryDSL(memberId);
    }

    /* 관리자 멤버 목록*/
    @Override 
    public Page<MemberDTO> showList(Pageable pageable) {
        Page<Member> memberPage = memberRepository.findAllMemberList_QueryDSL(PageRequest.of(1,10));
        List<MemberDTO> memberDTOS = memberPage.get().map(this::toMemberDTO).collect(Collectors.toList());

        return new PageImpl<>(memberDTOS, pageable, memberPage.getTotalElements());
    }

    @Override
    public MemberDTO getAdminMemberDetail(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return toMemberDTO(member.get());
    }


    /* 회원 비활성화 처리*/
    @Override
    public void setMemberStatusById(Long memberId) {
        memberRepository.findById(memberId).ifPresent(member -> member.setMemberStatus(MemberStatus.WITHDRAWAL));
    }

    /* 회원 정보 수정 */
    @Override
    public void setMemberInfoById(Long memberId, Member memberInfo) {
        memberRepository.findById(memberId).ifPresent(member -> {
            member.setMemberName(memberInfo.getMemberName());
            member.setMemberEmail(memberInfo.getMemberEmail());
            member.setMemberPhone(memberInfo.getMemberPhone());
            member.setAddress(memberInfo.getAddress());
        });
    }

    /* 비밀번호 변경 */
    @Override
    public void setMemberPasswordById(Long memberId, String password) {
        memberRepository.findById(memberId).ifPresent(member -> member.setMemberPassword(password));
    }

    /* 배너 신청 */
    @Override
    public void saveBannerApply(Long memberId, BannerApply bannerApply) {
        memberRepository.findById(memberId).ifPresent(member -> bannerApply.setMember(member));
        bannerRepository.save(bannerApply);
    }

    /* 내가 참가한 모집 게시글 전체 */
    @Override
    public List<RecruitmentBoard> getMyRecruitementedBoardList(Long memberId) {
        return recruitmentBoardRepository.findAllRecruitmentedBoardListByMemberId_QueryDSL(memberId);
    }

    /* 멤버 상태 변경*/
    @Override
    public void setMemberStatus(List<Long> ids, MemberStatus memberStatus) {
        for(Long id: ids){
            memberRepository.findById(id).ifPresent(member -> member.setMemberStatus(MemberStatus.WITHDRAWAL));
        }
    }

    /* 관리자 멤버 검색 */
    @Override
    public Page<MemberDTO> showMemberWithSearch_QueryDSL(Pageable pageable, AdminMemberSearch adminMemberSearch) {
        Page<Member> memberPage = memberRepository.findAdminMemberWithPaging_QueryDSL(adminMemberSearch, pageable);
        List<MemberDTO> adminMemberSearchDTOS = memberPage.getContent().stream()
                .map(this::toMemberDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(adminMemberSearchDTOS, pageable, memberPage.getTotalElements());
    }

}
