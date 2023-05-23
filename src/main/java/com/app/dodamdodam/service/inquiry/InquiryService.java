package com.app.dodamdodam.service.inquiry;

import com.app.dodamdodam.domain.InquiryDTO;
import com.app.dodamdodam.domain.MailDTO;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.inquiry.Inquiry;
import com.app.dodamdodam.search.Inquiry.AdminInquirySearch;
import com.app.dodamdodam.type.InquiryStatus;
import com.app.dodamdodam.type.InquiryType;
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
    public Page<InquiryDTO> showInquiryWithSearch_QueryDSL(Pageable pageable, AdminInquirySearch inquirySearch);

    //관리자 : 문의사항 삭제
    public void deleteInquires(List<Long> inquiryIds);

    //관리자 : 문의사항 상세
    public InquiryDTO getAdminInquiryDetail(Long id);
    
    //관리자 : 메일 답장 보내기 - 메일
    public void sendAnswerMail(Long id, MailDTO mailDTO);

    //관리자 : 문의사항 답장 보내기
    public void updateInquiryAnsewrStatus(Long inquiryId, InquiryDTO inquiryDTO);


    default Inquiry toInquiryEntity(InquiryDTO inquiryDTO){
        return Inquiry.builder().id(inquiryDTO.getId()).inquiryEmail(inquiryDTO.getInquiryEmail()).inquiryContent(inquiryDTO.getInquiryContent())
                .inquiryPhoneNumber(inquiryDTO.getInquiryPhoneNumber()).inquiryType(inquiryDTO.getInquiryType()).inquiryStatus(inquiryDTO.getInquiryStatus())
                .memberIdentification(inquiryDTO.getMemberIdentification())
                .inquiryAnswer(inquiryDTO.getInquiryAnswer()).build();
    }

    default InquiryDTO toInquiryDTO(Inquiry inquiry){
        return InquiryDTO.builder().id(inquiry.getId()).inquiryContent(inquiry.getInquiryContent())
                .inquiryEmail(inquiry.getInquiryEmail()).inquiryPhoneNumber(inquiry.getInquiryPhoneNumber())
                .inquiryStatus(inquiry.getInquiryStatus()).inquiryType(inquiry.getInquiryType())
                .memberIdentification(inquiry.getMemberIdentification())
                .inquiryAnswer(inquiry.getInquiryAnswer()).build();
    }

}
