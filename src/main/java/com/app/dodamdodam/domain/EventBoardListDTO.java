package com.app.dodamdodam.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
public class EventBoardListDTO {
    /*멤버 정보(이름)*/
    private MemberDTO memberDTO;

    /*이벤트정보(제목,게시날짜,좋아요)*/
    private EventBoardDTO eventBoardDTO;

    /*파일*/
    private List<EventBoardFileDTO> eventBoardFileDTOS;

    @QueryProjection
    public EventBoardListDTO(MemberDTO memberDTO, EventBoardDTO eventBoardDTO, List<EventBoardFileDTO> eventBoardFileDTOS) {
        this.memberDTO = memberDTO;
        this.eventBoardDTO = eventBoardDTO;
        this.eventBoardFileDTOS = eventBoardFileDTOS;
    }
}

