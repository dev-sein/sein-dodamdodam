package com.app.dodamdodam.service.point;

import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.domain.PointDTO;
import com.app.dodamdodam.domain.RecruitmentBoardFileDTO;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.search.board.AdminRecruitmentSearch;
import com.app.dodamdodam.search.point.AdminPointSearch;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Service
public interface PointService {
    //관리자 : 포인트 목록
    public Page<PointDTO> showList(Pageable pageable);

    //관리자 : 포인트 검색
    public Page<PointDTO> showAdminPointWithSearch_QueryDSL(Pageable pageable, AdminPointSearch adminPointSearch);


    default PointDTO toPointDTO(Point point){
    return PointDTO.builder().id(point.getId()).memberId(point.getMember().getMemberId()).
            memberDTO(toMemberDTO(point.getMember())).
            memberName(point.getMember().getMemberName()).pointAmount(point.getPointAmount()).
            pointStatus(point.getPointStatus()).createdDate(point.getCreatedDate()).updatedDate(point.getUpdatedDate()).build();
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
