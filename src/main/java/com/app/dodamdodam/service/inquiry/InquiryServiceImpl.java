package com.app.dodamdodam.service.inquiry;

import com.app.dodamdodam.domain.InquiryDTO;
import com.app.dodamdodam.entity.inquiry.Inquiry;
import com.app.dodamdodam.repository.inquiry.InquiryRepository;
import com.app.dodamdodam.search.Inquiry.AdminInquirySearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {
    private final InquiryRepository inquiryRepository;

    //문의사항 등록
    public void saveInquiry(InquiryDTO inquiryDTO){
//      Inquiry inquiry = inquiryDTO.toEntity();
    }

    @Override //문의사항 목록
    public Page<InquiryDTO> showList(Pageable pageable) {
        Page<Inquiry> inquiryPage = inquiryRepository.findAllWithPaging(PageRequest.of(1, 10));
        List<InquiryDTO> inquiryDTOS = inquiryPage.get().map(this::toInquiryDTO).collect(Collectors.toList());

        return new PageImpl<>(inquiryDTOS, pageable, inquiryPage.getTotalElements());
    }


}
