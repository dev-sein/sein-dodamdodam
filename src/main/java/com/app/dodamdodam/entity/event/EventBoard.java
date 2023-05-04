package com.app.dodamdodam.entity.event;

import com.app.dodamdodam.entity.board.Board;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@ToString(callSuper = true)
@Table(name = "TBL_EVENT_BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class EventBoard extends Board {
//    @Id @GeneratedValue
//    @EqualsAndHashCode.Include
//    private Long id;
    @NotNull private String eventAddress;
    @NotNull private String eventAddressDetail;
    @NotNull private LocalDate eventStartDate;
    @NotNull private LocalDate eventEndDate;
    @NotNull private String eventIntroduction;
    //    수락대기, 수락, 수락거절
    @ColumnDefault("'APPLYING'")
    @Enumerated(EnumType.STRING)
    @NotNull private EventType eventStatus;
    private String eventBusinessNumber;
    private String eventBusinessName;
    private String eventBusinessTel;
    private String eventBusinessEmail;

//    @OneToOne
//    private EventLike eventLike;
}