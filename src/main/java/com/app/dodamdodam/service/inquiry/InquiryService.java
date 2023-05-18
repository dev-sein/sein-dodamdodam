package com.app.dodamdodam.service.inquiry;

import com.app.dodamdodam.domain.AdminInquirySearchDTO;
import com.app.dodamdodam.domain.InquiryDTO;
import com.app.dodamdodam.entity.inquiry.Inquiry;
import com.app.dodamdodam.search.Inquiry.AdminInquirySearch;
import com.app.dodamdodam.search.member.AdminMemberSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InquiryService {

    //문의사항 등록
    public void register(InquiryDTO inquiryDTO);

    //관리자 : 문의사항 목록
    public Page<InquiryDTO> showList(Pageable pageable);

    //관리자 : 문의사항 검색
    public Page<AdminInquirySearchDTO> showInquiryWithSearch_QueryDSL(AdminInquirySearch inquirySearch, Pageable pageable);

    //관리자 : 문의사항 삭제
    public void deleteInquires(List<Long> inquiryIds);

    //관리자 : 문의사항 상세
    public InquiryDTO getAdminInquiryDetail(Long id);


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

    default AdminInquirySearchDTO toAdminInquirySearchDTO(AdminInquirySearch adminInquirySearch){
        return AdminInquirySearchDTO.builder().inquiryEmail(adminInquirySearch.getInquiryEmail()).inquiryPhoneNumber(adminInquirySearch.getInquiryPhoneNumber())
                .inquiryStatus(adminInquirySearch.getInquiryStatus()).inquiryType(adminInquirySearch.getInquiryType()).build();
    }
}
