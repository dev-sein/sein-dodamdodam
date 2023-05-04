package com.app.dodamdodam.entity.board;

import com.app.dodamdodam.audit.Period;
import com.app.dodamdodam.entity.file.File;
import com.app.dodamdodam.entity.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @ToString(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String boardTitle;
    private String boardContent;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
