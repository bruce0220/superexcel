/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.superexcel;

import com.bruce.superexcel.exception.SuperExcelException;

/**
 * 导入时，excel格式&模版不对，注意与{@link InvalidExcelCellDataException}区别
 * @author xiangshaoxu 2016年6月6日下午9:35:17
 * @version 1.0.0
 */
public class InvalidExcelDefineException extends SuperExcelException {

	private static final long serialVersionUID = 6991415621131694018L;

	private String validateMsg;


    /**
     *
     */
    public InvalidExcelDefineException(String validateMsg) {
    	setValidateMsg(validateMsg);
    }

    /**
     * @param message
     */
    public InvalidExcelDefineException(String message, String validateMsg) {
        super(message);
        setValidateMsg(validateMsg);
    }

    /**
     * @param cause
     */
    public InvalidExcelDefineException(Throwable cause, String validateMsg) {
        super(cause);
        setValidateMsg(validateMsg);
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidExcelDefineException(String message, Throwable cause, String validateMsg) {
        super(message, cause);
        setValidateMsg(validateMsg);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public InvalidExcelDefineException(String message, Throwable cause, boolean enableSuppression,
                                       boolean writableStackTrace, String validateMsg) {
        super(message, cause, enableSuppression, writableStackTrace);
        setValidateMsg(validateMsg);
    }

	
	public String getValidateMsg() {
		return validateMsg;
	}

	private void setValidateMsg(String cellErrorMsg) {
		this.validateMsg = cellErrorMsg;
	}
	
	
}
