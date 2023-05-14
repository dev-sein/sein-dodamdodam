package com.app.dodamdodam.entity.file;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
public class BoardFile {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String fileOriginalName;
    @NotNull private String fileUuid;
    @NotNull private String filePath;
     private Long fileSize;

    public BoardFile(String fileOriginalName, String fileUuid, String filePath, Long fileSize) {
        this.fileOriginalName = fileOriginalName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}
