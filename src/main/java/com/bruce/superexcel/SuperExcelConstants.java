/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.superexcel;

/**
 * 保存系统常量，仅仅用于部分int String等常量的统一存放， 有分类意义的需要使用枚举
 * 
 * @author xiangshaoxu 2016年6月3日下午4:21:15
 * @version 1.0.0
 */
public interface SuperExcelConstants {
	/**excel宽度和高度单位不一致，基本上h,w=15则导出为长方形，符合大多情况*/
	int EXCEL_DEFAULT_ROW_HEIGHT = 15;
	int EXCEL_DEFAULT_CELL_WIDTH = 15;
	
	int STATUS_ACTIVE = 1;
	int STATUS_INACTIVE = 0;
	
	int NUM_1W = 10000;


	/**自定义任意折扣*/
	String PROMOTION_CUSTOM_DISCOUNT = "-100";

	/**自定义任意减免*/
	String PROMOTION_CUSTOM_REDUCTION = "-200";

	/**自定义任意赠送课时*/
	String PROMOTION_CUSTOM_GIFT_COURSES = "-300";

	String TEMP = "temporary-";
}
