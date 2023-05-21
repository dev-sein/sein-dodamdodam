package com.app.dodamdodam.controller.board.event;

import com.app.dodamdodam.domain.EventBoardDTO;
import com.app.dodamdodam.service.board.eventBoard.EventBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/event/*")
@RequiredArgsConstructor
@Slf4j
public class EventBoardController {
    @Qualifier
    private final EventBoardService eventBoardService;

    @GetMapping("list")
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
