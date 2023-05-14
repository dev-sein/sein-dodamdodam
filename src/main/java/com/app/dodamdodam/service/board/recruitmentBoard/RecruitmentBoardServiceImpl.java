package com.app.dodamdodam.service.board.recruitmentBoard;

import com.app.dodamdodam.domain.RecruitmentBoardFileDTO;
import com.app.dodamdodam.repository.board.recruitment.RecruitmentBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruitmentBoardServiceImpl implements RecruitmentBoardService {
    @Autowired
    private RecruitmentBoardRepository recruitmentBoardRepository;

    @Override
    public List<RecruitmentBoardFileDTO> getRecruimentBoardListByMemberId(Pageable pageable, Long memberId) {
        return recruitmentBoardRepository.findRecruitmentBoardListByMemberId_QueryDSL(pageable,memberId)
                .stream().map(recruitmentBoard -> toRecruitmentBoardFileDto(recruitmentBoard)).collect(Collectors.toList());
    }

    @Override
    public List<RecruitmentBoardFileDTO> getRecruimentedBoardListByMemberId(Pageable pageable, Long memberId) {
        return recruitmentBoardRepository.findRecruitmentedBoardListByMemberId_QueryDSL(pageable,memberId)
                .stream().map(recruitmentBoard -> toRecruitmentBoardFileDto(recruitmentBoard)).collect(Collectors.toList());
    }
}
