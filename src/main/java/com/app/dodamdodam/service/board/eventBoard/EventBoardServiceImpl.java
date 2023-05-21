package com.app.dodamdodam.service.board.eventBoard;

import com.app.dodamdodam.domain.EventBoardDTO;
import com.app.dodamdodam.domain.EventFileDTO;
import com.app.dodamdodam.domain.FreeBoardFileDTO;
import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.event.EventFile;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.exception.BoardNotFoundException;
import com.app.dodamdodam.exception.MemberNotFoundException;
import com.app.dodamdodam.repository.board.event.board.EventBoardRepository;
import com.app.dodamdodam.repository.board.event.file.EventFileRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.search.board.AdminEventBoardSearch;
import com.app.dodamdodam.search.board.AdminFreeBoardSearch;
import com.app.dodamdodam.type.FileRepresent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("eventBoard")
@Slf4j
@Transactional
public class EventBoardServiceImpl implements EventBoardService {
    private final EventBoardRepository eventBoardRepository;
    private final MemberRepository memberRepository;
    private final EventFileRepository eventFileRepository;

    @Override
    public EventBoardDTO getDetail(Long id) {
        EventBoard eventBoard = eventBoardRepository.findEventBoardById_QueryDSL(id).orElseThrow(() -> {
            throw new BoardNotFoundException();
        });
        return eventBoardToDTO(eventBoard);
    }
// 저장하기
@Override
public void write(EventBoardDTO eventBoardDTO, Long memberId) {
    List<EventFileDTO> fileDTOS = eventBoardDTO.getEventFiles();

    memberRepository.findById(memberId).ifPresent(
            member -> eventBoardDTO.setMemberDTO(toMemberDTO(member))
    );
    log.info("들어옴@@@@@@@@@@@@@@@@@@");
    log.info(eventBoardDTO.toString());
    log.info(toEventBoardEntity(eventBoardDTO).toString());

    eventBoardRepository.save(toEventBoardEntity(eventBoardDTO));
    if(fileDTOS != null){
        for (int i = 0; i < fileDTOS.size(); i++) {
            if(i == 0){
                fileDTOS.get(i).setFileRepresent(FileRepresent.REPRESENT);
            }else {
                fileDTOS.get(i).setFileRepresent(FileRepresent.ORDINARY);
            }
            fileDTOS.get(i).setEventBoard(getCurrentSequence());
            eventFileRepository.save(toEventFileEntity(fileDTOS.get(i)));
        }
    }
}
//    현재 시퀀스 가져오기
    @Override
    public EventBoard getCurrentSequence() {
        return eventBoardRepository.getCurrentSequence_QueryDsl();
    }


    @Override
    public Slice<EventBoardDTO> getEventBoards(Pageable pageable) {
        Slice<EventBoard> eventBoards =
                eventBoardRepository.findAllByIdDescWithPaging_QueryDSL(PageRequest.of(0, 10));
        List<EventBoardDTO> collect = eventBoards.get().map(board -> eventBoardToDTO(board)).collect(Collectors.toList());

        return new SliceImpl<>(collect, pageable, eventBoards.hasNext());
    }


    @Override
    public void update(EventBoardDTO eventBoardDTO) {

    }


//    @Override @Transactional
//    public void update(EventBoardDTO eventBoardDTO) {
//        List<EventFileDTO> fileDTOS = eventBoardDTO.getEventFiles();
//
//        eventBoardRepository.findById(eventBoardDTO.getId()).ifPresent(eventBoard -> {
//            EventBoard updatedEventBoard = EventBoard.builder()
//                    .id(eventBoard.getId())
//                    .boardContent(eventBoardDTO.getBoardContent())
//                    .boardTitle(eventBoardDTO.getBoardTitle())
//                    .member(eventBoard.getMember())
//                    .createDate(eventBoard.getCreatedDate())
//                    .suggestLikeCount(eventBoard.getEventLikeNumber())
//                    .suggestReplyCount(eventBoard.getEventReviewCount())
//                    .build();
//
//            eventBoardRepository.save(updatedEventBoard;
//        });
//
//        eventFileRepository.deleteBySuggestId(suggestDTO.getId());
//
//        if(fileDTOS != null){
//            for (int i = 0; i < fileDTOS.size(); i++) {
//                if(i == 0){
//                    fileDTOS.get(i).setFileType(FileType.REPRESENTATIVE);
//                }else {
//                    fileDTOS.get(i).setFileType(FileType.NORMAL);
//                }
//                fileDTOS.get(i).setSuggest(getCurrentSequence());
//                suggestFileRepository.save(toSuggestFileEntity(fileDTOS.get(i)));
//            }
//        }
//    }

    @Override
    public void delete(Long eventBoardId) {

    }

    /* 관리자 이벤트 검색 */
    @Override
    public Page<EventBoardDTO> showAdminEventWithSearch_QueryDSL(Pageable pageable, AdminEventBoardSearch adminEventBoardSearch) {
            Page<EventBoard> eventBoardPage = eventBoardRepository.findAdmindEventBoardWithPaging_QueryDSL(adminEventBoardSearch, pageable);
            List<EventBoardDTO> eventBoardDTOS = eventBoardPage.getContent().stream()
                    .map(this::toEventSearchBoardDTO)
                    .collect(Collectors.toList());
            return new PageImpl<>(eventBoardDTOS, pageable, eventBoardPage.getTotalElements());
        }
}
