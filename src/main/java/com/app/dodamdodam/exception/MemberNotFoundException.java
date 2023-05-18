package com.app.dodamdodam.exception;

import com.app.dodamdodam.type.ErrorCode;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND.name());
    }

    public MemberNotFoundException(String message) {
        super(message);
    }
}
