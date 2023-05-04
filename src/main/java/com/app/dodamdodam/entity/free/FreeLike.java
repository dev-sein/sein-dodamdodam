package com.app.dodamdodam.entity.free;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_FREE_BOARD_LIKE")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeLike {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;


    @OneToOne
    @JoinColumn(name = "FreeBoard")
    private FreeBoard freeBoard;

}
