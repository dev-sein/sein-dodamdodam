package com.app.dodamdodam.repository.board.recruitment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.app.dodamdodam.entity.recruitment.QRecruitmentFile.recruitmentFile;


@RequiredArgsConstructor
public class RecruitmentFileQueryDslImpl implements RecruitmentFileQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public void deleteByEventBoardId(Long recruitmentId) {
        query.delete(recruitmentFile)
                .where(recruitmentFile.recruitmentBoard.id.eq(recruitmentId))
                .execute();
    }
}
