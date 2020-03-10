/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.superexcel.export.anno;

import com.bruce.superexcel.SuperExcelConstants;

import java.lang.annotation.*;

/**
 * 对要导出为excel的sheet命名
 * 
 * @author xiangshaoxu 2016年6月2日上午9:36:04
 * @version 1.0.0
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExportedSheetDefine {
	String sheetName();
	int defaultColumnWidth() default SuperExcelConstants.EXCEL_DEFAULT_CELL_WIDTH;
	float rowHeight() default SuperExcelConstants.EXCEL_DEFAULT_ROW_HEIGHT;
}
