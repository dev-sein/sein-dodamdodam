package com.app.dodamdodam.repository.recruitment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class RecruitmentQueryDslImpl implements RecruitmentQueryDsl {
    @Autowired
    private JPAQueryFactory query;


}
