package com.app.dodamdodam.controller.board.event;

import com.app.dodamdodam.domain.EventBoardDTO;
import com.app.dodamdodam.domain.EventReplyDTO;
import com.app.dodamdodam.entity.event.EventReply;
import com.app.dodamdodam.service.board.eventBoard.EventBoardService;
import com.app.dodamdodam.service.board.eventBoard.eventReply.EventReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/event/*")
@RequiredArgsConstructor
@Slf4j
public class EventBoardController {
    private final EventBoardService eventBoardService;
    private final EventReplyService eventReplyService;

    /* 이벤트 게시판 메인 */
    @GetMapping("list")
    public String goList(Model model){
        return "event-board/event-board-list";
    }

    /* 상세페이지 */
    @GetMapping("detail/{eventBoardId}")
    public String goToDetail(Model model/*, @AuthenticationPrincipal UserDetail userDetail*/, @PathVariable("eventBoardId") Long eventBoardId) {
        EventBoardDTO eventBoardDTO = eventBoardService.getDetail(eventBoardId);
        model.addAttribute("eventBoardDTO", eventBoardDTO);
        return "event-board/event-board-detail";
    }

    /* 이벤트 게시판 상세보기 */
//    @GetMapping("detail/{boardId}")
//    public String eventBoardDetail(@PathVariable(value = "boardId") Long boardId, Model model, HttpSession session) {
//        session.setAttribute("boardId", 31L);
//        Long id = (Long) session.getAttribute("boardId");
//        model.addAttribute("eventBoardDetail", eventBoardService.getDetail(id));
//        /* PageRequest는 뭐 넣어도 상관없이 개수 가져와서 아무렇게나 넣음 */
//        model.addAttribute("replyCount", eventReplyService.getEventRepliesCountByBoardId(PageRequest.of(0, 5), id));
//        return "event-board/event-board-detail";
//    }

    /* 이벤트 상세페이지 이동 */
    @GetMapping("detail")
    public String goEventDetail(HttpSession session, Model model) {
        session.setAttribute("boardId", 31L);
        Long boardId = (Long)session.getAttribute("boardId");
        log.info(boardId+"boardId");
        log.info("=========================================" + eventBoardService.getDetail(boardId).toString());
        model.addAttribute("eventBoardDetail", eventBoardService.getDetail(boardId));
        model.addAttribute("replyCount", eventReplyService.getEventRepliesCountByBoardId(PageRequest.of(0, 5), boardId));
        return "event-board/event-board-detail";
    }

    /* 이벤트 작성하기 */
    @GetMapping("write")
    public String goToWriteForm(HttpSession session) {
        /* 임시로 세션에 memberId 담아둠 */
        session.setAttribute("memberId", 1L);
        return "event-board/event-board-write";
    }

    @ResponseBody
    @PostMapping("write")
    public void getEventWriteForm(@RequestBody EventBoardDTO eventBoardDTO, HttpSession session){
        log.info("eventBoardDTO.toString()");
        log.info(eventBoardDTO.toString());
        Long memberId = (Long) session.getAttribute("memberId");
        eventBoardService.register(eventBoardDTO, memberId);
    }


    /* 이벤트 게시판 댓글 작성 */
    @PostMapping("write-reply")
    @ResponseBody
    public Long writeReply(String replyContent, HttpSession session){
        session.setAttribute("boardId", 31L);
        Long boardId = (Long)session.getAttribute("boardId");
        EventReply eventReply = new EventReply(replyContent);
        log.info("=============================댓글작성들어옴=====================================");
        log.info("이벤트 게시판 댓글 : " + replyContent);
        log.info("게시판 ID : " + boardId);
        Long memberId = (Long)session.getAttribute("memberId");
        eventReplyService.saveEventBoardReply(eventReply, boardId, memberId);
        Long replyCount = eventReplyService.getEventRepliesCountByBoardId(PageRequest.of(0, 5), boardId);
        return replyCount;
    }

    /* 이벤트 게시판 댓글 수정 */
    @PostMapping("update-reply/{replyId}")
    @ResponseBody
    public String updateReply(String updatedEventReply, @PathVariable(value = "replyId") Long replyId, HttpSession session) {
        log.info("댓글 수정 들어옴");
        log.info("댓글 : " + updatedEventReply);
        log.info("댓글 : " + replyId);
//        session.setAttribute("boardId", 31L);
//        Long boardId = (Long)session.getAttribute("boardId");

        EventReply updatedReply = new EventReply(updatedEventReply);
        eventReplyService.setEventReplyContent(updatedReply, replyId);
        return "success##";
    }

    /* 이벤트 게시판 댓글 삭제 */
    @PostMapping("delete-reply/{replyId}")
    @ResponseBody
    public Integer deleteReply(@PathVariable(value = "replyId") Long replyId, HttpSession session){
        log.info("댓글 삭제 들어옴");
        session.setAttribute("boardId", 31L);
        Long boardId = (Long)session.getAttribute("boardId");
        Integer replyCount = eventReplyService.getEventRepliesCountByReplyId(replyId);
        eventReplyService.removeEventReply(replyId);
        return replyCount - 1;
    }

    /* 이벤트 게시판 댓글 리스트 */
    @GetMapping("replies/{page}")
    @ResponseBody
    public List<EventReplyDTO> getReplies(@PathVariable(value = "page") int page, HttpSession session){
        session.setAttribute("boardId", 31L);
        Long boardId = (Long)session.getAttribute("boardId");
        log.info("==========================댓글 리스트 들어옴================================");
        log.info(boardId + " || " + page);
        Pageable pageable = PageRequest.of(page, 5);
        return eventReplyService.getEventRepliesByBoardId(pageable, boardId);
    }
}
