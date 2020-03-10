/**
 * 
 */
package com.bruce.superexcel.exception;


/**
 * @classname	ConnetionErrorException.java 
 * @Description
 * @author	ChenGuiBan
 * @Date	2011-5-3  10:12:37
 * @LastUpdate	ChenGuiBan
 * @Version	1.0
 */
public class GenericeException extends RuntimeException {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/**  @return the errorCode */
	private ErrorCode errorCode;
	
	public GenericeException(ErrorCode errorCode, String errorMsg) {
        super(errorMsg);
		this.errorCode = errorCode;
	}

	/**
	 * Constructs a new runtime exception with the specified detail message and
	 * cause.  <p>Note that the detail message associated with
	 * {@code cause} is <i>not</i> automatically incorporated in
	 * this runtime exception's detail message.
	 *
	 * @param message the detail message (which is saved for later retrieval
	 *                by the {@link #getMessage()} method).
	 * @param cause   the cause (which is saved for later retrieval by the
	 *                {@link #getCause()} method).  (A <tt>null</tt> value is
	 *                permitted, and indicates that the cause is nonexistent or
	 *                unknown.)
	 * @since 1.4
	 */
	public GenericeException(String message, Throwable cause) {
		super(message, cause);
        this.errorCode = ErrorCode.SYSTEM_ERROR;
    }

	/**
	 * @return the errorCode
	 */
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	} 
	
	/**
	 * @see java.lang.Throwable#printStackTrace()
	 */
	public void printStackTrace() {
		super.printStackTrace();
	}

	public String getErrorMsg() {
		return getMessage();
	}


}
