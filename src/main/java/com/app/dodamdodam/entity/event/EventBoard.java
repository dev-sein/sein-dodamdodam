package com.app.dodamdodam.entity.event;

import com.app.dodamdodam.entity.board.Board;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@ToString
@Table(name = "TBL_EVENT_BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventBoard extends Board {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String eventAddress;
    @NotNull private String eventAddressDetail;
    @NotNull private LocalDate eventStartDate;
    @NotNull private LocalDate eventEndDate;
    @NotNull private String eventIntroduction;
    @NotNull private String eventStatus;
    private String eventBusinessNumber;
    private String eventBusinessName;
    private String eventBusinessTel;
    private String eventBusinessEmail;
}