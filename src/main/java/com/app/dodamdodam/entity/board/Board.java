package com.app.dodamdodam.entity.board;

import com.app.dodamdodam.audit.Period;
import com.app.dodamdodam.entity.file.File;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@Table(name = "TBL_BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String boardTitle;
    private String boardContent;

    @OneToMany(mappedBy = "board")
    private List<File> files = new ArrayList<>();
}
