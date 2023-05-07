package com.app.dodamdodam.repository.inquiry;


import com.app.dodamdodam.entity.inquiry.Inquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InquiryQueryDsl {
    public Page<Inquiry> findAllWithPaging(Pageable pageable);
}
