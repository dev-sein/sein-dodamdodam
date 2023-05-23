package com.app.dodamdodam.controller.board.recruitment;

import com.app.dodamdodam.domain.RecruitmentBoardDTO;
import com.app.dodamdodam.domain.RecruitmentBoardFileDTO;
import com.app.dodamdodam.service.board.recruitmentBoard.RecruitmentBoardService;
import com.app.dodamdodam.service.member.MemberService;
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
        model.addAttribute("boardDetail", recruitmentBoardService.getRecruitmentBoardDetailByBoardId(boardId));
//        model.addAttribute("freeBoardDetail", freeBoardService.getFreeBoardById(boardId));
//        /* PageRequest는 뭐 넣어도 상관없이 개수 가져와서 아무렇게나 넣음 */
//        model.addAttribute("replyCount",freeReplyService.getFreeRepliesCountByBoardId(PageRequest.of(0, 5), boardId));
//        model.addAttribute("recentFreeBoards",freeBoardService.getRecentFreeBoardList());
//
//        model.addAttribute("checkLike",freeBoardService.checkFreeLikeByBoardIdAndMemberId(boardId, memberId));
        return "recruitment-board/recruitment-board-detail";
    }

    @PostMapping("recruit")
    @ResponseBody
    public void getRecruit(@RequestBody Long boardId, HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");
        recruitmentBoardService.getRecruitment(memberId, boardId);
    }
    /* 이벤트 작성하기 */
    @GetMapping("write")
    public String goToWriteForm(HttpSession session) {
        /* 임시로 세션에 memberId 담아둠 */
        Long memberId = (Long) session.getAttribute("memberId");
        return "recruitment-board/recruitment-board-write";
    }

    @ResponseBody
    @PostMapping("write")
    public void getRecruitmentWriteForm(@RequestBody RecruitmentBoardDTO recruitmentBoardDTO, HttpSession session){
        log.info("recruitmentBoardDTO.toString()");
        log.info(recruitmentBoardDTO.toString());
        Long memberId = (Long) session.getAttribute("memberId");
        recruitmentBoardService.register(recruitmentBoardDTO, memberId);
    }

}
