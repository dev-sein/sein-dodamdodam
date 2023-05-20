package com.app.dodamdodam.service.banner;

import com.app.dodamdodam.domain.*;
import com.app.dodamdodam.entity.banner.BannerApply;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.search.banner.AdminBannerSearch;
import com.app.dodamdodam.search.board.AdminFreeBoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BannerApplyService {
    //관리자 : 배너 신청 목록
    public Page<BannerDTO> showList(Pageable pageable);

    //관리자 : 배너 상세 신청 내역
    public BannerDTO getAdminBannerDetail(Long id);

    /* 관리자 자유게시판 검색 */
    public Page<BannerDTO> showAdminBannerWithSearch_QueryDSL(Pageable pageable, AdminBannerSearch adminBannerSearch);

    //관리자 : 배너 삭제
    public void deleteAdminBannerList(List<Long> bannerApplyIds);

    default BannerDTO toBannerDTO(BannerApply bannerApply){
        return BannerDTO.builder().id(bannerApply.getId()).bannerRegisterDate(bannerApply.getBannerRegisterDate())
                .bannerStatus(bannerApply.getBannerStatus()).createdDate(bannerApply.getCreatedDate()).updatedDate(bannerApply.getUpdatedDate())
                .period(bannerApply.getPeriod()).memberDTO(toMemberDTO(bannerApply.getMember())).build();
    }

    default MemberDTO toMemberDTO(Member member){
        return MemberDTO.builder().id(member.getId())
                .memberEmail(member.getMemberEmail())
                .memberName(member.getMemberName())
                .memberId(member.getMemberId())
                .memberPassword(member.getMemberPassword())
                .memberPhone(member.getMemberPhone())
                .memberStatus(member.getMemberStatus())
                .memberPoint(member.getMemberPoint())
                .participationCount(member.getParticipationCount())
                .address(member.getAddress())
                .build();
    }
}
