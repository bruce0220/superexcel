package com.bruce.superexcel.export;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 定义map数据导出的excel
 */
public class MapExcelDefine {

	/**
	 * sheetName
	 */
	private String sheetName;
	
	/**
	 * 表头
	 */
	private String[] headers;
	
	/**
	 * 列
	 */
	private String[] columns;
	
	/**
	 * 列数据
	 */
	private List<Map<String, Serializable>> mapDatas = new ArrayList<>();
	
	public MapExcelDefine(String sheetName, String[] headers, String[] columns, List<Map<String, Serializable>> mapDatas){
		this.sheetName = sheetName;
		this.headers = headers;
		this.columns = columns;
		this.mapDatas = mapDatas;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String[] getHeaders() {
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
	}

	public List<Map<String, Serializable>> getMapDatas() {
		return mapDatas;
	}

	public void setMapDatas(List<Map<String, Serializable>> mapDatas) {
		this.mapDatas = mapDatas;
	}
	
	
}
