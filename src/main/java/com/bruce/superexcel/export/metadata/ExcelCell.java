/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.superexcel.export.metadata;

import com.bruce.superexcel.export.CellVistor;
import com.bruce.superexcel.SuperExcelConstants;

/**
 * 
 * @author xiangshaoxu 2016年6月3日上午10:52:38
 * @version 1.0.0
 */
public abstract class ExcelCell {

	private int index;

	private ExcelRow parent;
	
	/** 
	 * 记录单元格的宽度，仅仅在header中会有意义
	 * */
	private int width = SuperExcelConstants.EXCEL_DEFAULT_CELL_WIDTH;

	public ExcelCell(int index, ExcelRow parent) {
		this.index = index;
		this.parent = parent;
	}


	/** 
	 * 返回单元格的宽度，仅仅在header中会有意义
	 * */
	public int getWidth() {
		return width;
	}

	/** 
	 * 设置单元格的宽度，仅仅在header中会有意义
	 * */
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	public ExcelRow getParent() {
		return parent;
	}


	public void setParent(ExcelRow parent) {
		this.parent = parent;
	}


	public abstract void accept(CellVistor vistor);

}