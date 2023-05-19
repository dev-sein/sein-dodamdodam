package com.app.dodamdodam.service;

import com.app.dodamdodam.domain.EventFileDTO;
import com.app.dodamdodam.repository.file.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public List<EventFileDTO> getFileList(LocalDateTime date){
        return fileRepository.findAllFiles(date);
    }
}