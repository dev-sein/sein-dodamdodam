package com.app.dodamdodam.controller.board.recruitment;

import com.app.dodamdodam.domain.RecruitmentBoardDTO;
import com.app.dodamdodam.domain.RecruitmentBoardFileDTO;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.free.FreeReply;
import com.app.dodamdodam.entity.recruitment.RecruitmentReply;
import com.app.dodamdodam.search.FreeBoardSearch;
import com.app.dodamdodam.service.board.freeBoard.FreeBoardService;
import com.app.dodamdodam.service.board.freeBoard.freeReply.FreeReplyService;
import com.app.dodamdodam.service.board.recruitmentBoard.RecruitmentBoardService;
import com.app.dodamdodam.service.board.recruitmentBoard.recruitmentReply.RecruitmentReplyService;
import com.app.dodamdodam.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("recruitment/*")
@RequiredArgsConstructor
@Slf4j
public class RecruitmentBoardController {
    private final RecruitmentBoardService recruitmentBoardService;
    private final RecruitmentReplyService recruitmentReplyService;
    private final MemberService memberService;

//    모집 게시판 메인(list)
    @GetMapping("list")
    public String recruitmentBoardList(Model model){

        return "recruitment-board/recruitment-board-list";
    }

//  모집 게시판 페이징처리(무한 스크롤)
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

    //    모집 게시판 상세
    @GetMapping("detail/{boardId}")
    public String freeBoardDetail(Model model, @PathVariable(value = "boardId") Long boardId, HttpSession session){
        Long memberId = (Long)session.getAttribute("memberId");
        log.info("들어옴");
        model.addAttribute("boardDetail", recruitmentBoardService.getRecruitmentBoardDetailByBoardId(boardId));
//        /* PageRequest는 뭐 넣어도 상관없이 개수 가져와서 아무렇게나 넣음 */
        model.addAttribute("replyCount", recruitmentReplyService.getRecruitmentRepliesCountByBoardId(PageRequest.of(0,5), boardId));

        return "recruitment-board/recruitment-board-detail";
    }

//  모집 게시글 삭제
    @GetMapping("delete-board/{boardId}")
    public RedirectView deleteBoard(@PathVariable(value = "boardId")Long boardId){
        log.info("삭제 컨트롤러 들어옴");
        recruitmentBoardService.deleteRecruitmentBoardByBoardId(boardId);
        return new RedirectView("/recruitment/list");
    }

//    모집 게시글 수정
    @GetMapping("update/{boardId}")
    public String updateFreeBoard(@PathVariable(value = "boardId") Long boardId, Model model){
        model.addAttribute("board",recruitmentBoardService.getAdminRecruitmentBoardDetail(boardId));

        return "recruitment-board/recruitment-board-update";
    }

//    모집 게시글 수정
    @PostMapping("update-board/{boardId}")
    public RedirectView updateFreeBoard(RecruitmentBoardFileDTO updatedBoard, String date, @PathVariable(value = "boardId") Long boardId){
        log.info("수정 들어옴");
        log.info("date : " + date);
        log.info("boardId : " + boardId);
        log.info(updatedBoard.toString());
//        날짜는 따로 String으로 받아와서 LocalDate 타입으로 변형해서 따로 DTO에 담아둠
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        updatedBoard.setRecruitmentDate(localDate);
//        수정
        recruitmentBoardService.updateRecruitmentBoard(updatedBoard, boardId);

//        FreeBoard updatedFreeBoard = new FreeBoard(updatedBoard.getId(), updatedBoard.getBoardTitle(), updatedBoard.getBoardContent(), updatedBoard.getFreeCategory());
//        freeBoardService.updateFreeBoard(updatedFreeBoard, boardId);
        return new RedirectView("/recruitment/detail/{boardId}");
    }

//    //    자유 게시글 수정 페이지
//    @GetMapping("update-board/{boardId}")
//    public String updateFreeBoard(@PathVariable(value = "boardId") Long boardId, Model model){
//        model.addAttribute("freeBoard",freeBoardService.getFreeBoardById(boardId));
//
//        return "free-board/free-board-update";
//    }
//
//
//    //    자유 게시글 수정
//    @PostMapping("update-board/{boardId}")
//    public RedirectView updateFreeBoard(FreeBoard updatedBoard, @PathVariable(value = "boardId") Long boardId){
//        log.info("수정 들어옴");
//        log.info(updatedBoard.toString());
//
//        FreeBoard updatedFreeBoard = new FreeBoard(updatedBoard.getId(), updatedBoard.getBoardTitle(), updatedBoard.getBoardContent(), updatedBoard.getFreeCategory());
//        freeBoardService.updateFreeBoard(updatedFreeBoard, boardId);
//        return new RedirectView("/free/detail/{boardId}");
//    }
//
//    //    자유 게시글 삭제
//    @GetMapping("delete-board/{boardId}")
//    public RedirectView deleteFreeBoard(@PathVariable(value = "boardId") Long boardId){
//        log.info("삭제 컨트롤러 들어옴");
//        freeBoardService.deleteFreeBoard(boardId);
//        return new RedirectView("/free/list");
//    }
    
    
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
