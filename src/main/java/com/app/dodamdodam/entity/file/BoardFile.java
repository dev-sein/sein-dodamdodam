package com.app.dodamdodam.entity.file;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
public class BoardFile {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String fileOriginalName;
    private String fileUuid;
    private String filePath;
    private Long fileSize;

    public BoardFile(String fileOriginalName, String fileUuid, String filePath, Long fileSize) {
        this.fileOriginalName = fileOriginalName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}
