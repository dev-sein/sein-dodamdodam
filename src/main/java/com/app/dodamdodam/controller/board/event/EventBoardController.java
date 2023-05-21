package com.app.dodamdodam.controller.board.event;

import com.app.dodamdodam.domain.EventBoardDTO;
import com.app.dodamdodam.search.EventBoardSearch;
import com.app.dodamdodam.service.board.eventBoard.EventBoardService;
import com.app.dodamdodam.type.EventType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/event/*")
@RequiredArgsConstructor
@Slf4j
public class EventBoardController {
    @Qualifier
    private final EventBoardService eventBoardService;

    @GetMapping("list")
    public String goList(Model model){
        log.info("@LLllllllllllllll");
        return "event-board/event-board-list";
    }

    //    이벤트 게시판 리스트(최신순)
    @ResponseBody
    @GetMapping("list-search")
    public List<EventBoardDTO> getEventBoardList(@RequestParam int page, @RequestParam String search, @RequestParam String eventType){
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        EventBoardSearch eventBoardSearch = new EventBoardSearch();
        eventBoardSearch.setBoardContent(search);
        eventBoardSearch.setBoardTitle(search);
        eventBoardSearch.setWriterName(search);
        EventType eventStatus = null;
        if (eventStatus.equals("전체")){
            eventStatus = EventType.ALL;
        } else if(eventStatus.equals("진행중")){
            eventStatus = EventType.PROGRESS;
        } else if (eventStatus.equals("진행전")){
            eventStatus = EventType.HOLD;
        } else if (eventStatus.equals("이벤트")){
            eventStatus = EventType.END;
        } else {
            eventStatus = null;
        }

        log.info(eventBoardSearch.toString());

        Pageable pageable = PageRequest.of(page,8);

        List<EventBoardDTO> eventboards = eventBoardService.getEventBoardsBySearch(pageable, eventBoardSearch, eventStatus);
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info(eventboards.toString());

        return eventboards;
    }


    //    이벤트 게시판 상세보기
    @GetMapping("detail/{id}")
    public String goEventDetail(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventBoardService.getDetail(id));
        return "event-board/event-board-detail";
    }

// 작성하기
    @GetMapping("write")
    public String goToWriteForm(HttpSession session) {
        /*임시로 세션에 memberId 담아둠*/
        session.setAttribute("memberId", 1L);

        return "event-board/event-board-write";
    }

    @PostMapping("write")
    public RedirectView write(EventBoardDTO eventBoardDTO, HttpSession session) {
        Long memberId = (Long)session.getAttribute("memberId");
        log.info(eventBoardDTO.toString());

        eventBoardService.write(eventBoardDTO, memberId);
        return new RedirectView("/event/list");
    }


}
