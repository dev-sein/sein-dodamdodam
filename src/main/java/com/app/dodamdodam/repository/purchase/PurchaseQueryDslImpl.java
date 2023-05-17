package com.app.dodamdodam.repository.purchase;

import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.repository.board.recruitment.RecruitmentBoardQueryDsl;
import com.app.dodamdodam.search.board.AdminRecruitmentSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.recruitment.QRecruitmentBoard.recruitmentBoard;

public class PurchaseQueryDslImpl implements PurchaseQueryDsl {
    @Autowired
    private JPAQueryFactory query;

}
