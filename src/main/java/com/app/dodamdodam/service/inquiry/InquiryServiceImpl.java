package com.app.dodamdodam.service.inquiry;

import com.app.dodamdodam.domain.InquiryDTO;
import com.app.dodamdodam.domain.MailDTO;
import com.app.dodamdodam.entity.inquiry.Inquiry;
import com.app.dodamdodam.repository.inquiry.InquiryRepository;
import com.app.dodamdodam.search.Inquiry.AdminInquirySearch;
import com.app.dodamdodam.type.InquiryStatus;
import com.app.dodamdodam.type.InquiryType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("inquiry")
@RequiredArgsConstructor
@Transactional
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

    /* 메일 발송하기 */
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendAnswerMail(MailDTO mailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDTO.getAddress());
//      message.setFrom(""); from 값을 설정하지 않으면 application.yml의 username값이 설정됩니다.
        message.setSubject(mailDTO.getTitle());
        message.setText(mailDTO.getMessage());

        mailSender.send(message);
    }

    /* 문의사항 답변하기, 답변 저장, status 변경하기*/
    @Override
    public void updateInquiryAnsewrStatus(Long inquiryId, InquiryDTO inquiryDTO) {
        Optional<Inquiry> inquires = inquiryRepository.findById(inquiryId);
        inquires.ifPresent(
                inquiry -> {
                    inquiry.setInquiryAnswer(inquiryDTO.getInquiryAnswer());
                    inquiry.setInquiryStatus(InquiryStatus.DONE);
                    inquiryRepository.save(inquiry);
                });
    }

}
