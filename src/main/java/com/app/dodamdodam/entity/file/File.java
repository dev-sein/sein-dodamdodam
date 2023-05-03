package com.app.dodamdodam.entity.file;

import com.app.dodamdodam.entity.board.Board;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString
@Table(name = "TBL_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String fileOriginalName;
    private String fileUuid;
    private String filePath;
    private Long fileSize;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;

}
