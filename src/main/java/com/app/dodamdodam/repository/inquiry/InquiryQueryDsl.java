package com.app.dodamdodam.repository.inquiry;


import com.app.dodamdodam.domain.MailDTO;
import com.app.dodamdodam.entity.inquiry.Inquiry;
import com.app.dodamdodam.search.Inquiry.AdminInquirySearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public interface InquiryQueryDsl {
    //문의사항 페이징
    public Page<Inquiry> findAllWithPaging(Pageable pageable);

    //문의사항 정보 검색
    public Page<Inquiry> findInquiryWithSearch_QueryDSL(AdminInquirySearch inquirySearch, Pageable pageable);

}
