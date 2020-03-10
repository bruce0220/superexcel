/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.superexcel.export.anno;

import com.bruce.superexcel.SuperExcelConstants;

import java.lang.annotation.*;

/**
 * 对要导出为excel的字段标识导出表头字段名称
 * 
 * @author xiangshaoxu 2016年6月2日上午9:36:04
 * @version 1.0.0
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExportedHeaderDefine {
	String headerName();
	int cellWidth() default SuperExcelConstants.EXCEL_DEFAULT_CELL_WIDTH;
}
