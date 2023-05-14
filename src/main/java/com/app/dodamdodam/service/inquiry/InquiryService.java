package com.app.dodamdodam.service.inquiry;

import com.app.dodamdodam.domain.InquiryDTO;
import com.app.dodamdodam.entity.inquiry.Inquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface InquiryService {

    //문의사항 등록
    public void saveInquiry(InquiryDTO inquiryDTO);

    //관리자 : 문의사항 목록
    public Page<InquiryDTO> showList(Pageable pageable);

    default Inquiry toInquiryEntity(InquiryDTO inquiryDTO){
        return Inquiry.builder().id(inquiryDTO.getId()).inquiryEmail(inquiryDTO.getInquiryEmail()).inquiryContent(inquiryDTO.getInquiryContent())
                .inquiryPhoneNumber(inquiryDTO.getInquiryPhoneNumber()).inquiryType(inquiryDTO.getInquiryType()).inquiryStatus(inquiryDTO.getInquiryStatus())
                .inquiryAnswer(inquiryDTO.getInquiryAnswer()).build();
    }

    default InquiryDTO toInquiryDTO(Inquiry inquiry){
        return InquiryDTO.builder().id(inquiry.getId()).inquiryContent(inquiry.getInquiryContent())
                .inquiryEmail(inquiry.getInquiryEmail()).inquiryPhoneNumber(inquiry.getInquiryPhoneNumber())
                .inquiryStatus(inquiry.getInquiryStatus()).inquiryType(inquiry.getInquiryType())
                .inquiryAnswer(inquiry.getInquiryAnswer()).build();
    }
}
