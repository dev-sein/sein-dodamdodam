package com.app.dodamdodam.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    MEMBER, ADMIN;


    private static final String ROLE_PREFIX = "ROLE_";


    public String getSecurityRole(){
        return ROLE_PREFIX + name();
    }
}
