package com.app.dodamdodam.service.inquiry;

import com.app.dodamdodam.domain.AdminInquirySearchDTO;
import com.app.dodamdodam.domain.InquiryDTO;
import com.app.dodamdodam.entity.inquiry.Inquiry;
import com.app.dodamdodam.repository.inquiry.InquiryRepository;
import com.app.dodamdodam.search.Inquiry.AdminInquirySearch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("inquiry") @Primary
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {
    private final InquiryRepository inquiryRepository;

    //문의사항 등록
    public void saveInquiry(InquiryDTO inquiryDTO){
//      Inquiry inquiry = inquiryDTO.toEntity();
    }

    @Override //문의사항 목록
    public Page<InquiryDTO> showList(Pageable pageable) {
        Page<Inquiry> inquiryPage = inquiryRepository.findAllWithPaging(pageable);
        List<InquiryDTO> inquiryDTOS = inquiryPage.get().map(this::toInquiryDTO).collect(Collectors.toList());

        return new PageImpl<>(inquiryDTOS, pageable, inquiryPage.getTotalElements());
    }

    @Override
    public Page<AdminInquirySearchDTO> showInquiryWithSearch_QueryDSL(AdminInquirySearch inquirySearch, Pageable pageable) {
        Page<Inquiry> inquiryPage = inquiryRepository.findInquiryWithSearch_QueryDSL(inquirySearch, pageable);
        List<AdminInquirySearchDTO> adminInquirySearchDTOS = inquiryPage.getContent().stream()
                .map(this::toAdminInquirySearchDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(adminInquirySearchDTOS, pageable, inquiryPage.getTotalElements());
    }

    @Override
    public void deleteInquires(List<Long> inquiryIds) {
        for(Long inquiryId: inquiryIds){
            inquiryRepository.deleteById(inquiryId);
        }
    }

    private AdminInquirySearchDTO toAdminInquirySearchDTO(Inquiry inquiry) {
        return AdminInquirySearchDTO.builder()
                .inquiryType(inquiry.getInquiryType())
                .inquiryEmail(inquiry.getInquiryEmail())
                .inquiryPhoneNumber(inquiry.getInquiryPhoneNumber())
                .inquiryStatus(inquiry.getInquiryStatus())
                .build();
    }

//    @Override
//    public Page<AdminInquirySearchDTO> showInquiryWithSearch_QueryDSL(AdminInquirySearch inquirySearch, Pageable pageable) {
//        Page<AdminInquirySearch> adminInquirySearchPage = inquiryRepository.findInquiryWithSearch_QueryDSL(inquirySearch, PageRequest.of(1, 10));
//        return null;
//    }

//    @Override
//    public Page<AdminInquirySearchDTO> showInquiryWithSearch_QueryDSL(AdminInquirySearch inquirySearch, Pageable pageable) {
//        Page<Inquiry> inquiryPage = inquiryRepository.findInquiryWithSearch_QueryDSL(inquirySearch, pageable);
//        List<AdminInquirySearchDTO> adminInquirySearchDTOS = inquiryPage.getContent().stream()
//                .map(this::toAdminInquirySearchDTO)
//                .collect(Collectors.toList());
//        return new PageImpl<>(adminInquirySearchDTOS, pageable, inquiryPage.getTotalElements());
//    }

}
