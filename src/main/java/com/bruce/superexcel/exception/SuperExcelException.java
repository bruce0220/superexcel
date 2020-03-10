/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.superexcel.exception;

/**
 * 系统受控异常父类
 * 
 * @author xiangshaoxu 2016年6月6日下午9:37:47
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class SuperExcelException extends Exception {


    /**
     *
     */
    public SuperExcelException() {}

    /**
     * @param message
     */
    public SuperExcelException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public SuperExcelException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public SuperExcelException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public SuperExcelException(String message, Throwable cause, boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
