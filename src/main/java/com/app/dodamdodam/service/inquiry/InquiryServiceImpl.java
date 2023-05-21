package com.app.dodamdodam.service.inquiry;

import com.app.dodamdodam.domain.InquiryDTO;
import com.app.dodamdodam.entity.inquiry.Inquiry;
import com.app.dodamdodam.repository.inquiry.InquiryRepository;
import com.app.dodamdodam.search.Inquiry.AdminInquirySearch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("inquiry") @Primary
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {
    private final InquiryRepository inquiryRepository;

    //문의사항 등록
    public void register(InquiryDTO inquiryDTO){
        inquiryRepository.save(toInquiryEntity(inquiryDTO));
    }

    //관리자 : 문의사항 목록
    @Override
    public Page<InquiryDTO> showList(Pageable pageable) {
        Page<Inquiry> inquiryPage = inquiryRepository.findAllWithPaging(pageable);
        List<InquiryDTO> inquiryDTOS = inquiryPage.get().map(this::toInquiryDTO).collect(Collectors.toList());

        return new PageImpl<>(inquiryDTOS, pageable, inquiryPage.getTotalElements());
    }

    //관리자 : 문의사항 검색
    @Override
    public Page<InquiryDTO> showInquiryWithSearch_QueryDSL(Pageable pageable, AdminInquirySearch inquirySearch) {
        Page<Inquiry> inquiryPage = inquiryRepository.findInquiryWithSearch_QueryDSL(inquirySearch, pageable);
        List<InquiryDTO> adminInquirySearchDTOS = inquiryPage.getContent().stream()
                .map(this::toInquiryDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(adminInquirySearchDTOS, pageable, inquiryPage.getTotalElements());
    }

    //관리자 : 문의사항 삭제
    @Override
    public void deleteInquires(List<Long> inquiryIds) {
        for(Long inquiryId: inquiryIds){
            inquiryRepository.deleteById(inquiryId);
        }
    }

    //관리자 : 문의사항 상세
    @Override
    public InquiryDTO getAdminInquiryDetail(Long id) {
        Optional<Inquiry> inquirydetails = inquiryRepository.findById(id);
        return toInquiryDTO(inquirydetails.get());
    }



}
