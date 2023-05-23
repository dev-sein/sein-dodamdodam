package com.app.dodamdodam.service.board.eventBoard;

import com.app.dodamdodam.domain.EventBoardDTO;
import com.app.dodamdodam.domain.EventFileDTO;
import com.app.dodamdodam.domain.FreeBoardFileDTO;
import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.event.EventFile;
import com.app.dodamdodam.repository.board.event.board.EventBoardRepository;
import com.app.dodamdodam.repository.board.event.file.EventFileRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.search.EventBoardSearch;
import com.app.dodamdodam.search.board.AdminEventBoardSearch;
import com.app.dodamdodam.type.EventType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("eventBoard") @Primary
@Transactional
@Slf4j
public class EventBoardServiceImpl implements EventBoardService {
    private final EventBoardRepository eventBoardRepository;
    private final MemberRepository memberRepository;
    private final EventFileRepository eventFileRepository;

//    상세보기
    @Override
    public EventBoardDTO getDetail(Long eventBoardId) {
        Optional<EventBoard> eventBoard = eventBoardRepository.findEventBoardById_QueryDSL(eventBoardId);
        return eventBoardToDTO(eventBoard.get());
    }

    // 이벤트 게시판 등록
    @Override
    public void register(EventBoardDTO eventBoardDTO, Long memberId) {
        List<EventFileDTO> eventFileDTOS = eventBoardDTO.getEventFileDTOS();

        memberRepository.findById(memberId).ifPresent(
                member -> eventBoardDTO.setMemberDTO(toMemberDTO(member))
        );

        EventBoard eventBoard = eventBoardRepository.save(toEventBoardEntity(eventBoardDTO));

        if(eventFileDTOS != null){
            for (int i = 0; i < eventFileDTOS.size(); i++) {
                EventFileDTO eventFileDTO = eventFileDTOS.get(i);
                EventFile eventFile = toEventFileEntity(eventFileDTO);
                eventFile.setEventBoard(eventBoard);

                eventFileRepository.save(eventFile);
            }
        }
    }
//    현재 시퀀스 가져오기
    @Override
    public EventBoard getCurrentSequence() {
        return eventBoardRepository.getCurrentSequence_QueryDsl();
    }


    /* 이벤트 게시글 검색 */
    @Override
    public List<EventBoardDTO> getEventBoardsBySearch(Pageable pageable, EventBoardSearch eventBoardSearch, EventType eventStatus) {
        return eventBoardRepository.findEventBoardBySearchWithPaging_QueryDSL(eventBoardSearch, pageable, eventStatus).stream().map(eventBoard -> eventBoardToDTO(eventBoard)).collect(Collectors.toList());
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

    /* 관리자 이벤트 상세 */
    @Override
    public EventBoardDTO getAdminEventBoardDetail(Long id) {
            Optional<EventBoard> eventBoards = eventBoardRepository.findById(id);
            return toEventSearchBoardDTO(eventBoards.get());
        }

    @Override
    public List<EventBoardDTO> getRecentEventBoardList() {
        List<EventBoardDTO> eventBoardDTOS = eventBoardRepository.findRecentEventBoardList_QueryDSL().stream().map(freeBoard -> eventBoardToDTO(freeBoard)).collect(Collectors.toList());
        return eventBoardDTOS;
    }

}
