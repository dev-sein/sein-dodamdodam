package com.app.dodamdodam.controller.board.free;

import com.app.dodamdodam.domain.FreeBoardFileDTO;
import com.app.dodamdodam.search.FreeBoardSearch;
import com.app.dodamdodam.service.board.freeBoard.FreeBoardService;
import com.app.dodamdodam.service.board.freeBoard.freeReply.FreeReplyService;
import com.app.dodamdodam.type.CategoryType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("free/*")
@RequiredArgsConstructor
@Slf4j
public class FreeBoardController {
    private final FreeBoardService freeBoardService;

    private final FreeReplyService freeReplyService;

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
    public List<FreeBoardFileDTO> getFreeBoardList(@RequestParam int page, @RequestParam String search, @RequestParam String category){
        FreeBoardSearch freeBoardSearch = new FreeBoardSearch();
        freeBoardSearch.setBoardContent(search);
        freeBoardSearch.setBoardTitle(search);
        freeBoardSearch.setWriterName(search);
        CategoryType categoryType = null;
        if (category.equals("문화")){
            categoryType = CategoryType.CULTURE;
        } else if (category.equals("일상")){
            categoryType = CategoryType.DAILY;
        } else if (category.equals("이벤트")){
            categoryType = CategoryType.EVENT;
        } else if (category.equals("판매")){
            categoryType = CategoryType.PURCHASE;
        } else if (category.equals("모집")){
            categoryType = CategoryType.RECRUITMENT;
        } else {
            categoryType = null;
        }

        log.info(freeBoardSearch.toString());

        Pageable pageable = PageRequest.of(page,10);

        List<FreeBoardFileDTO> freeBoards = freeBoardService.getFreeBoardsBySearch(pageable, categoryType, freeBoardSearch);
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info(freeBoards.toString());

        return freeBoards;
    }

    //    자유게시판 상세
    @GetMapping("detail/{boardId}")
    public String freeBoardDetail(Model model, @PathVariable(value = "boardId") Long boardId){
        freeBoardService.getFreeBoardById(boardId).ifPresent(freeBoard -> model.addAttribute("freeBoardDetail",freeBoard));
        model.addAttribute("top5",freeBoardService.getTop5FreeBoards());
        /* PageRequest는 뭐 넣어도 상관없이 개수 가져와서 아무렇게나 넣음 */
        model.addAttribute("replyCount",freeReplyService.getFreeRepliesCountByBoardId(PageRequest.of(0, 5), boardId));


        return "free-board/free-board-detail";
    }
    
}
