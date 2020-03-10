package com.bruce.superexcel.exception;

/**
 * 业务异常
 * <pre>使用场景</pre>
 * 1.业务发生异常但不希望前端统一拦截弹出错误消息时
 * 1.业务发生异常需要返回额外数据时
 * @author xuwen
 */
public class ApplicationBusinessException extends RuntimeException {

    private int businessCode;
    private Object businessData;

    public ApplicationBusinessException(int businessCode) {
        super();
        this.businessCode = businessCode;
    }

    public ApplicationBusinessException(int businessCode, Object businessData) {
        this(businessCode);
        this.businessData = businessData;
    }

    public int getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(int businessCode) {
        this.businessCode = businessCode;
    }

    public Object getBusinessData() {
        return businessData;
    }

    public void setBusinessData(Object businessData) {
        this.businessData = businessData;
    }
}
