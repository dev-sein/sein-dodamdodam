package com.app.dodamdodam.entity.inquiry;

import com.app.dodamdodam.audit.Period;
import com.app.dodamdodam.type.InquiryStatus;
import com.app.dodamdodam.type.InquiryType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "TBL_INQUIRY")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquiry extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @Enumerated(EnumType.STRING)
    @NotNull private InquiryType inquiryType;
    @NotNull private String inquiryEmail;
    private String memberIdentification;
    @NotNull private String inquiryPhoneNumber;
    @NotNull private String inquiryContent;
    private String inquiryAnswer;

    @ColumnDefault("'HOLD'")
    @Enumerated(EnumType.STRING)
    private InquiryStatus inquiryStatus;

    public Inquiry(InquiryType inquiryType, String inquiryEmail, String memberIdentification, String inquiryPhoneNumber, String inquiryContent, InquiryStatus inquiryStatus) {
        this.inquiryType = inquiryType;
        this.inquiryEmail = inquiryEmail;
        this.memberIdentification = memberIdentification;
        this.inquiryPhoneNumber = inquiryPhoneNumber;
        this.inquiryContent = inquiryContent;
        this.inquiryStatus = inquiryStatus;
    }

    public void setInquiryAnswer(String inquiryAnswer) {
        this.inquiryAnswer = inquiryAnswer;
    }

    public void setInquiryStatus(InquiryStatus inquiryStatus) {
        this.inquiryStatus = inquiryStatus;
    }

    @Builder
    public Inquiry(Long id, InquiryType inquiryType, String inquiryEmail, String memberIdentification, String inquiryPhoneNumber, String inquiryContent, String inquiryAnswer, InquiryStatus inquiryStatus) {
        this.id = id;
        this.inquiryType = inquiryType;
        this.inquiryEmail = inquiryEmail;
        this.memberIdentification = memberIdentification;
        this.inquiryPhoneNumber = inquiryPhoneNumber;
        this.inquiryContent = inquiryContent;
        this.inquiryAnswer = inquiryAnswer;
        this.inquiryStatus = inquiryStatus;
    }
}
