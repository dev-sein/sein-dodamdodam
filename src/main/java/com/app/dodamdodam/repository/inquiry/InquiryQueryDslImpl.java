package com.app.dodamdodam.repository.inquiry;

import com.app.dodamdodam.entity.inquiry.Inquiry;
import com.app.dodamdodam.entity.inquiry.QInquiry;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.search.Inquiry.AdminInquirySearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.app.dodamdodam.entity.inquiry.QInquiry.inquiry;
import static com.app.dodamdodam.entity.member.QMember.member;
import static com.app.dodamdodam.entity.point.QPoint.point;
@Service
@RequiredArgsConstructor
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

    @Override
    public Page<Inquiry> findInquiryWithSearch_QueryDSL(AdminInquirySearch inquirySearch, Pageable pageable) {
        BooleanExpression inquiryTypeEq = inquirySearch.getInquiryType() == null ? null : inquiry.inquiryType.eq(inquirySearch.getInquiryType());
        BooleanExpression inquiryEmailEq = inquirySearch.getInquiryEmail() == null ? null : inquiry.inquiryEmail.eq(inquirySearch.getInquiryEmail());
        BooleanExpression inquiryPhoneNumberEq = inquirySearch.getInquiryPhoneNumber() == null ? null : inquiry.inquiryPhoneNumber.eq(inquirySearch.getInquiryPhoneNumber());
        BooleanExpression inquiryStatusEq = inquirySearch.getInquiryStatus() == null ? null : inquiry.inquiryStatus.eq(inquirySearch.getInquiryStatus());

        List<Inquiry> inquiries = query.select(inquiry)
                .from(inquiry)
                .where(inquiryTypeEq, inquiryEmailEq, inquiryPhoneNumberEq, inquiryStatusEq)
                .orderBy(inquiry.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(inquiry.count()).from(inquiry).fetchOne();

        return new PageImpl<>(inquiries, pageable, count);

    }

}
