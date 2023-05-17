package com.app.dodamdodam.controller;

import com.app.dodamdodam.domain.*;
import com.app.dodamdodam.entity.inquiry.Inquiry;
import com.app.dodamdodam.service.board.freeBoard.FreeBoardService;
import com.app.dodamdodam.service.board.purchase.PurchaseBoardService;
import com.app.dodamdodam.service.inquiry.InquiryService;
import com.app.dodamdodam.service.member.MemberService;
import com.app.dodamdodam.service.point.PointService;
import com.app.dodamdodam.type.MemberStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admins/*")
@Slf4j
public class AdminController {
    private final InquiryService inquiryService;
    private final PointService pointService;
    private final FreeBoardService freeBoardService;
    private final PurchaseBoardService purchaseBoardService;
    private final MemberService memberService;

    /*문의 게시판*/
    @GetMapping("inquiry/list") //문의 게시판 목록
    public String adminInquiryList() {
        return "admin/inquiry-list";
    }

    @ResponseBody
    @GetMapping("inquiry/list-content")  //문의 게시판 목록
    public Page<InquiryDTO> adminInquiryGetListJson(@RequestParam(name = "page") int page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
//        Pageable pageable = PageRequest.of(page, 10);
        Page<InquiryDTO> inquiryAdminPages = inquiryService.showList(pageRequest);
        log.info("======================"+page);
        return inquiryAdminPages;
    }

    /*문의 삭제*/
    @DeleteMapping("inquiry/delete")
    @ResponseBody
    public ResponseEntity<String> deleteAdminInquires(@RequestBody List<Long> inquiryIds){
        inquiryService.deleteInquires(inquiryIds);
        return ResponseEntity.ok("게시물 삭제에 성공했습니다");
    }

    @ResponseBody
    @PostMapping("inquiry/search")
    public Page<AdminInquirySearchDTO> adminInquirySearch(){
        return null;
    }

    /*포인트 게시판 */
    @GetMapping("point/list") //포인트 게시판 목록
    public String adminPointList(){
        return "admin/point-list";
    }

    @ResponseBody
    @GetMapping("point/list/{page}")
    public Page<PointDTO> getPointList(@RequestParam(value = "page") Integer page) {
        Pageable pageable = PageRequest.of(page-1, 10);
        Page<PointDTO> pointAdminPage =  pointService.showList(pageable);
        return pointAdminPage;
    }


    /*자유 게시판 */
    @GetMapping("free-board/list") //자유 게시판 목록
    public String adminfreeBoardList(){
        return "admin/free-board-list";
    }

    @ResponseBody
    @GetMapping("free-board/list/{page}") //자유 게시판 목록
    public Page<FreeBoardFileDTO> getFreeBoardList(@RequestParam(value = "page") Integer page){
        Pageable pageable = PageRequest.of(page-1, 10);
        Page<FreeBoardFileDTO> freeBoardAdminPage = freeBoardService.getAdminFreeBoardList(pageable);
        return freeBoardAdminPage;
    }

    @DeleteMapping("free-board/delete") //자유게시글 삭제
    @ResponseBody
    public ResponseEntity<String> deleteAdminFreeBoard(@RequestBody List<Long> freeBoardIds){
        freeBoardService.deleteAdminFreeBoard(freeBoardIds);
        return ResponseEntity.ok("게시물 삭제에 성공했습니다");
    }


    /*판매 게시판 */
    @GetMapping("purchase-board/list") //판매 게시판 목록
    public String adminPurchaseBoardList(){
        return "admin/purchase-board-list";
    }

    @ResponseBody
    @GetMapping("purchase-board/list/{page}") //판매 게시판 목록
    public Page<PurchaseBoardDTO> getPurchaseBoardList(@RequestParam(value = "page") Integer page){
        Pageable pageable = PageRequest.of(page - 1, 10);
        Page<PurchaseBoardDTO> purchaseBoardAdminPage = purchaseBoardService.showList(pageable);
        return purchaseBoardAdminPage;
    }

    @DeleteMapping("purchase-board/delete") //판매 게시글 삭제
    @ResponseBody
    public ResponseEntity<String> deleteAdminPurchaseBoard(@RequestBody List<Long> purchaseBoardIds){
        purchaseBoardService.deleteAdminPurchaseBoard(purchaseBoardIds);
        return ResponseEntity.ok("게시물 삭제에 성공했습니다");
    }

    /*멤버 게시판*/
    @GetMapping("member/list") //멤버 목록
    public String adminMemberList(){ return "admin/member-list"; }

    @ResponseBody
    @GetMapping("member/list/{page}") //멤버 목록
    public Page<MemberDTO> getMemberList(@RequestParam(value = "page") Integer page){
        Pageable pageable = PageRequest.of(page-1, 10);
        Page<MemberDTO> memberAdminPage = memberService.showList(pageable);
        return memberAdminPage;
    }

    @PatchMapping("member/withdraw")
    @ResponseBody //회원 탈퇴(상태 변경)
    public ResponseEntity<String> setMemberStatus(@RequestBody List<Long> ids, MemberStatus memberStatus){
        memberService.setMemberStatus(ids, memberStatus);
        return ResponseEntity.ok("회원 상태 변경 완료하였습니다");
    }


}
