package com.app.dodamdodam.controller;

import com.app.dodamdodam.service.board.freeBoard.FreeBoardService;
import com.app.dodamdodam.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {
    private final MemberService memberService;

    private final FreeBoardService freeBoardService;

    /*마이 페이지 메인*/
    @GetMapping("main")
    public String myPageInfo(Model model, HttpSession session) {
        /* calendar 작업 추가로 해야함 */
//        임의로 세션에 memberId값 담아둠
        session.setAttribute("memberId", 2L);
        Long memberId =  (Long)session.getAttribute("memberId");
        memberService.getMemberInfo(memberId).ifPresent(member -> model.addAttribute("member", member));
        model.addAttribute("point",memberService.getMyPointList(memberId));
        return"myPage/myPage-Main";
    }

    /* 마이페이지 프로필*/

    /* 내가 작성한 자유 게시글*/
    @GetMapping("free-board")
    public String myPageFreeBoard(Model model, HttpSession session) {
        log.info("들어옴@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        Pageable pageable = PageRequest.of(0, 10);
        model.addAttribute("freeBoard",freeBoardService.getAllFreeBoards(pageable));

        return"myPage/myPage-Main";  /*테스트로 아무 페이지에나 보내봄*/
    }

    /* 포인트 내역 */
    @GetMapping("point")
    public String myPointList(Model model, HttpSession session) {
        log.info("들어옴@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        session.setAttribute("memberId", 2L);
        Long memberId = (Long)session.getAttribute("memberId");
        memberService.getMemberInfo(memberId).ifPresent(member -> model.addAttribute("member", member));
        model.addAttribute("points",memberService.getMyPointList(memberId));

        return"myPage/myPage-point";  /*테스트로 아무 페이지에나 보내봄*/
    }

    @GetMapping("board")
    public String myBoardList(HttpSession session, Model model){
        session.setAttribute("memberId", 5L);
        Long memberId = (Long)session.getAttribute("memberId");
        memberService.getMemberInfo(memberId).ifPresent(member -> model.addAttribute("member", member));
        log.info(memberService.getMyFreeBoardListCount(memberId).toString());
        return "myPage/myPage-myBoards";
    }
}
