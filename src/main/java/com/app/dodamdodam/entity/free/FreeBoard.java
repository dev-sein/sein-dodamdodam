package com.app.dodamdodam.entity.free;

import com.app.dodamdodam.entity.board.Board;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_FREE_BOARD")
@Getter @ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class FreeBoard extends Board {
@ColumnDefault("'ALL'")
@Enumerated(EnumType.STRING)
    @NotNull private CategoryType freeCategory;


//    @OneToOne
//    private FreeLike freeLike;


}
