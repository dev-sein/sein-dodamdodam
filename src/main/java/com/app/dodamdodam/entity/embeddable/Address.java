package com.app.dodamdodam.entity.embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
//@AllArgsConstructor
public class Address {
    private String postcode;
    private String address;
    private String addressDetail;

    public Address(String postcode, String address, String addressDetail) {
        this.postcode = postcode;
        this.address = address;
        this.addressDetail = addressDetail;
    }
}
