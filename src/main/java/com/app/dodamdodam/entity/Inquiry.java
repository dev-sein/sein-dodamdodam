package com.app.dodamdodam.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_INQUIRY")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquiry {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String inquiryType;
    @NotNull private String inquiryEmail;
    @NotNull private String memberIdentification;
    @NotNull private String inquiryPhoneNumber;
    @NotNull private String inquiryContent;
    @NotNull private String inquiryAnswer;
}
