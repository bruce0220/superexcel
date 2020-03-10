/**
 * 
 */
package com.bruce.superexcel.exception;

import java.util.HashMap;
import java.util.Map;


/**
 * 错误提示
 * @classname	ErrorCode.java 
 * @Description
 * @author	ChenGuiBan
 * @Date	2011-4-22  11:17:22
 * @LastUpdate	ChenGuiBan
 * @Version	1.0
 */
public enum ErrorCode {
	
	USER_ID_EMPTY("001-用户id不用为空"),
	
	USER_NOT_FOUND("002-用户不存在"),
	
	ORG_ID_EMPTY("003-组织架构不能为空"),
	
	ORG_NOT_FOUND("003-组织架构不存在"),
	
	HAS_USER_FOR_DELETE("004-该职位配置下还有用户关联，请清除所有用户与该职位关联再删除职位配置。"),
	
	HAS_ORG_FOR_DELETE("004-所选记录下属还有关联的组织架构，请先移除本记录下属组织架构再删除。"),
	
	ROLE_NOT_FOUND("005-职位不存在"),
	
	RES_NOT_FOUND("006-资源不存在"),
	
	EMPTY_CONTENT("008-内容不能为空"),
	
	TOKEN_ERROR("013-登录令牌校验失败"),

	OPTION_CATEGORY_EMPTY("014-选择项类别不能为空"),
	
	PARAMETER_EMPTY("015-参数不能为空"),
	
	STUDENT_INFO_EMPTY("016-学生信息不能为空"),
	
	NO_LOGIN_USER_INFO("017-没发登录用户信息，请重新登录"),
	
	USRR_ORG_NOT_FOUND("18-没能找到当前用户组织架构"),
	
	PARAMETER_FORMAT_ERROR("19-参数格式错误"),
	
	COURSE_NOT_FOUND("20-找不到课程"),
	
	COURSE_NOT_AUDITED("21-课时未核对"),
	
	STUDENT_NOT_FOUND_BY_ATTENDANCE_NO("22-根据考勤编号找不到学生"),
	
	USER_AUTHROTY_FAIL("23-当前用户没有本操作权限"),
	
	CUSTOMER_NOT_FOUND("24-找不到客户"),
	
	CUSTOMER_CONTACT_FOUND("25-该号码客户已存在。"),
	
	COURSE_STUDY_MANAGEMENT_NOT_AUDIT("26-学管未核对课时。"),
	
	CUSTOMER_HAD_BEED_CONFIRM("27-客户已经确认。"),
	
	STUDENT_NOT_FOUND_BY_IC_CARD_NO("28-根据IC卡编号找不到学生"),
	
	STUDENT_ATTENDANCE_NUM_EXIST("29-学生考勤编号已存在，请重新录入。"),
	
	TEACHER_NOT_FOUND("30-老师不存在"),
	
	COURSE_CAN_NOT_BE_DELETE("31-只有未上课的课程才能被删除。"),
	
	STUDENT_ID_EMPTY("32-学生编号不能为空。"),
	
	STUDENT_PASSWORD_EMPTY("33-学生密码不能为空。"),
	
	STUDENT_LOGIN_FAIL("33-用户名或密码错误，登录失败。"),
	
	CANCEL_CONTRACT_TYPE_OF_DEPOSIT("34-现在已取消\"定金合同\"功能"),
	
	SYNC_REFLUSH_REPORT("35-报表正在执行中，请稍后查询"),
	
	NOT_ENOUGH_SMS("36-机构剩余短信不足，无法发送本次短信"),
	
	SQL_ERROR("990-数据库错误"),
	
	PERMISSION_DENY("991-没有该操作的权限"),
	
	USER_NAME_PASSWORD_ERROR("998-用户名或密码错误"),

	USER_WORK_TYPE_IS_DUMMY("360-您的账号是虚拟账号，不允许登录，如账号类型有误请找机构管理员重新设定"),

	SYSTEM_ERROR("999-系统错误，请联系管理员"), 
	
	MONEY_NOT_ENOUGH("300-合同资金不足够"), 
	
	MONEY_ERROR("301-合同资金错误，请检查"), 
	
	ONE_ON_ONE_MONEY_NOT_ENOUGH("302-课程扣费失败：资金不足，请确认学生所签合同是否已收费或所签合同的一对一课程是否已分配资金（合同未收完款时需要学管把合同已收款的资金分配给不同的课程账号后才能进行扣费）。"),
	
	MIN_CLASS_MONEY_NOT_ENOUGH("303-课程扣费失败：资金不足，请确认学生所签合同是否已收费或所签合同的班课课程是否已分配资金（合同未收完款时需要学管把合同已收款的资金分配给不同的课程账号后进行扣费）。"),
	
	EMPTY_START_DATE("70-开始日期不能为空"),
	
	CONTRACT_TOTAL_LESS_PAID("401-合同修改后的金额小于已付款，请重新修改"), 
	
