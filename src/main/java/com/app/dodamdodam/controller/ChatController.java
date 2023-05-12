package com.app.dodamdodam.controller;

import com.app.dodamdodam.domain.RoomDTO;
import com.app.dodamdodam.service.chatting.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat/*")
@Slf4j
public class ChatController {
    private final ChatService chatService;

//    @PostMapping("create")
//    public RoomDTO createRoom(@RequestBody Long roomId) {
//        return chatService.createRoom(roomId);
//    }
    @PostMapping("create")
    public RoomDTO createRoom(@RequestBody Long hostId, @RequestBody Long havingId) {
        log.info("==============들어오니?================");
        return chatService.createRoom(hostId, havingId);
    }

    @GetMapping
    public List<RoomDTO> findAllRoom() {
        return chatService.findAllRoom();
    }
}
