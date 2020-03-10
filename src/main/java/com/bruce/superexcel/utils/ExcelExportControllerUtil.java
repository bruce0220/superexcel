/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.superexcel.utils;



import com.bruce.superexcel.export.ExcelExportEo;
import com.bruce.superexcel.export.ExcelExporter;
import com.bruce.superexcel.export.MapExcelDefine;
import com.bruce.superexcel.export.PoiExcelExporter;
import com.bruce.superexcel.common.Pair;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 
 * @author xiangshaoxu 2016年6月3日下午6:44:08
 * @version 1.0.0
 */
public class ExcelExportControllerUtil {


	/***
	 * 导出一个sheet
	 */
	public static <T extends ExcelExportEo> void execelExport(String filename, HttpServletResponse response, List<T> eos, Class<T> voClass) throws IOException {
			
			response.setContentType("application/ms-excel;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));
			
			ExcelExporter export = PoiExcelExporter.newInstance().addMetaData(eos, voClass);

			export.createExcel().export2Stream(response.getOutputStream());
		 }

	/***
	 * 导出一个sheet
	 */
	public static <T extends ExcelExportEo, U extends ExcelExportEo>  void execelExportMoreSheet(String filename, HttpServletResponse response, Pair<List<T>, Class<T>> sheet, Pair<List<U>, Class<U>> ...  more) throws IOException {

		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));

		List<T> eos = sheet.getFirst();
		Class<T> voClass = sheet.getSecond();
		ExcelExporter export = PoiExcelExporter.newInstance().addMetaData(eos, voClass);

		for (Pair<List<U>, Class<U>> each : more) {
			List<U> ueos = each.getFirst();
			Class<U> uClass = each.getSecond();
			export.addMetaData(ueos, uClass);
		}

		export.createExcel().export2Stream(response.getOutputStream());
	}
	 /**
	  * map  数据导出
	  * @param filename
	  * @param response
	  * @param med
	  * @param more
	  * @throws IOException
	  */
	 public static void excelExport(String filename, HttpServletResponse response, MapExcelDefine med, @SuppressWarnings("unchecked") MapExcelDefine... more) throws IOException {
		 
			response.setContentType("application/ms-excel;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));
			
			ExcelExporter export = PoiExcelExporter.newInstance().addMetaData(med.getSheetName(), med.getHeaders(), med.getColumns(), med.getMapDatas());
			
			for (MapExcelDefine medSub : more) {
				export.addMetaData(medSub.getSheetName(), medSub.getHeaders(), medSub.getColumns(), medSub.getMapDatas());
			}
			
			export.createExcel().export2Stream(response.getOutputStream());		 
				
	 }
}
