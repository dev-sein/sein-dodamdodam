package com.app.dodamdodam.repository.file;

import com.app.dodamdodam.domain.FileDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface FileQueryDsl {
    public List<FileDTO> findAllFiles(LocalDateTime date);
}
