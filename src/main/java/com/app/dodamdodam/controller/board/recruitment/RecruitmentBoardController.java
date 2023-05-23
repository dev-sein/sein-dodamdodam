package com.app.dodamdodam.controller.board.recruitment;

import com.app.dodamdodam.domain.FreeBoardDTO;
import com.app.dodamdodam.domain.FreeBoardFileDTO;
import com.app.dodamdodam.domain.FreeReplyDTO;
import com.app.dodamdodam.domain.RecruitmentBoardFileDTO;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.free.FreeReply;
import com.app.dodamdodam.search.FreeBoardSearch;
import com.app.dodamdodam.service.board.freeBoard.FreeBoardService;
import com.app.dodamdodam.service.board.freeBoard.freeReply.FreeReplyService;
import com.app.dodamdodam.service.board.recruitmentBoard.RecruitmentBoardService;
import com.app.dodamdodam.service.member.MemberService;
import com.app.dodamdodam.type.CategoryType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("recruitment/*")
@RequiredArgsConstructor
@Slf4j
public class RecruitmentBoardController {
    private final RecruitmentBoardService recruitmentBoardService;
    private final MemberService memberService;

//    모집 게시판 메인(list)
    @GetMapping("list")
    public String recruitmentBoardList(Model model){

        return "recruitment-board/recruitment-board-list";
    }


    @GetMapping("list-paging")
    @ResponseBody
    public List<RecruitmentBoardFileDTO> getRecruitmentBoardList(@RequestParam int page){
        log.info("paging 들어옴");
        log.info("page : " + page);

        /* 12 <- 한번에 가져오는 데이터 수*/
        Pageable pageable = PageRequest.of(page,12);
        
        List<RecruitmentBoardFileDTO> recruitmentBoardFileDTOS = recruitmentBoardService.getRecruitmentBoardListByPaging(pageable);
        log.info(recruitmentBoardFileDTOS.toString());

        return recruitmentBoardFileDTOS;
    }

    //    자유 게시판 상세
    @GetMapping("detail/{boardId}")
    public String freeBoardDetail(Model model, @PathVariable(value = "boardId") Long boardId, HttpSession session){
        Long memberId = (Long)session.getAttribute("memberId");
        log.info("들어옴");
//        model.addAttribute("boardDeatail",recruitmentBoardService.);
//        model.addAttribute("freeBoardDetail", freeBoardService.getFreeBoardById(boardId));
//        model.addAttribute("top5",freeBoardService.getTop5FreeBoards());
//        /* PageRequest는 뭐 넣어도 상관없이 개수 가져와서 아무렇게나 넣음 */
//        model.addAttribute("replyCount",freeReplyService.getFreeRepliesCountByBoardId(PageRequest.of(0, 5), boardId));
//        model.addAttribute("recentFreeBoards",freeBoardService.getRecentFreeBoardList());
//
//        model.addAttribute("checkLike",freeBoardService.checkFreeLikeByBoardIdAndMemberId(boardId, memberId));
        return "recruitment-board/recruitment-board-detail";
    }

}
