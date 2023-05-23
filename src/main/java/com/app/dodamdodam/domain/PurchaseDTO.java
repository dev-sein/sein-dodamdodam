package com.app.dodamdodam.domain;

import com.app.dodamdodam.entity.embeddable.Address;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.purchase.Product;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class PurchaseDTO {
    private Long id;
    private ProductDTO productDTO;
    private MemberDTO memberDTO;
    private Address address;

    public PurchaseDTO(Long id, ProductDTO productDTO, MemberDTO memberDTO, Address address) {
        this.id = id;
        this.productDTO = productDTO;
        this.memberDTO = memberDTO;
        this.address = address;
    }
}
