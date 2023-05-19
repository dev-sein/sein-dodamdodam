package com.app.dodamdodam.repository.inquiry;

import com.app.dodamdodam.entity.inquiry.Inquiry;
import com.app.dodamdodam.entity.inquiry.QInquiry;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.search.Inquiry.AdminInquirySearch;
import com.app.dodamdodam.type.InquiryStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.app.dodamdodam.entity.free.QFreeBoard.freeBoard;
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

/*    @Override
    public Page<Inquiry> findInquiryWithSearch_QueryDSL(AdminInquirySearch inquirySearch, Pageable pageable) {
//        BooleanExpression inquiryTypeEq = inquirySearch.getInquiryType() == null ? null : inquiry.inquiryType.eq(inquirySearch.getInquiryType());
        BooleanExpression inquiryEmailEq = inquirySearch.getInquiryEmail() == null ? null : inquiry.inquiryEmail.eq(inquirySearch.getInquiryEmail());
        BooleanExpression inquiryPhoneNumberEq = inquirySearch.getInquiryPhoneNumber() == null ? null : inquiry.inquiryPhoneNumber.eq(inquirySearch.getInquiryPhoneNumber());
        BooleanExpression inquiryContentEq = inquirySearch.getInquiryContent() == null ? null : inquiry.inquiryContent.eq(inquirySearch.getInquiryContent());
//        BooleanExpression inquiryStatusEq = inquirySearch.getInquiryStatus() == null ? null : inquiry.inquiryStatus.eq(convertToInquiryStatus(inquirySearch.getInquiryStatus().toString()));

        List<Inquiry> inquiries = query.select(inquiry)
                .from(inquiry)
                .where(inquiryEmailEq, inquiryPhoneNumberEq, inquiryContentEq)
                .orderBy(inquiry.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(inquiry.count()).from(inquiry).fetchOne();

        return new PageImpl<>(inquiries, pageable, count);
    }*/
        @Override
        public Page<Inquiry> findInquiryWithSearch_QueryDSL(AdminInquirySearch inquirySearch, Pageable pageable) {
       /*     BooleanExpression searchEmail = inquirySearch.getInquiryEmail() == null ? null : inquiry.inquiryEmail.contains(inquirySearch.getInquiryEmail());
            BooleanExpression searchPhoneNumber = inquirySearch.getInquiryPhoneNumber() == null ? null : inquiry.inquiryPhoneNumber.contains(inquirySearch.getInquiryPhoneNumber());
            BooleanExpression searchContent = inquirySearch.getInquiryContent() == null ? null : inquiry.inquiryContent.contains(inquirySearch.getInquiryContent());
*/
/*            BooleanExpression searchAll = inquirySearch.getInquiryContent() == null && inquirySearch.getInquiryEmail() == null && inquirySearch.getInquiryPhoneNumber() == null ? null
                    :(inquiry.inquiryContent.contains(inquirySearch.getInquiryContent())
                    .or(inquiry.inquiryEmail.contains(inquirySearch.getInquiryEmail())).or(inquiry.inquiryPhoneNumber.contains(inquirySearch.getInquiryPhoneNumber())));*/

            BooleanExpression searchAll = null;
            if (inquirySearch.getInquiryContent() != null || inquirySearch.getInquiryEmail() != null || inquirySearch.getInquiryPhoneNumber() != null) {
                BooleanExpression searchContent = inquirySearch.getInquiryContent() != null ? inquiry.inquiryContent.contains(inquirySearch.getInquiryContent()) : null;
                BooleanExpression searchEmail = inquirySearch.getInquiryEmail() != null ? inquiry.inquiryEmail.contains(inquirySearch.getInquiryEmail()) : null;
                BooleanExpression searchPhoneNumber = inquirySearch.getInquiryPhoneNumber() != null ? inquiry.inquiryPhoneNumber.contains(inquirySearch.getInquiryPhoneNumber()) : null;

                searchAll = searchContent.or(searchEmail).or(searchPhoneNumber);
            }

            List<Inquiry> inquiries = query.select(inquiry)
                    .from(inquiry)
                    .where(searchAll)
                    .orderBy(inquiry.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
            Long count = query.select(inquiry.count()).from(inquiry).where(searchAll).fetchOne();

            return new PageImpl<>(inquiries, pageable, count);
        }



}
