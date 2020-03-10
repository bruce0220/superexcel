/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.superexcel.export.metadata;

import com.bruce.superexcel.SuperExcelConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author xiangshaoxu 2016年6月3日上午10:49:40
 * @version 1.0.0
 */
public class ExcelSheet {

	/**
	 * sheet index
	 * */
	private int index;
	private String sheetName;
	private Integer defaultColumnWidth = SuperExcelConstants.EXCEL_DEFAULT_CELL_WIDTH;
	public float rowHeight = SuperExcelConstants.EXCEL_DEFAULT_ROW_HEIGHT;
	List<ExcelRow> rows = new ArrayList<>();

	public ExcelSheet(int index) {
		this.index = index;
	}

	public ExcelSheet(String sheetName) {
		this.sheetName = sheetName;
	}

	public void addRow(ExcelRow row) {
		rows.add(row);
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public Integer getDefaultColumnWidth() {
		return defaultColumnWidth;
	}

	public void setDefaultColumnWidth(Integer defaultColumnWidth) {
		this.defaultColumnWidth = defaultColumnWidth;
	}

	public List<ExcelRow> getRows() {
		return rows;
	}

	public float getRowHeight() {
		return rowHeight;
	}

	public void setRowHeight(float rowHeight) {
		this.rowHeight = rowHeight;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}