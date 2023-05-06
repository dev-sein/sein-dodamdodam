package com.app.dodamdodam.entity.free;

import com.app.dodamdodam.entity.board.Board;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.type.CategoryType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "freeBoard")
    private List<FreeFile> freeFiles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "freeBoard")
    private List<FreeReply> freeReplies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public FreeBoard(CategoryType freeCategory) {
        this.freeCategory = freeCategory;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
