package com.app.dodamdodam.controller;

import com.app.dodamdodam.domain.*;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.recruitment.Recruitment;
import com.app.dodamdodam.service.banner.BannerApplyService;
import com.app.dodamdodam.service.board.freeBoard.FreeBoardService;
import com.app.dodamdodam.service.board.purchase.PurchaseBoardService;
import com.app.dodamdodam.service.board.recruitmentBoard.RecruitmentBoardService;
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
    private final RecruitmentBoardService recruitmentBoardService;
    private final BannerApplyService bannerApplyService;

    /*홈*/
    @GetMapping("/home")
    public String adminHome(){return "admin/home";}

    /*문의 게시판*/
    @GetMapping("inquiry/list") //문의 게시판 목록
    public String adminInquiryList() {
        return "admin/inquiry-list";
    }

    @ResponseBody
    @GetMapping("inquiry/list-content")  //문의 게시판 목록
    public Page<InquiryDTO> adminInquiryGetListJson(@RequestParam(name = "page") int page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<InquiryDTO> inquiryAdminPages = inquiryService.showList(pageRequest);
        return inquiryAdminPages;
    }

    @DeleteMapping("inquiry/delete") //문의 삭제
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


    @GetMapping("inquiry/detail/{id}") //문의 게시판 상세
    public String adminInquiryDetail(@PathVariable("id") Long inquiryId, Model model){
        InquiryDTO inquiryDTO = inquiryService.getAdminInquiryDetail(inquiryId);
        model.addAttribute("inquiryDTO", inquiryDTO);
        return "admin/inquiry-detail";
    }


    /*포인트 게시판 */
    @GetMapping("point/list") //포인트 게시판 목록
    public String adminPointList(){
        return "admin/point-list";
    }

    @ResponseBody
    @GetMapping("point/list-content")
    public Page<PointDTO> getPointList(@RequestParam(value = "page") int page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<PointDTO> pointAdminPage =  pointService.showList(pageRequest);
        return pointAdminPage;
    }


    /*자유 게시판 */
    @GetMapping("free-board/list") //자유 게시판 목록
    public String adminfreeBoardList(){
        return "admin/free-board-list";
    }

    @ResponseBody
    @GetMapping("free-board/list-content") //자유 게시판 목록
    public Page<FreeBoardFileDTO> getFreeBoardList(@RequestParam(value = "page") int page){
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<FreeBoardFileDTO> freeBoardAdminPage = freeBoardService.getAdminFreeBoardList(pageRequest);
        return freeBoardAdminPage;
    }

    @DeleteMapping("free-board/delete") //자유게시글 삭제
    @ResponseBody
    public ResponseEntity<String> deleteAdminFreeBoard(@RequestBody List<Long> freeBoardIds){
        freeBoardService.deleteAdminFreeBoard(freeBoardIds);
        return ResponseEntity.ok("게시물 삭제에 성공했습니다");
    }

    @GetMapping("free-board/detail/{id}") // 자유게시글 상세
    public String freeBoardDetail(@PathVariable("id") Long boardId, Model model) {
        FreeBoardFileDTO freeBoardFileDTO = freeBoardService.getAdminFreeBoardDetail(boardId);
        model.addAttribute("freeBoardFileDTO", freeBoardFileDTO);
        return "admin/free-board-detail";
    }

    /*판매 게시판 */
    @GetMapping("purchase-board/list") //판매 게시판 목록
    public String adminPurchaseBoardList(){
        return "admin/purchase-board-list";
    }

    @ResponseBody
    @GetMapping("purchase-board/list-content") //판매 게시판 목록
    public Page<PurchaseBoardDTO> getPurchaseBoardList(@RequestParam(value = "page") int page){
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<PurchaseBoardDTO> purchaseBoardAdminPage = purchaseBoardService.showList(pageRequest);
        return purchaseBoardAdminPage;
    }

    @DeleteMapping("purchase-board/delete") //판매 게시글 삭제
    @ResponseBody
    public ResponseEntity<String> deleteAdminPurchaseBoard(@RequestBody List<Long> purchaseBoardIds){
        purchaseBoardService.deleteAdminPurchaseBoard(purchaseBoardIds);
        return ResponseEntity.ok("게시물 삭제에 성공했습니다");
    }

    @GetMapping("purchase-board/detail/{id}") // 판매 게시글 상세
    public String purchaseBoardDetail(@PathVariable("id") Long boardId, Model model) {
        PurchaseBoardDTO purchaseBoardDTO = purchaseBoardService.getAdminPurchaseBoardDetail(boardId);
        model.addAttribute("purchaseBoardDTO", purchaseBoardDTO);
        return "admin/purchase-board-detail";
    }

    /*멤버 게시판*/
    @GetMapping("member/list") //멤버 목록
    public String adminMemberList(){ return "admin/member-list"; }

    @ResponseBody
    @GetMapping("member/list-content") //멤버 목록
    public Page<MemberDTO> getMemberList(@RequestParam(value = "page") int page){
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<MemberDTO> memberAdminPage = memberService.showList(pageRequest);
        return memberAdminPage;
    }

    @ResponseBody
    @GetMapping("member/detail/{memberId}") // 멤버 상세
    public ResponseEntity<MemberDTO> getMemberDetail(@PathVariable Long memberId) {
        MemberDTO memberDTO = memberService.getAdminMemberDetail(memberId);
        if (memberDTO != null) {
            return ResponseEntity.ok(memberDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("member/withdraw")
    @ResponseBody //회원 탈퇴(상태 변경)
    public ResponseEntity<String> setMemberStatus(@RequestBody List<Long> ids, MemberStatus memberStatus){
        memberService.setMemberStatus(ids, memberStatus);
        return ResponseEntity.ok("회원 상태 변경 완료하였습니다");
    }

    /*모집 게시판*/
    @GetMapping("recruitment-board/list") //모집 목록
    public String adminRecruitmentBoardList(){ return "admin/recruitment-board"; }

    @ResponseBody
    @GetMapping("recruitment-board/list-content")  //모집 게시판 목록
    public Page<RecruitmentBoardFileDTO> adminRecruitmentBoardGetListJson(@RequestParam(name = "page") int page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<RecruitmentBoardFileDTO> recruitmentBoardFileDTOPage = recruitmentBoardService.showList(pageRequest);
        return recruitmentBoardFileDTOPage;
    }

    @DeleteMapping("recruitment-board/delete") //모집 게시글 삭제
    @ResponseBody
    public ResponseEntity<String> deleteAdminRecruitmentBoard(@RequestBody List<Long> recruitmentBoardIds){
        recruitmentBoardService.deleteRecruitmentBoard(recruitmentBoardIds);
        return ResponseEntity.ok("게시물 삭제에 성공했습니다");
    }

    @GetMapping("recruitment-board/detail/{id}") // 모집 게시글 상세
    public String recruitmentBoardDetail(@PathVariable("id") Long boardId, Model model) {
        RecruitmentBoardFileDTO recruitmentBoardFileDTO = recruitmentBoardService.getAdminRecruitmentBoardDetail(boardId);
        model.addAttribute("recruitmentBoardFileDTO", recruitmentBoardFileDTO);
        return "admin/recruitment-board-detail";
    }

    /*배너 */
    @GetMapping("banner/list") //배너 목록
    public String adminBannerList(){return "admin/banner";}

    @ResponseBody
    @GetMapping("banner/list-content") //배너 신청 목록
    public Page<BannerDTO> adminBannerGetListJson(@RequestParam(name = "page") int page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<BannerDTO> bannerDTOPage = bannerApplyService.showList(pageRequest);
        return bannerDTOPage;
    }

    @GetMapping("banner/detail/{id}")  // 배너 상세 현황
    public String adminBannerDetail(@PathVariable("id") Long bannerApplyId, Model model) {
        BannerDTO bannerDTO = bannerApplyService.getAdminBannerDetail(bannerApplyId);
        model.addAttribute("bannerDTO", bannerDTO);
        return "admin/banner-detail";
    }

    @GetMapping("banner-management") // 배너 관리하기
    public String bannerManagement(){ return "admin/banner-management"; }

}
