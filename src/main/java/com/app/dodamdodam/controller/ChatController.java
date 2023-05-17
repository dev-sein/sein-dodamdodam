package com.app.dodamdodam.controller;

import com.app.dodamdodam.domain.ChattingDTO;
import com.app.dodamdodam.domain.RoomDTO;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.service.chatting.ChatService;
import com.app.dodamdodam.service.chatting.ChatServiceImpl;
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

@RequiredArgsConstructor
@Controller
@RequestMapping("chat/*")
@Slf4j
public class ChatController {
    private final MemberService memberService;
    private final ChatService chatService;
    private final ChatServiceImpl chatServiceImpl;

    /*신규 채팅룸 생성*/
//    @PostMapping("/create")
//    @ResponseBody
//    public RoomDTO createRoom(Model model, HttpSession session) {
//        session.setAttribute("memberId", 199L);
//        Long memberId = (Long)session.getAttribute("memberId");
//        memberService.getMemberInfo(memberId).ifPresent(member -> model.addAttribute("member", member));
//        log.info("==============들어오니?================");
//        return chatServiceImpl.createRoom(500L);
//    }

//    @GetMapping("/room/find")
//    @ResponseBody
//    public List<RoomDTO> findAllRoom() {
//        return chatService.findAllRoom();
//    }

    /*로그인 된 맴버*/
    @GetMapping("main")
    public String goChat(Model model, HttpSession session) {
        //임의로 세션에 memberId값 담아둠
        session.setAttribute("memberId", 199L);
        Long memberId = (Long)session.getAttribute("memberId");
        memberService.getMemberInfo(memberId).ifPresent(member -> model.addAttribute("member", member));
        return "components/chat";
    }

    /*내가 참여한 채팅룸 목록*/
    @GetMapping("chat-room")
    public String myRoomList(HttpSession session, Model model){
        session.setAttribute("memberId", 199L);
        Long memberId = (Long)session.getAttribute("memberId");
        memberService.getMemberInfo(memberId).ifPresent(member -> model.addAttribute("member", member));
        return "components/chat";
    }

    /*내가 참여한 채팅룸 목록 무한 스크롤*/
    @ResponseBody
    @GetMapping("chat-room/{page}")
    public List<RoomDTO> myRoomList(HttpSession session, @PathVariable(value = "page") Integer page){
        session.setAttribute("memberId", 199L);
        Long memberId = (Long)session.getAttribute("memberId");

        //한번에 8개 씩
        Pageable pageable = PageRequest.of(page, 6);

        List<RoomDTO> roomDTOList = chatServiceImpl.getRoomByMemberId(pageable,memberId);
        log.info(roomDTOList.toString());
        return roomDTOList;
    }

    /*내가 참여한 채팅 목록*/
    @GetMapping("chatting")
    public String myChatList(HttpSession session, Model model, Long roomId){
        session.setAttribute("memberId", 199L);
        Long memberId = (Long)session.getAttribute("memberId");
        memberService.getMemberInfo(memberId).ifPresent(member -> model.addAttribute("member", member));
        return "components/chat";
    }

    /*내가 참여한 채팅룸 목록 무한 스크롤*/
    @ResponseBody
    @GetMapping("chatting/{page}")
    public List<ChattingDTO> myChatList(HttpSession session, @PathVariable(value = "page") Integer page, Long roomId){
        session.setAttribute("roomId", 300L);
        Long memberId = (Long)session.getAttribute("memberId");

        //한번에 10개 씩
        Pageable pageable = PageRequest.of(page, 10);

        List<ChattingDTO> chattingDTOList = chatServiceImpl.getChattingByRoomId(pageable, roomId, memberId);
        log.info(chattingDTOList.toString());
        return chattingDTOList;
    }
}
