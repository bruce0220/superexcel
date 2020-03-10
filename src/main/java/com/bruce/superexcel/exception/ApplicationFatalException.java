package com.bruce.superexcel.exception;

/**
 * 严重等级最高的应用错误
 * 例如机构串数据
 * @author xuwen
 */
public class ApplicationFatalException extends RuntimeException {

    public ApplicationFatalException() {
    }

    public ApplicationFatalException(String message) {
        super(message);
    }

    public ApplicationFatalException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationFatalException(Throwable cause) {
        super(cause);
    }

    public ApplicationFatalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
