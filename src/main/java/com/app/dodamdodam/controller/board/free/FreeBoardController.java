package com.app.dodamdodam.controller.board.free;

import com.app.dodamdodam.domain.FreeBoardFileDTO;
import com.app.dodamdodam.domain.FreeReplyDTO;
import com.app.dodamdodam.entity.free.FreeReply;
import com.app.dodamdodam.search.FreeBoardSearch;
import com.app.dodamdodam.service.board.freeBoard.FreeBoardService;
import com.app.dodamdodam.service.board.freeBoard.freeReply.FreeReplyService;
import com.app.dodamdodam.type.CategoryType;
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
    private final FreeReplyService freeReplyService;

    //    자유 게시판 메인
    @GetMapping("list")
    public String freeBoardList(Model model){
        /* 좋아요 랭킹 5개 freeBoardList */
        model.addAttribute("top5",freeBoardService.getTop5FreeBoards());

        return "free-board/free-board-list";
    }

    //    자유 게시판 무한 스크롤  // 검색으로 수정하기
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

    //    자유 게시판 상세
    @GetMapping("detail/{boardId}")
    public String freeBoardDetail(Model model, @PathVariable(value = "boardId") Long boardId, HttpSession session){
        session.setAttribute("memberId",7L);    /* 임시로 세션에 memberId 값 담아둠 */

        model.addAttribute("freeBoardDetail", freeBoardService.getFreeBoardById(boardId));
        model.addAttribute("top5",freeBoardService.getTop5FreeBoards());
        /* PageRequest는 뭐 넣어도 상관없이 개수 가져와서 아무렇게나 넣음 */
        model.addAttribute("replyCount",freeReplyService.getFreeRepliesCountByBoardId(PageRequest.of(0, 5), boardId));
        model.addAttribute("recentFreeBoards",freeBoardService.getRecentFreeBoardList());


        return "free-board/free-board-detail";
    }

    //    자유 게시판 댓글 작성
    @PostMapping("write-reply")
    @ResponseBody
    public String writeReply(String replyContent, Long boardId, HttpSession session){
        FreeReply freeReply = new FreeReply(replyContent);
        log.info("댓글 작성 들어옴@@@@@@@@@@@@@@@@@@@@@@@@2");
        log.info(replyContent);
        log.info(boardId + "");
        Long memberId = (Long)session.getAttribute("memberId");
        freeReplyService.saveFreeBoardReply(freeReply, boardId, memberId);
        return "success";
    }

    //    자유 게시판 댓글 수정
    @PostMapping("update-reply/{replyId}")
    @ResponseBody
    public String writeReply(String updatedFreeReply, @PathVariable(value = "replyId") Long replyId){
        log.info("수정 들어옴");
        log.info(updatedFreeReply);

        FreeReply updatedReply = new FreeReply(updatedFreeReply);
        freeReplyService.setFreeReplyContent(updatedReply, replyId);
        return "success";
    }

    //    자유 게시판 댓글 삭제
    @PostMapping("delete-reply/{replyId}")
    @ResponseBody
    public String deleteReply(@PathVariable(value = "replyId") Long replyId){
        log.info("삭제 컨트롤러 들어옴");
        freeReplyService.removeFreeReply(replyId);
        return "success";
    }

    //    자유 게시판 댓글 리스트
    @GetMapping("replies/{boardId}/{page}")
    @ResponseBody
    public List<FreeReplyDTO> getReplies(@PathVariable(value = "boardId") Long boardId , @PathVariable(value = "page") int page){
        log.info("@@@@@@@@@@@@@@@@@@@들어옴@@@@@@@@@@@@@@@@@@@@");
        log.info(boardId + " || " + page);
        Pageable pageable = PageRequest.of(page,5);
        return freeReplyService.getFreeRepliesByBoardId(pageable, boardId);
    }

}