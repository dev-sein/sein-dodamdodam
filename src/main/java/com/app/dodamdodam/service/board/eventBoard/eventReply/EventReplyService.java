package com.app.dodamdodam.service.board.eventBoard.eventReply;

import com.app.dodamdodam.domain.EventReplyDTO;
import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.event.EventReply;
import com.app.dodamdodam.entity.member.Member;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventReplyService {
    /* 댓글 작성 */
    public void saveEventBoardReply(EventReply eventReply, Long boardId, Long memberId);

    /* 댓글 수정 */
    public void setEventReplyContent(EventReply UpdatedEventReply, Long replyId);

    /* 댓글 삭제 */
    public void removeEventReply(Long replyId);

    /* 댓글 조회 */
    public List<EventReplyDTO> getEventRepliesByBoardId(Pageable pageable, Long boardId);

    /* 총 댓글수 조회 */
    public Long getEventRepliesCountByBoardId(Pageable pageable, Long boardId);

    /* 댓글 지운 후 그 board에 남아있는 댓글수 조회 */
    public Integer getEventRepliesCountByReplyId(Long replyId);

    default EventReplyDTO toEventReplyDTO(EventReply eventReply){
        return EventReplyDTO.builder().id(eventReply.getId())
                .memberDTO(toMemberDTO(eventReply.getMember()))
                .replyContent(eventReply.getReplyContent())
                .boardId(eventReply.getEventBoard().getId())
                .createdDate(eventReply.getCreatedDate())
                .updatedDate(eventReply.getUpdatedDate())
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
                .memberType(member.getMemberType())
                .build();
    }
}
