package com.app.dodamdodam.service.board.recruitmentBoard.recruitmentReply;

import com.app.dodamdodam.domain.FreeReplyDTO;
import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.domain.RecruitmentBoardFileDTO;
import com.app.dodamdodam.domain.RecruitmentReplyDTO;
import com.app.dodamdodam.entity.free.FreeReply;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.recruitment.RecruitmentReply;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecruitmentReplyService {
    /* 댓글 리스트 가져오기 */
    public List<RecruitmentReplyDTO> getFreeRepliesByBoardId(Pageable pageable, Long boardId);

    /* 총 댓글수 조회 */
    public Long getFreeRepliesCountByBoardId(Pageable pageable, Long boardId);

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
                .memberType(member.getMemberType())
                .build();
    }

    default RecruitmentReplyDTO toRecruitmentReplyDTO(RecruitmentReply recruitmentReply){
        return RecruitmentReplyDTO.builder().id(recruitmentReply.getId())
                .boardId(recruitmentReply.getRecruitmentBoard().getId())
                .createdDate(recruitmentReply.getCreatedDate())
                .memberDTO(toMemberDTO(recruitmentReply.getMember()))
                .replyContent(recruitmentReply.getReplyContent())
                .updatedDate(recruitmentReply.getUpdatedDate())
                .build();
    }
}