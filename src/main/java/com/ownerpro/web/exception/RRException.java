package com.ownerpro.web.exception;

import lombok.Getter;
import com.ownerpro.web.common.EnumExceptionType;

@Getter
public class RRException extends RuntimeException {

    private EnumExceptionType enumExceptionType;

    public RRException() {
    }

    public RRException(EnumExceptionType enumExceptionType) {
        this.enumExceptionType = enumExceptionType;
    }
}
