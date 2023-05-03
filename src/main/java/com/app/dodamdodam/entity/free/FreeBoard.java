package com.app.dodamdodam.entity.free;

import com.app.dodamdodam.entity.board.Board;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_FREE_BOARD")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeBoard extends Board {
//    @Enumerated
    @NotNull private String freeCategory;


    @OneToOne
    private FreeLike freeLike;


}
