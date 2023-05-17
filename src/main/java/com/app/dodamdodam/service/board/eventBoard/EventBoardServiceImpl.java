package com.app.dodamdodam.service.board.eventBoard;

import com.app.dodamdodam.domain.EventBoardDTO;
import com.app.dodamdodam.domain.EventFileDTO;
import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.event.EventFile;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.exception.BoardNotFoundException;
import com.app.dodamdodam.exception.MemberNotFoundException;
import com.app.dodamdodam.repository.board.event.board.EventBoardRepository;
import com.app.dodamdodam.repository.board.event.file.EventFileRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.type.FileRepresent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("eventBoard")
@Slf4j
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
@Transactional(rollbackFor = Exception.class)
public void write(EventBoardDTO eventBoardDTO, Long memberId) {
    List<EventFileDTO> eventFileDTOS = eventBoardDTO.getEventFiles();
    // 아이디 조회 실패 시 Exception
    Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);

    // 게시판에 setMember
    EventBoard eventBoard = toEventBoardEntity(eventBoardDTO);
    eventBoard.setMember(member);

    eventBoardRepository.save(eventBoard);

    int count = 0;

    for (int i = 0; i < eventFileDTOS.size(); i++) {
        if(eventFileDTOS.get(i) == null) continue;

        if (count == 0) {
            eventFileDTOS.get(i).setFileRepresent(FileRepresent.REPRESENT);
            count++;
        } else {
            eventFileDTOS.get(i).setFileRepresent(FileRepresent.ORDINARY);
        }

        eventFileDTOS.get(i).setEventBoardDTO(eventBoardToDTO(getCurrentSequence()));
        // 엔티티
        EventFile eventFile = toEventFileEntity(eventFileDTOS.get(i));

        eventFile.setEventBoard(eventBoard);

        eventFileRepository.save(eventFile);
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
}
