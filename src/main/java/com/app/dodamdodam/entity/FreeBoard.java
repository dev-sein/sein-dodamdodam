package com.app.dodamdodam.entity;

import com.app.dodamdodam.audit.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_FREE_BOARD")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeBoard extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String freeTitle;
    @NotNull private String freeContent;
//    @Enumerated
    @NotNull private String freeCategory;
//    @NotNull private LocalDateTime freeRegisterDate;
//    @NotNull private LocalDateTime freeUpdateDate;
}
