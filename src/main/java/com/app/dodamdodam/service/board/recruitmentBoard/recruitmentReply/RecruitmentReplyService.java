package com.app.dodamdodam.service.board.recruitmentBoard.recruitmentReply;

import com.app.dodamdodam.domain.*;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.recruitment.RecruitmentReply;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecruitmentReplyService {
    /* 댓글 작성 */
    public void saveRecruitmentBoardReply(RecruitmentReply recruitmentReply, Long boardId, Long memberId);

    /* 댓글 수정 */
    public void setRecruitmentReplyContent(RecruitmentReply recruitmentReply, Long replyId);

    /* 댓글 삭제 */
    public void removeRecruitmentReply(Long replyId);

    /* 댓글 조회 */
    public List<RecruitmentReplyDTO> getRecruitmentRepliesByBoardId(Pageable pageable, Long boardId);

    /* 총 댓글수 조회 */
    public Long getRecruitmentRepliesCountByBoardId(Pageable pageable, Long boardId);

    /* 댓글 지운 후 그 board에 남아있는 댓글수 조회 */
    public Integer getRecruitmentRepliesCountByReplyId(Long replyId);

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