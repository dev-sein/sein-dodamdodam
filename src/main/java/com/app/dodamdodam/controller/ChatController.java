package com.app.dodamdodam.controller;

import com.app.dodamdodam.domain.RoomDTO;
import com.app.dodamdodam.service.chatting.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
@Slf4j
public class ChatController {
    private final ChatService chatService;

//    @PostMapping("create")
//    public RoomDTO createRoom(@RequestBody Long roomId) {
//        return chatService.createRoom(roomId);
//    }

    @GetMapping("")
    public String goChat() { return "chat/chat"; }

    @PostMapping("/create")
    @ResponseBody
    public RoomDTO createRoom() {
        log.info("==============들어오니?================");
        return chatService.createRoom(1L);
    }

    @GetMapping("/room/find")
    @ResponseBody
    public List<RoomDTO> findAllRoom() {
        return chatService.findAllRoom();
    }
}
