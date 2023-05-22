package com.app.dodamdodam.controller.board.event;

import com.app.dodamdodam.domain.EventBoardDTO;
import com.app.dodamdodam.domain.EventFileDTO;
import com.app.dodamdodam.search.EventBoardSearch;
import com.app.dodamdodam.service.board.eventBoard.EventBoardService;
import com.app.dodamdodam.type.EventType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/event/*")
@RequiredArgsConstructor
@Slf4j
public class EventBoardController {
    @Qualifier
    private final EventBoardService eventBoardService;


    @GetMapping("list")
    public String goList(Model model){
        log.info("@LLllllllllllllll");
        return "event-board/event-board-list";
    }

    //    이벤트 게시판 리스트(최신순)
    @ResponseBody
    @GetMapping("list-search")
    public List<EventBoardDTO> getEventBoardList(@RequestParam int page, @RequestParam String search, @RequestParam String eventType){
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        EventBoardSearch eventBoardSearch = new EventBoardSearch();
        eventBoardSearch.setBoardContent(search);
        eventBoardSearch.setBoardTitle(search);
        eventBoardSearch.setWriterName(search);
        EventType eventStatus = null;
        if (eventStatus.equals("전체")){
            eventStatus = EventType.ALL;
        } else if(eventStatus.equals("진행중")){
            eventStatus = EventType.PROGRESS;
        } else if (eventStatus.equals("진행전")){
            eventStatus = EventType.HOLD;
        } else if (eventStatus.equals("이벤트")){
            eventStatus = EventType.END;
        } else {
            eventStatus = null;
        }

        log.info(eventBoardSearch.toString());

        Pageable pageable = PageRequest.of(page,8);

        List<EventBoardDTO> eventboards = eventBoardService.getEventBoardsBySearch(pageable, eventBoardSearch, eventStatus);
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info(eventboards.toString());

        return eventboards;
    }


    //    이벤트 게시판 상세보기
    @GetMapping("detail/{id}")
    public String goEventDetail(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventBoardService.getDetail(id));
        return "event-board/event-board-detail";
    }

// 작성하기
    @GetMapping("write")
    public String goToWriteForm(HttpSession session) {
        /*임시로 세션에 memberId 담아둠*/
        session.setAttribute("memberId", 1L);

        return "event-board/event-board-write";
    }

    @PostMapping("write")
    public RedirectView write(EventBoardDTO eventBoardDTO, HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");
        log.info(eventBoardDTO.toString());

        // 파일 저장 로직 추가
        List<MultipartFile> eventFiles = eventBoardDTO.getEventFiles();
        List<EventFileDTO> fileDTOS = new ArrayList<>();

        if (eventFiles != null && !eventFiles.isEmpty()) {
            for (MultipartFile file : eventFiles) {
                // 파일을 저장하고 저장된 파일의 경로를 fileUrl에 설정
                String fileUrl = saveFile(file);

                // 파일 DTO 생성 및 정보 설정
                EventFileDTO fileDTO = EventFileDTO.builder()
                        .fileOriginalName(file.getOriginalFilename())
                        .fileUuid(UUID.randomUUID().toString())
                        .filePath(fileUrl)
                        .fileSize(file.getSize())
                        .build();

                fileDTOS.add(fileDTO);
            }
        }

        // 파일 DTO 리스트를 eventBoardDTO에 추가
        eventBoardDTO.setFileDTOS(fileDTOS);

        // eventBoardDTO를 사용하여 게시글 작성 등 필요한 처리 수행
        eventBoardService.write(eventBoardDTO, memberId);
        return new RedirectView("/event/list");
    }

    private String saveFile(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String basePath = "c:/upload/"; // 파일을 저장할 기본 경로
            String filePath = basePath + fileName; // 파일을 저장할 경로 설정
            File destFile = new File(filePath);
            file.transferTo(destFile); // 파일을 저장

            return filePath; // 저장된 파일의 경로 반환
        } catch (IOException e) {
            // 파일 저장 중에 예외가 발생한 경우 처리
            e.printStackTrace();
            return null; // 예외 발생 시 null 반환 또는 예외 처리 방식에 맞게 수정
        }
    }

}
