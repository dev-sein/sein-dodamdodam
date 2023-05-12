package com.app.dodamdodam.domain;

import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.member.Member;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EventBoardDetailDTO {
    /*파일*/
    private List<EventBoardFileDTO> eventBoardFileDTOS;
    /*작성자,작성자번호, 작성자이메일*/
    private MemberDTO memberDTO;
    /*이벤트 날짜(시작일,종료일),이벤트 장소*/
    private EventBoardDTO eventBoardDTO;
    /*댓글,댓글작성자,(create/upate)날짜 */
    private EventBoardReviewDTO eventBoardReviewDTO;
}
