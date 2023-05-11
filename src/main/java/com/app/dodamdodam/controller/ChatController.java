package com.app.dodamdodam.controller;

import com.app.dodamdodam.domain.RoomDTO;
import com.app.dodamdodam.service.chatting.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat/*")
public class ChatController {
    private final ChatService chatService;

//    @PostMapping("create")
//    public RoomDTO createRoom(@RequestBody Long roomId) {
//        return chatService.createRoom(roomId);
//    }
    @PostMapping("create")
    public RoomDTO createRoom(@RequestBody Long hostId) {
        return chatService.createRoom(hostId);
    }

    @GetMapping
    public List<RoomDTO> findAllRoom() {
        return chatService.findAllRoom();
    }
}
