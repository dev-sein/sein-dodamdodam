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
    @NotNull private String memberIdentification;
    @NotNull private String inquiryPhoneNumber;
    @NotNull private String inquiryContent;
    @NotNull private String inquiryAnswer;
    @ColumnDefault("'APPLYING'")
    @Enumerated(EnumType.STRING)
    @NotNull private InquiryStatus inquiryStatus;

}
