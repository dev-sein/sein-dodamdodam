package com.app.dodamdodam.entity.free;

import com.app.dodamdodam.entity.board.Board;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.type.CategoryType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.util.ArrayList;
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

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "freeBoard", cascade = CascadeType.REMOVE)
    private List<FreeFile> freeFiles = new ArrayList<>();;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "freeBoard", cascade = CascadeType.REMOVE)
    private List<FreeReply> freeReplies = new ArrayList<>();;

    @NotNull
    private int likeCount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void setFreeFiles(List<FreeFile> freeFiles) {
        this.freeFiles = freeFiles;
    }

    public FreeBoard(Long id, String boardTitle, String boardContent, CategoryType freeCategory) {
        super(id, boardTitle, boardContent);
        this.freeCategory = freeCategory;
    }

    @Builder
    public FreeBoard(Long id, String boardTitle, String boardContent, CategoryType freeCategory, List<FreeFile> freeFiles, List<FreeReply> freeReplies, int likeCount, Member member) {
        super(id, boardTitle, boardContent);
        this.freeCategory = freeCategory;
        this.freeFiles = freeFiles;
        this.freeReplies = freeReplies;
        this.likeCount = likeCount;
        this.member = member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void updateLikePlus(){
        this.likeCount++;
    }

    public void updateLikeMinus(){
        this.likeCount--;
    }
    /* 추가 */
}
