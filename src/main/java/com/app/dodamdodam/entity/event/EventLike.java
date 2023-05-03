package com.app.dodamdodam.entity.event;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "TBL_APPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventLike {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne
    @JoinColumn(name = "EVENT_BOARD_ID")
    private EventBoard eventBoard;

}
