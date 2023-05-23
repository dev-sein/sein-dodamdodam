package com.app.dodamdodam.service.board.recruitmentBoard.recruitmentReply;

import com.app.dodamdodam.domain.FreeReplyDTO;
import com.app.dodamdodam.entity.free.FreeReply;
import com.app.dodamdodam.repository.board.free.FreeBoardRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.repository.reply.freeReply.FreeReplyRepository;
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
    private RecruitmentReplyRepository recruitmentReplyRepository;

    @Autowired
    private MemberRepository memberRepository;

}
