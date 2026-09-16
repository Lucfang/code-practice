package com.lcf.codepractice.common.exception;

import com.lcf.codepractice.common.enums.ResultCode;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author Luchunfang
 */
@Getter
public class BusinessException extends RuntimeException implements Serializable {

    private final Integer code;

    public BusinessException(String message) {
        super(message);
        this.code = ResultCode.BUSINESS_ERROR.getCode();
    }

    public BusinessException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
