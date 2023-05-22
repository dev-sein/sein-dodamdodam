package com.app.dodamdodam.controller;

import com.app.dodamdodam.domain.*;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.service.board.freeBoard.FreeBoardService;
import com.app.dodamdodam.service.board.purchase.PurchaseBoardService;
import com.app.dodamdodam.service.board.recruitmentBoard.RecruitmentBoardService;
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
import java.util.List;

@Controller
@RequestMapping("mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {
    private final MemberService memberService;

    private final FreeBoardService freeBoardService;

    private final PurchaseBoardService purchaseBoardService;

    private final RecruitmentBoardService recruitmentBoardService;

    /*마이 페이지 메인*/
    @GetMapping("main")
    public String myPageInfo(Model model, HttpSession session) {
        /* calendar 작업 추가로 해야함 */
//        임의로 세션에 memberId값 담아둠
        session.setAttribute("memberId", 5L);
        Long memberId =  (Long)session.getAttribute("memberId");
        model.addAttribute("member",memberService.getMemberInfo(memberId));
//        memberService.getMemberInfo(memberId).ifPresent(member -> model.addAttribute("member", member));
        model.addAttribute("point",memberService.getMyPointList(memberId));
        model.addAttribute("myGrade",memberService.getMemberGrade(memberId));
        return"myPage/myPage-Main";
    }

    /*마이 페이지 메인 테스트*/
    @GetMapping("main-test")
    public String myPageInfoTest(Model model, HttpSession session) {
        /* calendar 작업 추가로 해야함 */
//        임의로 세션에 memberId값 담아둠
        session.setAttribute("memberId", 5L);
        Long memberId =  (Long)session.getAttribute("memberId");
        model.addAttribute("member",memberService.getMemberInfo(memberId));
//        memberService.getMemberInfo(memberId).ifPresent(member -> model.addAttribute("member", member));
        model.addAttribute("point",memberService.getMyPointList(memberId));
        model.addAttribute("myGrade",memberService.getMemberGrade(memberId));
        return"myPage/mypage-main-test";
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
        session.setAttribute("memberId", 5L);
        Long memberId = (Long)session.getAttribute("memberId");
        model.addAttribute("member",memberService.getMemberInfo(memberId));
//        memberService.getMemberInfo(memberId).ifPresent(member -> model.addAttribute("member", member));
        model.addAttribute("points",memberService.getMyPointList(memberId));

        return"myPage/myPage-point";  /*테스트로 아무 페이지에나 보내봄*/
    }

    /* 내가 작성한 자유게시글 목록 */
    @GetMapping("free")
    public String myBoardList(HttpSession session, Model model){
        session.setAttribute("memberId", 5L);
        Long memberId = (Long)session.getAttribute("memberId");

        model.addAttribute("member",memberService.getMemberInfo(memberId));
//        memberService.getMemberInfo(memberId).ifPresent(member -> model.addAttribute("member", member));

        /* 내가 작성한 자유 게시글 개수 */
        model.addAttribute("freeBoardCount",memberService.getMyFreeBoardListCount(memberId));
        /* 내가 작성한 판매 게시글 개수 */
        model.addAttribute("purchaseBoardCount",memberService.getMyPurchaseBoardListCount(memberId));
        /* 내가 작성한 모집 게시글 개수 */
        model.addAttribute("recruitmentBoardCount",memberService.getMyRecruitmentBoardListCount(memberId));
        /* 내가 참여한 모집 게시글 개수 */
        model.addAttribute("recruitmentedBoardCount",memberService.getMyRecruitmentedBoardListCount(memberId));

        return "myPage/myPage-myFreeBoards";
    }

    /* 내가 작성한 자유게시글 목록 무한 스크롤 */
    @ResponseBody
    @GetMapping("free-board/{page}")
    public List<FreeBoardFileDTO> myFreeBoardList(HttpSession session, @PathVariable(value = "page") Integer page){
        session.setAttribute("memberId", 5L);
        Long memberId = (Long)session.getAttribute("memberId");

        /* 한번에 12개씩 */
        Pageable pageable = PageRequest.of(page,12);

        List<FreeBoardFileDTO> freeBoards = freeBoardService.getFreeBoardsByMemberId(pageable,memberId);

        log.info(freeBoards.toString());
        return freeBoards;
    }

    /* 내가 작성한 판매게시글 목록 */
    @GetMapping("purchase")
    public String myPurchaseBoardList(HttpSession session, Model model){
        session.setAttribute("memberId", 5L);
        Long memberId = (Long)session.getAttribute("memberId");

        model.addAttribute("member",memberService.getMemberInfo(memberId));
//        memberService.getMemberInfo(memberId).ifPresent(member -> model.addAttribute("member", member));

        /* 내가 작성한 자유 게시글 개수 */
        model.addAttribute("freeBoardCount",memberService.getMyFreeBoardListCount(memberId));
        /* 내가 작성한 판매 게시글 개수 */
        model.addAttribute("purchaseBoardCount",memberService.getMyPurchaseBoardListCount(memberId));
        /* 내가 작성한 모집 게시글 개수 */
        model.addAttribute("recruitmentBoardCount",memberService.getMyRecruitmentBoardListCount(memberId));
        /* 내가 참여한 모집 게시글 개수 */
        model.addAttribute("recruitmentedBoardCount",memberService.getMyRecruitmentedBoardListCount(memberId));

        return "myPage/myPage-myPurchaseBoards";
    }

    /* 내가 작성한 판매게시글 목록 무한 스크롤 */
    @ResponseBody
    @GetMapping("purchase-board/{page}")
    public List<PurchaseBoardFileDTO> myPurchaseBoardList(HttpSession session, @PathVariable(value = "page") Integer page){
        session.setAttribute("memberId", 5L);
        Long memberId = (Long)session.getAttribute("memberId");

        /* 한번에 12개씩 */
        Pageable pageable = PageRequest.of(page,12);

        List<PurchaseBoardFileDTO> purchaseBoards = purchaseBoardService.getPurchaseBoardListByMemberId(pageable,memberId);

        return purchaseBoards;
    }

    /* 내가 작성한 모집게시글 목록 */
    @GetMapping("recruitment")
    public String myRecruitmentBoardList(HttpSession session, Model model){
        session.setAttribute("memberId", 5L);
        Long memberId = (Long)session.getAttribute("memberId");

        model.addAttribute("member",memberService.getMemberInfo(memberId));
//        memberService.getMemberInfo(memberId).ifPresent(member -> model.addAttribute("member", member));

        /* 내가 작성한 자유 게시글 개수 */
        model.addAttribute("freeBoardCount",memberService.getMyFreeBoardListCount(memberId));
        /* 내가 작성한 판매 게시글 개수 */
        model.addAttribute("purchaseBoardCount",memberService.getMyPurchaseBoardListCount(memberId));
        /* 내가 작성한 모집 게시글 개수 */
        model.addAttribute("recruitmentBoardCount",memberService.getMyRecruitmentBoardListCount(memberId));
        /* 내가 참여한 모집 게시글 개수 */
        model.addAttribute("recruitmentedBoardCount",memberService.getMyRecruitmentedBoardListCount(memberId));

        return "myPage/myPage-myRecruitmentBoards";
    }

    /* 내가 작성한 모집게시글 목록 무한 스크롤 */
    @ResponseBody
    @GetMapping("recruitment-board/{page}")
    public List<RecruitmentBoardFileDTO> myRecruitmentBoardList(HttpSession session, @PathVariable(value = "page") Integer page){
        session.setAttribute("memberId", 5L);
        Long memberId = (Long)session.getAttribute("memberId");

        /* 한번에 12개씩 */
        Pageable pageable = PageRequest.of(page,12);

        List<RecruitmentBoardFileDTO> recruitmentBoards = recruitmentBoardService.getRecruimentBoardListByMemberId(pageable,memberId);

        log.info(recruitmentBoards.toString());
        return recruitmentBoards;
    }

    /* 내가 작성한 모집게시글에 참여한 회원 목록 */
    @ResponseBody
    @GetMapping("recruitments/{boardId}")
    public RecruitmentMemberDTO myRecruitmentMembers(@PathVariable(value = "boardId") Long boardId){
        RecruitmentMemberDTO recruitmentMembers = recruitmentBoardService.getRecruitmentedMembersByBoardId(boardId);
        log.info(recruitmentMembers.toString());
        return recruitmentMembers;
    }

    /* 내가 참가한 모집게시글 목록 */
    @GetMapping("recruitmented")
    public String myRecruitmentedBoardList(HttpSession session, Model model){
        session.setAttribute("memberId", 5L);
        Long memberId = (Long)session.getAttribute("memberId");

        model.addAttribute("member",memberService.getMemberInfo(memberId));
//        memberService.getMemberInfo(memberId).ifPresent(member -> model.addAttribute("member", member));

        /* 내가 작성한 자유 게시글 개수 */
        model.addAttribute("freeBoardCount",memberService.getMyFreeBoardListCount(memberId));
        /* 내가 작성한 판매 게시글 개수 */
        model.addAttribute("purchaseBoardCount",memberService.getMyPurchaseBoardListCount(memberId));
        /* 내가 작성한 모집 게시글 개수 */
        model.addAttribute("recruitmentBoardCount",memberService.getMyRecruitmentBoardListCount(memberId));
        /* 내가 참여한 모집 게시글 개수 */
        model.addAttribute("recruitmentedBoardCount",memberService.getMyRecruitmentedBoardListCount(memberId));

        return "myPage/myPage-myRecruitmentedBoards";
    }

    /* 내가 참가한 모집게시글 목록 무한 스크롤 */
    @ResponseBody
    @GetMapping("recruitmented-board/{page}")
    public List<RecruitmentBoardFileDTO> myRecruitmentedBoardList(HttpSession session, @PathVariable(value = "page") Integer page){
        session.setAttribute("memberId", 5L);
        Long memberId = (Long)session.getAttribute("memberId");

        /* 한번에 12개씩 */
        Pageable pageable = PageRequest.of(page,12);

        List<RecruitmentBoardFileDTO> recruitmentedBoards = recruitmentBoardService.getRecruimentedBoardListByMemberId(pageable,memberId);

        return recruitmentedBoards;
    }

    /* 회원 정보 수정 페이지 */
    @GetMapping("info")
    public String getMyInfo(HttpSession session, Model model){
        session.setAttribute("memberId", 5L);
        Long memberId = (Long)session.getAttribute("memberId");
        model.addAttribute("member",memberService.getMemberInfo(memberId));
//        memberService.getMemberInfo(memberId).ifPresent(member -> model.addAttribute("member",member));

        return "myPage/myPage-profileUpdate";
    }

    /* 회원 정보 수정 */
    @PostMapping("info/update")
    public RedirectView updateMemberInfo(MemberDTO memberDTO){
        log.info(memberDTO.toString());
        Member updatedMember = memberService.toMemberEntity(memberDTO);
        memberService.setMemberInfoById(memberDTO.getId(),updatedMember);
        return new RedirectView("/mypage/info?update=ok");
    }

    /* 회원탈퇴 페이지 */
    @GetMapping("withDrawl")
    public String memberWithDrawl(HttpSession session){
        session.setAttribute("memberId", 10L);
//        Long memberId = (Long)session.getAttribute("memberId");

        return "myPage/myPage-withDrawl";
    }

    /* 회원탈퇴 (회원 비활성화)*/
    @PostMapping("withDrawl")
    public RedirectView setMemberStatus(HttpSession session){
        Long memberId = (Long)session.getAttribute("memberId");
        memberService.setMemberStatusById(memberId);
        session.invalidate();
        return new RedirectView("/main");
    }

    /* 비밀번호 변경 페이지 */
    @GetMapping("change-password")
    public String changePasswordPage(HttpSession session){
        session.setAttribute("memberId", 11L);

        return "myPage/myPage-password";
    }


    /* 비밀번호 변경 */
    @PostMapping("change-password")
    public RedirectView changePassword(HttpSession session, String memberPassword){
        Long memberId = (Long)session.getAttribute("memberId");
        memberService.setMemberPasswordById(memberId,memberPassword);
        return new RedirectView("/mypage/change-password?update=ok");
    }

    /* 추가 */
}