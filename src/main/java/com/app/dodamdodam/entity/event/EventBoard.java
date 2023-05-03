package com.app.dodamdodam.entity.event;

import com.app.dodamdodam.entity.board.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@ToString
@Table(name = "TBL_BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventBoard extends Board {


}
