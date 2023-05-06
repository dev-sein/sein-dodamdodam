package com.app.dodamdodam.domain;

import com.app.dodamdodam.type.MemberStatus;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Component
@Data
@NoArgsConstructor
public class MemberDTO {
    private Long id;

    private String memberId;
    private String memberPassword;
    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private String memberAddress;
    private String memberAddressDetail;
//    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;
    private Integer memberPoint;
    private Integer participationCount;
}
