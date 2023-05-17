package com.app.dodamdodam.controller.board.event;

import com.app.dodamdodam.domain.EventBoardDTO;
import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.service.board.eventBoard.EventBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/event-board/*")
@RequiredArgsConstructor
@Slf4j
public class EventBoardController {
    @Qualifier
    private final EventBoardService eventBoardService;

    @GetMapping("event-board-list")
    public String goList(EventBoardDTO eventBoardDTO){return "event-board/event-board-list";}

    //    이벤트 게시판 리스트(최신순)
//    @GetMapping("event-board-list")
//    @ResponseBody
//    public Slice<EventBoardDTO> goEventBoardList(@PageableDefault(page = 1, size = 5) Pageable pageable) {
//        return eventBoardService
//                .getEventBoards(PageRequest.of(pageable.getPageNumber() - 1,
//                        pageable.getPageSize()));
//    }

    //    이벤트 게시판 상세보기
    @GetMapping("event-board-detail/{id}")
    public String goEventDetail(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventBoardService.getDetail(id));
        return "event-board/event-board-detail";
    }

// 작성하기
    @GetMapping("event-board-write")
    public void goToEventWrite(Model model) {
        model.addAttribute("eventBoard", new EventBoardDTO());
    }

    @PostMapping("event-board-write")
    public RedirectView EventWrite(@ModelAttribute EventBoardDTO eventBoardDTO) {
        Long memberId = 1L;
        eventBoardService.write(eventBoardDTO, memberId);

        log.info("=====================" + eventBoardDTO);

        return new RedirectView("/event-board/event-board-list");
    }

}
