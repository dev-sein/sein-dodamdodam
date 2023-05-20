package com.app.dodamdodam.service.board.freeBoard.freeReply;

import com.app.dodamdodam.domain.FreeBoardFileDTO;
import com.app.dodamdodam.domain.FreeReplyDTO;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.free.FreeReply;
import com.app.dodamdodam.repository.board.free.FreeBoardRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.repository.reply.freeReply.FreeReplyRepository;
import com.app.dodamdodam.search.FreeBoardSearch;
import com.app.dodamdodam.type.CategoryType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FreeReplyServiceImpl implements FreeReplyService {
    @Autowired
    private FreeBoardRepository freeBoardRepository;

    @Autowired
    private FreeReplyRepository freeReplyRepository;

    @Autowired
    private MemberRepository memberRepository;

    /* 자유 게시글 댓글 작성 */
    @Override
    public void saveFreeBoardReply(FreeReply freeReply, Long boardId, Long memberId) {
        freeReply.setFreeBoard(freeBoardRepository.findById(boardId).get());
        freeReply.setMember(memberRepository.findById(memberId).get());
        freeReplyRepository.save(freeReply);
    }

    /* 자유 게시글 댓글 수정 */
    @Override
    public void setFreeReplyContent(FreeReply updatedFreeReply, Long replyId) {
        freeReplyRepository.findById(replyId).ifPresent(freeReply -> freeReply.setReplyContent(updatedFreeReply.getReplyContent()));
    }

    /* 자유 게시글 댓글 삭제*/
    @Override
    public void removeFreeReply(Long replyId) {
        freeReplyRepository.findById(replyId).ifPresent(freeReply -> freeReplyRepository.delete(freeReply));
    }

    /* 자유 게시글 boardId로 댓글 리스트 뿌려주기*/
    @Override
    public List<FreeReplyDTO> getFreeRepliesByBoardId(Pageable pageable, Long boardId) {
        Page<FreeReply> freeReplies = freeReplyRepository.findFreeRepliesByBoardId(pageable,boardId);
        List<FreeReplyDTO> freeReplyDTOS = freeReplies.get().map(freeReply -> toFreeReplyDTO(freeReply)).collect(Collectors.toList());
        return freeReplyDTOS;
    }

    /* 자유게시글 boardId로 그 게시글에 달린 댓글 총 개수 구하기*/
    @Override
    public Long getFreeRepliesCountByBoardId(Pageable pageable, Long boardId) {
        Page<FreeReply> freeReplies = freeReplyRepository.findFreeRepliesByBoardId(pageable,boardId);
        return freeReplies.getTotalElements();
    }
}
