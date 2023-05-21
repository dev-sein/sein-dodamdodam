package com.app.dodamdodam.domain;

import com.app.dodamdodam.type.InquiryStatus;
import com.app.dodamdodam.type.InquiryType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InquiryDTO {
    private Long id;
    private InquiryType inquiryType;
    private String inquiryEmail;
    private String inquiryPhoneNumber;
    private String memberIdentification;
    private String inquiryContent;
    private String inquiryAnswer;
    private InquiryStatus inquiryStatus;

    @QueryProjection
    public InquiryDTO(Long id, InquiryType inquiryType, String inquiryEmail, String inquiryPhoneNumber, String memberIdentification, String inquiryContent, String inquiryAnswer, InquiryStatus inquiryStatus) {
        this.id = id;
        this.inquiryType = inquiryType;
        this.inquiryEmail = inquiryEmail;
        this.inquiryPhoneNumber = inquiryPhoneNumber;
        this.memberIdentification = memberIdentification;
        this.inquiryContent = inquiryContent;
        this.inquiryAnswer = inquiryAnswer;
        this.inquiryStatus = inquiryStatus;
    }
}