	ONE_ON_ONE_TOTAL_LESS_CONSUME("402-一对一课时总数不能小于已消费课时数"), 
	
	FREE_TOTAL_LESS_CONSUME("403-赠送课时数不能小于已消费的赠送课时数"), 
	
	DATA_TYPE_CONVERSION("数据类型转换错误"),
	
	CANT_DEL_WHEN_CHARGED("404-已扣费的班课产品不允许删除"), 
	
	JSON_PARSE_ERROR("71-JSON编译出错 "), 
	
	CAMPUS_ACCOUNT_ACHARGE_RECORDS("304-当前用户的归属组织架构只有校区下的才能扣费"),
	
	CAMPUS_FUNDS_CHANGE_HISTORY("306-当前用户的归属组织架构只有校区下的才能收款扣费"),
	
	NOT_FIND_STUDY_MANAGER("305-本校区该学生还没有对应的学管，请先分配学管再排课"),
	
	
	MINI_CLASS_HOUR_NOT_ENOUGH("308-课时不足，请确认学生所签合同的班课课程是否已分配课时。"),
	
	
	NOT_SUPPORT_METHOD("405-调用出错,或者暂时不支持本方法!"),
	
	PHONELOGIN_USER_NOT_FOUNT("501-找不到有效用户！"),
	
	PHONELOGIN_USER_CONTACT_REPEAT("502-有效用户电话号码与登录密码的组合有重复！"),
	
	PHONELOGIN_ERROR("503-登陆失败，请确认登陆手机号与密码是否正确或该账号已被禁用！"),
	
	NO_INSTITUTION_PHONE_REMAIN("机构当前通话时长为0，如需使用，请联系校长或机构管理员进行购买~"),
	
	
	OOO_ATTEND_FAILED_CONFLICT("1000-考勤失败：课程冲突，请找排课人员调整修改后再扣费"),
	OOO_CHARGE_FAILED_ALREADY_DONE("1001-已经完成扣费，扣费操作失败"),
	OOO_ATTEND_FAILED_NO_PRODUCT("1002-课程未关联产品，无法扣除课时"),
	OOO_ATTEND_FAILED_HOUR_NOT_ENOUGH("1003-课时不足，请确认学生所签合同的一对一课程是否已分配课时"),
	OOO_CHARGE_FAILED_MONEY_NOT_ENOUGH("1004-一对一合同产品资金不足，请确认学生所签合同是否已收费或其一对一课程是否已分配资金"),
	COMMON_EXCEPTION("1050-系统出错"),
	COMMON_ATTEND_FAILED_PRODUCT_CLOSED("1051-合同产品已结束"),
	COMMON_CHARGE_FAILED_INCORRECT_CPHCR("1052-无法进行扣费，请检查课时流水的扣费状态"),
	COMMON_CHARGE_FAILED_INCORRECT_STATUS("1053-当前课程状态不允许扣费"),
	COMMON_CHARGE_FAILED_MONEY_NOT_ENOUGH("1054-合同产品资金不足，请确认学生所签合同是否已收费或是否已分配资金"),
	COMMON_NOT_SUPPORT_AVERAGE_CHARGE("1055-无法进行扣费，该类型产品暂不支持均摊付费模式"),
	COMMON_NOT_SUPPORT_CHARGE_HOUR("1056-无法进行考勤，该类型产品暂不支持考勤"),
	MC_CHARGE_FAILED_MONEY_NOT_ENOUGH("1101-班课合同产品资金不足，请确认学生所签合同是否已收费或其班课课程是否已分配资金"),
	OON_CHARGE_FAILED_MONEY_NOT_ENOUGH("1151-一对N合同产品资金不足，请确认学生所签合同是否已收费或其一对N课程是否已分配资金"),
	OON_ATTEND_FAILED_HOUR_NOT_ENOUGH("1150-课时不足，请确认所选合同产品的一对N课程是否已分配课时。"),
	MCS_QUIT_FAILED_CONTRACTPRODUCT_BINDED_MORE("1152-学生存在多个产品绑定同一班课")
	;
	
	
	/** The Constant value. */
	private final String value;
	
	/** The Constant STRING_TO_ENUM. */
    private static final Map<String, ErrorCode> STRING_TO_ENUM = new HashMap<String, ErrorCode>();
    
    static {
        for (ErrorCode e : values())
            STRING_TO_ENUM.put(e.getValue(), e);
    }
    
    ErrorCode(String value) {
        this.value = value;
    }
    
    /**
     * @return
     */
    public String getValue() {
        return value;
    }
    /**
     * @return
     */
    public Integer getErrorCode() {
    	String[] error = value.split("-");
    	return Integer.valueOf(error[0]);
    }
    /**
     * @return
     */
    public String getErrorString() {
    	String[] error = value.split("-");
    	return error.length > 1 ? error[1] : "";
    }
    /**
     * @param errorCode
     * @return
     */
    public String getErrorString(String errorCode) {
    	return STRING_TO_ENUM.get(errorCode).getErrorString();
    }
    
}
