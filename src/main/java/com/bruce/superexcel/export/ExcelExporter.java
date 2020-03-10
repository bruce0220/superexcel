/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.superexcel.export;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * 
 * @author xiangshaoxu 2016年6月5日下午4:02:13
 * @version 1.0.0
 */
public interface ExcelExporter {

	/**增加要导出的数据，每次为一个sheet,可以多次增加*/
	public abstract <T extends ExcelExportEo> ExcelExporter addMetaData(List<T> vos, Class<T> voClass);

	/**增加要导出的数据，每次为一个sheet,可以多次增加*/
	public abstract <T extends ExcelExportEo> ExcelExporter addMetaData(String sheetName, String[] headers, String[] columns, List<Map<String, Serializable>> mapList);

	/**
	 * 创建 excel文件，你需要至少调用一次addData()增加sheet
	 * */
	public abstract ExcelExporter createExcel();

	/**
	 * 创建 excel文件，你需要至少调用一次addData()增加sheet
	 * */
	public abstract ExcelExporter createExcel(TimeZone zone, Locale local);

	public abstract void export2Stream(OutputStream out) throws IOException;

}