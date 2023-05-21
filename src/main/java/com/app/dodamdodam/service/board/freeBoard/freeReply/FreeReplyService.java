package com.app.dodamdodam.service.board.freeBoard.freeReply;

import com.app.dodamdodam.domain.FreeReplyDTO;
import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.entity.free.FreeReply;
import com.app.dodamdodam.entity.member.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FreeReplyService {
    /* 댓글 작성 */
    public void saveFreeBoardReply(FreeReply freeReply, Long boardId, Long memberId);

    /* 댓글 수정 */
    public void setFreeReplyContent(FreeReply updatedFreeReply, Long replyId);

    /* 댓글 삭제 */
    public void removeFreeReply(Long replyId);

    /* 댓글 조회 */
    public List<FreeReplyDTO> getFreeRepliesByBoardId(Pageable pageable, Long boardId);

    /* 총 댓글수 조회 */
    public Long getFreeRepliesCountByBoardId(Pageable pageable, Long boardId);

    default FreeReplyDTO toFreeReplyDTO(FreeReply freeReply){
        return FreeReplyDTO.builder().id(freeReply.getId())
                .memberDTO(toMemberDTO(freeReply.getMember()))
                .replyContent(freeReply.getReplyContent())
                .boardId(freeReply.getFreeBoard().getId())
                .createdDate(freeReply.getCreatedDate())
                .updatedDate(freeReply.getUpdatedDate())
                .build();
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