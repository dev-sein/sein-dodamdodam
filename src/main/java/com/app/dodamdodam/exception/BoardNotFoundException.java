package com.app.dodamdodam.exception;

import com.app.dodamdodam.type.ErrorCode;

public class BoardNotFoundException extends RuntimeException{
    public BoardNotFoundException() {
        super(ErrorCode.BOARD_NOT_FOUNT.name());
    }

    public BoardNotFoundException(String message){
        super(message);
    }
}
