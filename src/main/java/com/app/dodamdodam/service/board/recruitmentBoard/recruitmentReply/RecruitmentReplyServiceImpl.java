package com.app.dodamdodam.service.board.recruitmentBoard.recruitmentReply;

import com.app.dodamdodam.domain.RecruitmentReplyDTO;
import com.app.dodamdodam.entity.recruitment.RecruitmentReply;
import com.app.dodamdodam.repository.board.recruitment.RecruitmentBoardRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.repository.reply.recruitmentReply.RecruitmentReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RecruitmentReplyServiceImpl implements RecruitmentReplyService {
    @Autowired
    private RecruitmentBoardRepository recruitmentBoardRepository;

    @Autowired
    private RecruitmentReplyRepository recruitmentReplyRepository;

    @Autowired
    private MemberRepository memberRepository;

    /* 모집 게시글 댓글 작성 */
    @Override
    public void saveRecruitmentBoardReply(RecruitmentReply recruitmentReply, Long boardId, Long memberId) {
        recruitmentReply.setRecruitmentBoard(recruitmentBoardRepository.findById(boardId).get());
        recruitmentReply.setMember(memberRepository.findById(memberId).get());
        recruitmentReplyRepository.save(recruitmentReply);
    }

    /* 모집 게시글 댓글 수정 */
    @Override
    public void setRecruitmentReplyContent(RecruitmentReply updateRecruitmentReply, Long replyId) {
        recruitmentReplyRepository.findById(replyId).ifPresent(recruitmentReply -> recruitmentReply.setReplyContent(updateRecruitmentReply.getReplyContent()));
    }

    /* 모집 게시글 댓글 삭제 */
    @Override
    public void removeRecruitmentReply(Long replyId) {
        recruitmentReplyRepository.findById(replyId).ifPresent(recruitmentReply -> recruitmentReplyRepository.delete(recruitmentReply));
    }

    /* 모집 게시글 boardId로 댓글 리스트 뿌려주기 */
    @Override
    public List<RecruitmentReplyDTO> getRecruitmentRepliesByBoardId(Pageable pageable, Long boardId) {
        Page<RecruitmentReply> recruitmentReplies = recruitmentReplyRepository.findRecruitmentRepliesByBoardId_QueryDSL(pageable,boardId);
        List<RecruitmentReplyDTO> recruitmentReplyDTOS = recruitmentReplies.get().map(recruitmentReply -> toRecruitmentReplyDTO(recruitmentReply)).collect(Collectors.toList());
        return recruitmentReplyDTOS;
    }

    /* 이벤트 게시글 boardId로 그 게시글에 달린 댓글 총 개수 구하기 */
    @Override
    public Long getRecruitmentRepliesCountByBoardId(Pageable pageable, Long boardId) {
        Page<RecruitmentReply> recruitmentReplies = recruitmentReplyRepository.findRecruitmentRepliesByBoardId_QueryDSL(pageable,boardId);
        return recruitmentReplies.getTotalElements();
    }

    /* 댓글 지운 후 그 board에 남아있는 댓글 수 조회 */
    @Override
    public Integer getRecruitmentRepliesCountByReplyId(Long replyId) {
        return recruitmentBoardRepository.findReplyCountByReplyId_QueryDsl(replyId);
    }


}
