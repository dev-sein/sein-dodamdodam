package com.app.dodamdodam.entity.recruitment;

import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.reply.Reply;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true,exclude = "recruitmentBoard")
@Table(name = "TBL_RECRUITMENT_REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitmentReply extends Reply {
//    @Id @GeneratedValue
//    @EqualsAndHashCode.Include
//    private Long id;
//    private String replyContent;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECRUITMENT_BOARD_ID")
    private RecruitmentBoard recruitmentBoard;

    public void setRecruitmentBoard(RecruitmentBoard recruitmentBoard) {
        this.recruitmentBoard = recruitmentBoard;
    }

    @Builder
    public RecruitmentReply(RecruitmentBoard recruitmentBoard) {
        this.recruitmentBoard = recruitmentBoard;
    }

    @Builder
    public RecruitmentReply(String replyContent) {
        super.setReplyContent(replyContent);
    }

    @Builder
    public RecruitmentReply(String replyContent, Member member, RecruitmentBoard recruitmentBoard) {
        super(replyContent, member);
        this.recruitmentBoard = recruitmentBoard;
    }
}
