package com.bruce.superexcel.exception;

/**
 * 用于区分Nested事务和NEW事务所抛出的ApplicationException所新建的类型
 * Nested从属于NEW时用
 * @author Yarizuisen
 *
 */
public class SubApplicationException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SubApplicationException(String errorMsg) {
		super(ErrorCode.SYSTEM_ERROR, errorMsg);
	}
}
