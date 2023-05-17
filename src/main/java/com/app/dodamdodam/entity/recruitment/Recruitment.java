package com.app.dodamdodam.entity.recruitment;

import com.app.dodamdodam.entity.member.Member;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "TBL_RECRUITMENT")
@ToString(exclude = {"recruitmentBoard"})
//@ToString(exclude = {"member", "recruitmentBoard"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class Recruitment {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private RecruitmentBoard recruitmentBoard;

    public Recruitment(Member member) {
        this.member = member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setRecruitmentBoard(RecruitmentBoard recruitmentBoard) {
        this.recruitmentBoard = recruitmentBoard;
    }
}
