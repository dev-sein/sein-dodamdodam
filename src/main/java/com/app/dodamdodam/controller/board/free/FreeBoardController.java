package com.app.dodamdodam.controller.board.free;

import com.app.dodamdodam.domain.FreeBoardFileDTO;
import com.app.dodamdodam.search.FreeBoardSearch;
import com.app.dodamdodam.service.board.freeBoard.FreeBoardService;
import com.sun.tools.jconsole.JConsoleContext;
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
@RequestMapping("free/*")
@RequiredArgsConstructor
@Slf4j
public class FreeBoardController {
    private final FreeBoardService freeBoardService;

//    자유게시판 메인
    @GetMapping("list")
    public String freeBoardList(Model model){
        /* 좋아요 랭킹 5개 freeBoardList */
        model.addAttribute("top5",freeBoardService.getTop5FreeBoards());

        return "free-board/free-board-list";
    }

//    자유게시판 무한 스크롤  // 검색으로 수정하기
    @ResponseBody
    @GetMapping("list-search")
    public List<FreeBoardFileDTO> getFreeBoardList(@RequestParam int page, @RequestParam String search){
        FreeBoardSearch freeBoardSearch = new FreeBoardSearch();
        freeBoardSearch.setBoardContent(search);
        freeBoardSearch.setBoardTitle(search);
        freeBoardSearch.setWriterName(search);

        log.info(freeBoardSearch.toString());

        Pageable pageable = PageRequest.of(page,10);

        List<FreeBoardFileDTO> freeBoards = freeBoardService.getFreeBoardsBySearch(pageable, freeBoardSearch);
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info(freeBoards.toString());

        return freeBoards;
    }

    //    자유게시판 상세
    @GetMapping("detail/{boardId}")
    public String freeBoardDetail(Model model, @PathVariable(value = "boardId") Long boardId){
        freeBoardService.getFreeBoardById(boardId).ifPresent(freeBoard -> model.addAttribute("freeBoardDetail",freeBoard));

        return "free-board/free-board-detail";
    }
    
}
