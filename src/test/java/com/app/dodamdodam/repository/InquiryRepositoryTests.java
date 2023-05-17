package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.inquiry.Inquiry;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.repository.inquiry.InquiryQueryDsl;
import com.app.dodamdodam.repository.inquiry.InquiryQueryDslImpl;
import com.app.dodamdodam.repository.inquiry.InquiryRepository;
import com.app.dodamdodam.search.Inquiry.AdminInquirySearch;
import com.app.dodamdodam.type.InquiryStatus;
import com.app.dodamdodam.type.InquiryType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.annotation.Rollback;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class InquiryRepositoryTests {

    @Autowired
    public InquiryRepository inquiryRepository;


    @Test //문의사항 저장 테스트
    public void saveTest(){
        for(int i=0; i<50; i++) {
            Inquiry inquiry = new Inquiry(
                    InquiryType.APPLY,
                    "abcd"+(i+1) +"@email.com",
                    "memberId" + (i+1),
                    "0101111111" + (i+1),
                    "문의내용" + (i+1),
                    InquiryStatus.HOLD);

            inquiryRepository.save(inquiry);
        }
    }


    @Test //답변 저장 테스트
    public void updateAnswerTest(){
      inquiryRepository.findById(162L).ifPresent(inquiry -> inquiry.setInquiryAnswer("답변내용"));
      inquiryRepository.findById(162L).ifPresent((inquiry -> inquiry.setInquiryStatus(InquiryStatus.DONE)));
    }

    @Test //전체 조회 테스트
    public void findAllTest(){
        inquiryRepository.findAll().forEach(inquiry -> log.info(inquiry.getInquiryContent().toString()));
    }

    @Test //문의사항 페이징 테스트
    public void findAllWithPagingTest(){
        Page<Inquiry> inquiryPage = inquiryRepository.findAll(PageRequest.of(0, 10));
        inquiryPage.getContent().stream().map(Inquiry::toString).forEach(log::info);
    }

    @Test
    public void findById(){
        inquiryRepository.findById(1L).ifPresent(inquiry -> log.info(inquiry.toString()));
    }

    @Test //문의 삭제 테스트
    public void deleteTest(){
        inquiryRepository.findById(1L).ifPresent(inquiry -> inquiryRepository.delete(inquiry));
    }

    @Test //문의사항 검색 테스트
    public void findInquiryWithSearch_QueryDSL_Test(){
        AdminInquirySearch adminInquirySearch = new AdminInquirySearch();
        adminInquirySearch.setInquiryEmail("abcd13@email.com");
        Page<Inquiry> inquiryPage = inquiryRepository.findInquiryWithSearch_QueryDSL(adminInquirySearch, PageRequest.of(0, 10));
        log.info(""+inquiryPage.getContent());
    }
}
