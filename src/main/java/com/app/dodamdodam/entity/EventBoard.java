package com.app.dodamdodam.entity;

import com.app.dodamdodam.audit.Period;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@ToString
@Table(name = "TBL_EVENT_BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventBoard extends Period {


}
