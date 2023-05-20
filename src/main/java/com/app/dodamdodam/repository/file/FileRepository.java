package com.app.dodamdodam.repository.file;

import com.app.dodamdodam.entity.file.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<BoardFile, Long>, FileQueryDsl {
}
