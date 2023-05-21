package com.app.dodamdodam.service.board.eventBoard;

import com.app.dodamdodam.domain.EventBoardDTO;
import com.app.dodamdodam.domain.EventFileDTO;
import com.app.dodamdodam.domain.FreeBoardFileDTO;
import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.event.EventFile;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.search.EventBoardSearch;
import com.app.dodamdodam.type.EventType;
import com.app.dodamdodam.search.board.AdminEventBoardSearch;
import com.app.dodamdodam.search.board.AdminFreeBoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface EventBoardService {

//    상세보기
    public EventBoardDTO getDetail(Long id);
    // 저장
    public void write(EventBoardDTO eventBoardDTO, Long memberId);
    //    현재 시퀀스 가져오기
    public EventBoard getCurrentSequence();
    /* 자유 게시글 검색 */
    public List<EventBoardDTO> getEventBoardsBySearch(Pageable pageable, EventBoardSearch eventBoardSearch, EventType eventStatus);
    // 수정
    public void update(EventBoardDTO eventBoardDTO);
    // 삭제
    public void delete(Long eventBoardId);
    //     관리자 : 게시글 검색
    public Page<EventBoardDTO> showAdminEventWithSearch_QueryDSL(Pageable pageable, AdminEventBoardSearch adminEventBoardSearch);


    default EventBoardDTO eventBoardToDTO(EventBoard eventBoard){
        return EventBoardDTO.builder()
                .id(eventBoard.getId())
                .boardTitle(eventBoard.getBoardTitle())
                .boardContent(eventBoard.getBoardContent())
                .eventStatus(eventBoard.getEventStatus())
                .createdDate(eventBoard.getCreatedDate())
                .updatedDate(eventBoard.getUpdatedDate())
                .memberDTO(toMemberDTO(eventBoard.getMember()))
                .eventFiles(eventFileToDTO(eventBoard.getEventFiles()))
                .build();
    }

    default EventBoardDTO toEventSearchBoardDTO(EventBoard eventBoard){
        return EventBoardDTO.builder().id(eventBoard.getId()).boardTitle(eventBoard.getBoardTitle())
                .boardContent(eventBoard.getBoardContent()).createdDate(eventBoard.getCreatedDate())
                .eventAddress(eventBoard.getEventAddress()).eventAddressDetail(eventBoard.getEventAddressDetail())
                .eventStatus(eventBoard.getEventStatus()).memberDTO(toMemberDTO(eventBoard.getMember()))
                .eventFiles(eventFileToDTO(eventBoard.getEventFiles())).build();
    }

    default MemberDTO toMemberDTO(Member member){
        return MemberDTO.builder()
                .id(member.getId())
                .memberId(member.getMemberId())
                .memberPassword(member.getMemberPassword())
                .memberName(member.getMemberName())
                .memberEmail(member.getMemberEmail())
                .memberPhone(member.getMemberPhone())
                .memberStatus(member.getMemberStatus())
                .memberPoint(member.getMemberPoint())
                .participationCount(member.getParticipationCount())
                .createdDate(member.getCreatedDate())
                .build();
    }

    default EventBoard toEventBoardEntity(EventBoardDTO eventBoardDTO){
        return EventBoard.builder()
                .boardTitle(eventBoardDTO.getBoardTitle())
                .boardContent(eventBoardDTO.getBoardContent())
                .eventFiles(eventBoardDTO.getEventFiles().stream().map(file -> toEventFileEntity(file)).collect(Collectors.toList()))
                .member(toMemberEntity(eventBoardDTO.getMemberDTO()))
                .eventBusinessName(eventBoardDTO.getEventBusinessName())
                .eventBusinessNumber(eventBoardDTO.getEventBusinessNumber())
                .eventBusinessEmail(eventBoardDTO.getEventBusinessEmail())
                .eventBusinessTel(eventBoardDTO.getEventBusinessTel())
                .eventStartDate(LocalDate.parse(eventBoardDTO.getEventStartDate()))
                .eventEndDate(LocalDate.parse(eventBoardDTO.getEventEndDate()))
                .eventAddressDetail(eventBoardDTO.getEventAddressDetail())
                .eventAddress(eventBoardDTO.getEventAddress())
                .eventLikeNumber(eventBoardDTO.getEventLikeNumber())
                .eventReviewCount(eventBoardDTO.getEventLikeNumber())
                .build();
    }

    default Member toMemberEntity(MemberDTO memberDTO){
        return Member.builder()
                .memberId(memberDTO.getMemberId())
                .memberPassword(memberDTO.getMemberPassword())
                .memberEmail(memberDTO.getMemberEmail())
                .memberName(memberDTO.getMemberName())
                .memberPhone(memberDTO.getMemberPhone())
                .memberStatus(memberDTO.getMemberStatus())
                .memberRole(memberDTO.getMemberRole())
                .build();
    }

    default List<EventFileDTO> eventFileToDTO(List<EventFile> eventFiles){
        List<EventFileDTO> eventFileDTOS = new ArrayList<>();
        eventFiles.forEach(
                eventFile ->{
                    EventFileDTO eventFileDTO = EventFileDTO.builder()
                            .id(eventFile.getId())
                            .fileOriginalName(eventFile.getFileOriginalName())
                            .filePath(eventFile.getFilePath())
                            .fileUuid(eventFile.getFileUuid())
                            .fileSize(eventFile.getFileSize())
                            .fileRepresent(eventFile.getFileRepresent())
                            .build();
                    eventFileDTOS.add(eventFileDTO);
                }
        );
        return eventFileDTOS;
    }

    default EventFile toEventFileEntity(EventFileDTO eventFileDTO){
        return EventFile.builder()
                .id(eventFileDTO.getId())
                .fileOriginalName(eventFileDTO.getFileOriginalName())
                .filePath(eventFileDTO.getFilePath())
                .fileRepresent(eventFileDTO.getFileRepresent())
                .fileSize(eventFileDTO.getFileSize())
                .fileUuid(eventFileDTO.getFileUuid())
                .build();
    }

}