package com.app.dodamdodam.domain;

import com.app.dodamdodam.type.InquiryStatus;
import com.app.dodamdodam.type.InquiryType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminInquirySearchDTO {
    private InquiryType inquiryType;
    private String inquiryEmail;
    private String inquiryPhoneNumber;
    private InquiryStatus inquiryStatus;

    @QueryProjection
    public AdminInquirySearchDTO(InquiryType inquiryType, String inquiryEmail, String inquiryPhoneNumber, InquiryStatus inquiryStatus) {
        this.inquiryType = inquiryType;
        this.inquiryEmail = inquiryEmail;
        this.inquiryPhoneNumber = inquiryPhoneNumber;
        this.inquiryStatus = inquiryStatus;
    }
}
