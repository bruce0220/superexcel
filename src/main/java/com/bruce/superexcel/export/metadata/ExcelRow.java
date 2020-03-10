/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.superexcel.export.metadata;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author xiangshaoxu 2016年6月3日上午10:52:05
 * @version 1.0.0
 */
public class ExcelRow {
	
	private List<ExcelCell> cells = new ArrayList<ExcelCell>();

	private int index;

	private ExcelSheet parent;

	public ExcelRow(int index, ExcelSheet parent) {
		this.index = index;
		this.parent = parent;
	}

	public Integer sizeOfCols() {
		return cells.size();
	}

	public void addCell(ExcelCell e) {
		cells.add(e);
	}

	public List<ExcelCell> getCells() {
		return cells;
	}

	public int getIndex() {
		return index;
	}

	public ExcelSheet getParent() {
		return parent;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}