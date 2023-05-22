package com.app.dodamdodam.entity.file;

import com.app.dodamdodam.type.FileType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public abstract class BoardFile {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private String fileOriginalName;

    @NotNull
    private String fileUuid;
    @NotNull
    private String filePath;
    private FileType fileType;

    private Long fileSize;

    public BoardFile(Long id, String fileOriginalName, String fileUuid, String filePath, FileType fileType, Long fileSize) {
        this.id = id;
        this.fileOriginalName = fileOriginalName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }
}
