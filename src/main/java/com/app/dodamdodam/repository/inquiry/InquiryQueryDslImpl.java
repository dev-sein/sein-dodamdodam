package com.app.dodamdodam.repository.inquiry;

import com.app.dodamdodam.entity.inquiry.Inquiry;
import com.app.dodamdodam.entity.inquiry.QInquiry;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.inquiry.QInquiry.inquiry;

public class InquiryQueryDslImpl implements InquiryQueryDsl {
    @Autowired
    private JPAQueryFactory query;

    @Override
    public Page<Inquiry> findAllWithPaging(Pageable pageable) {
        List<Inquiry> foundInquiry = query.select(inquiry)
                .from(inquiry)
                .orderBy(inquiry.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(inquiry.count())
                    .from(inquiry)
                    .fetchOne();

        return new PageImpl<>(foundInquiry, pageable, count);
    }

}
