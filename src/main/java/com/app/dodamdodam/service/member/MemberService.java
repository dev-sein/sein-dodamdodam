package com.app.dodamdodam.service.member;

import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.entity.banner.BannerApply;
import com.app.dodamdodam.entity.embeddable.Address;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MemberService {
    /* 로그인 된 유저 정보 가져오기 */
    public Optional<Member> getMemberInfo(Long memberId);

    /* 내 포인트 내역 */
    public List<Point> getMyPointList(Long memberId);

    /* 내가 작성한 자유 게시글 목록 */
    public Page<FreeBoard> getMyFreeBoardList(Pageable pageable, Long memberId);

    /* 내가 작성한 자유 게시글 개수 */
    public Long getMyFreeBoardListCount(Long memberId);

    /* 내가 작성한 판매 게시글 개수 */
    public Long getMyPurchaseBoardListCount(Long memberId);

    /* 내가 작성한 모집 게시글 개수 */
    public Long getMyRecruitmentBoardListCount(Long memberId);

    /* 내가 참가한 모집 게시글 개수*/
    public Long getMyRecruitmentedBoardListCount(Long memberId);

    /* 내 현재 등급 */

    /* 회원 비활성화 처리*/
    public void setMemberStatusById(Long memberId);

    /* 내 정보 수정 */
    public void setMemberInfoById(Long memberId, Member memberInfo);

    /* 비밀번호 변경 */
    public void setMemberPasswordById(Long memberId, String password);

    /* 배너신청 */
    public void saveBannerApply(Long memberId, BannerApply bannerApply);

    /* 캘린더에 일정 띄우기(내가 참가한 모집게시글 전체) */
    public List<RecruitmentBoard> getMyRecruitementedBoardList(Long memberId);
    
    
    /* 관리자 멤버 목록 */
    public Page<MemberDTO> showList(Pageable pageable);

    default MemberDTO toMemberDTO(Member member){
        return MemberDTO.builder().id(member.getId()).memberId(member.getMemberId()).memberEmail(member.getMemberEmail())
            .memberName(member.getMemberName()).memberPhone(member.getMemberPhone()).memberPoint(member.getMemberPoint())
            .memberStatus(member.getMemberStatus()).address(member.getAddress())
                .memberPassword(member.getMemberPassword()).createdDate(member.getCreatedDate()).participationCount(member.getParticipationCount())
                .build();
    }

    default Member toMemberEntity(MemberDTO memberDTO){
        return Member.builder()
                .id(memberDTO.getId())
                .memberEmail(memberDTO.getMemberEmail())
                .memberName(memberDTO.getMemberName())
                .memberId(memberDTO.getMemberId())
                .memberPassword(memberDTO.getMemberPassword())
                .memberPhone(memberDTO.getMemberPhone())
                .address(memberDTO.getAddress())
                .build();
    }
}
