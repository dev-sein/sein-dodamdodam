package com.app.dodamdodam.entity.embeddable;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
@ToString
@Setter
//@AllArgsConstructor
public class Address {
//    private String postcode;
    private String address;
    private String addressDetail;

    public Address(String address, String addressDetail) {
        this.address = address;
        this.addressDetail = addressDetail;
    }
}
