package com.app.dodamdodam.repository.inquiry;

import com.app.dodamdodam.entity.inquiry.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long>, InquiryQueryDsl {
}
