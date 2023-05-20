package com.app.dodamdodam.search.Inquiry;

import com.app.dodamdodam.type.InquiryStatus;
import com.app.dodamdodam.type.InquiryType;
import lombok.Data;

@Data
public class AdminInquirySearch {
    private String inquiryEmail;
    private String inquiryContent;
    private String inquiryPhoneNumber;

}
