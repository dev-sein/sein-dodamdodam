package com.app.dodamdodam.service.member;

import com.app.dodamdodam.domain.FreeBoardFileDTO;
import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.domain.RecruitmentBoardFileDTO;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.member.Grade;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.entity.banner.BannerApply;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.provider.UserDetail;
import com.app.dodamdodam.repository.banner.BannerRepository;
import com.app.dodamdodam.repository.board.free.FreeBoardRepository;
import com.app.dodamdodam.repository.board.purchase.PurchaseBoardRepository;
import com.app.dodamdodam.repository.board.recruitment.RecruitmentBoardRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.repository.point.PointRepository;
import com.app.dodamdodam.search.member.AdminMemberSearch;
import com.app.dodamdodam.type.MemberStatus;
import com.app.dodamdodam.type.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberServiceImpl implements MemberService/*, OAuth2UserService<OAuth2UserRequest, OAuth2User>*/ {

    private final HttpSession httpSession;
    private final MemberRepository memberRepository;
    private final PointRepository pointRepository;
    private final FreeBoardRepository freeBoardRepository;
    private final PurchaseBoardRepository purchaseBoardRepository;
    private final RecruitmentBoardRepository recruitmentBoardRepository;
    private final BannerRepository bannerRepository;

    /* 로그인 된 유저 정보 */
    @Override
    public MemberDTO getMemberInfo(Long memberId) {
        Optional<Member> foundMember = memberRepository.findById(memberId);
        if(foundMember.isPresent()){
            return toMemberDTO(foundMember.get());
        }
        return null;
    }
//    public Optional<Member> getMemberInfo(Long memberId) {
//        return memberRepository.findById(memberId);
//    }

    @Override
    public void join(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
        Optional<Member> optionalMember = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());

        memberDTO.setMemberPassword(passwordEncoder.encode(memberDTO.getMemberPassword()));
        memberDTO.setMemberRole(Role.MEMBER);
        memberDTO.setMemberStatus(MemberStatus.NORMAL);

        Member member;

        if (optionalMember.isPresent()) {
            Member foundMember = optionalMember.get();
            foundMember.setMemberId(memberDTO.getMemberId());
            foundMember.setMemberPassword(memberDTO.getMemberPassword());
            foundMember.setMemberName(memberDTO.getMemberName());
            foundMember.setAddress(memberDTO.getAddress());
            foundMember.setMemberRole(memberDTO.getMemberRole());
            foundMember.setMemberPhone(memberDTO.getMemberPhone());

            memberRepository.save(foundMember);
        } else {
            member = toMemberEntity(memberDTO);

            memberRepository.save(member);
        }



    }

    /* 아이디 중복 검사 */
    @Override
    public String checkMemberId(String memberId) {
        String result = null;

        Optional<Member> optionalMember = memberRepository.findMemberByMemberId_QueryDSL(memberId);
        if (!optionalMember.isPresent()){
            result = "available";
        }

        return result;
    }

    /* 이메일 중복 검사 */
    @Override
    public String checkMemberEmail(String memberEmail) {
        String result = null;

        Optional<Member> optionalMember = memberRepository.findByMemberEmail(memberEmail);
        if (!optionalMember.isPresent()){
            result = "available";
        }

        return result;
    }

    /* 이메일 중복 검사 */
    @Override
    public String checkMemberPhone(String memberPhone) {
        String result = null;

        Optional<Member> optionalMember = memberRepository.findByMemberPhone(memberPhone);
        if (!optionalMember.isPresent()){
            result = "available";
        }

        return result;
    }
//    @Override
//    public Optional<Member> getMemberByMemberEmail(String memberEmail){
//        return memberRepository.findByMemberEmail(memberEmail);
//    }

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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        화면에서 입력한 회원 아이디를 통해 조회된 정보 (로그인 검사에 사용된다.)
        log.info("loadUserByUsername 진입");
//        Member member = memberRepository.findMemberByMemberId_QueryDSL(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        log.info(username);
        Member member = memberRepository.findByMemberId(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

        log.info("==================================");
        log.info(String.valueOf(member.getId()));
        log.info(String.valueOf(member.getMemberId()));
        log.info(String.valueOf(member.getMemberPassword()));
        log.info(String.valueOf(member.getMemberEmail()));
        log.info(member.getMemberRole().toString());
        log.info(String.valueOf(member.getRoleKey()));
        log.info(member.getAddress().toString());
        log.info("==================================");

        return UserDetail.builder()
                .id(member.getId())
                .memberId(member.getMemberId())
                .memberPassword(member.getMemberPassword())
                .memberRole(member.getMemberRole())
                .build();
    }
    /* 멤버 상태 변경*/
    @Override
    public void setMemberStatus(List<Long> ids, MemberStatus memberStatus) {
        for(Long id: ids){
            memberRepository.findById(id).ifPresent(member -> member.setMemberStatus(MemberStatus.WITHDRAWAL));
        }
    }

    /* 캘린더 눌렀을 때 누른 날짜로 내가 참가한 모집게시글 리스트 가져오기 */
    @Override
    public List<RecruitmentBoardFileDTO> getRecruitmentBoardListByMemberIdAndDate(Long memberId, LocalDate recruitmentDate) {
        List<RecruitmentBoard> recruitmentBoards = recruitmentBoardRepository.findRecruitmentBoardListByMemberIdAndDate_QueryDSL(memberId, recruitmentDate);
        List<RecruitmentBoardFileDTO> recruitmentBoardFileDTOS = recruitmentBoards.stream().map(recruitmentBoard -> toRecruitmentBoardFileDto(recruitmentBoard)).collect(Collectors.toList());
        return recruitmentBoardFileDTOS;
    }

    /* 내가 참여한 모집 게시글 모집 날짜 리스트 가져오기 */
    @Override
    public List<LocalDate> getMyRecruimentDateByMemberId(Long memberId) {
        return recruitmentBoardRepository.findRecruimentDateByMemberId_QueryDSL(memberId);
    }

    /* 내 등급, 등급 명 가져오기 */
    @Override
    public Grade getMyGradeByMemberId(Long memberId) {
        return memberRepository.findMemberGradeByMemberId_QueryDSL(memberId);
    }

    /* 내 등급 가져오기 */
    @Override
    public String getMemberGrade(Long memberId) {
        return memberRepository.findAdminMemberDetail_QueryDSL(memberId);
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

    @Override
    public List<MemberDTO> getRecentMemberList() {
        List<MemberDTO> memberDTOS = memberRepository.findRecentMemberList_QueryDSL().stream().map(memberList -> toMemberDTO(memberList)).collect(Collectors.toList());
        return memberDTOS;
    }

}
