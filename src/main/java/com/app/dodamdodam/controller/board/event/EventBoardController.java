package com.app.dodamdodam.controller.board.event;

import com.app.dodamdodam.domain.EventBoardDTO;
import com.app.dodamdodam.service.board.eventBoard.EventBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String goList(Model model){
        return "event-board/event-board-list";
    }

    //    이벤트 게시판 상세보기
    @GetMapping("detail/{id}")
    public String goEventDetail(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventBoardService.getDetail(id));
        return "event-board/event-board-detail";
    }

// 작성하기
    @GetMapping("write")
    public String goToWriteForm() {
        return "event-board/event-board-write";
    }

    @ResponseBody
    @PostMapping("write")
    public RedirectView getEventWriteForm(@RequestBody EventBoardDTO eventBoardDTO, HttpSession session){
//        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
//        Long memberId = memberDTO.getId();
        Long memberId = (Long) session.getAttribute("id");
        log.info(memberId + "");
        eventBoardService.register(eventBoardDTO, memberId);

        return new RedirectView("/event/list");
    }

}
