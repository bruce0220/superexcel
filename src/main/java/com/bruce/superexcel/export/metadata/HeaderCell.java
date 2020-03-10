/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.superexcel.export.metadata;

import com.bruce.superexcel.export.CellVistor;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 
 * @author xiangshaoxu 2016年6月3日下午3:19:15
 * @version 1.0.0
 */
public class HeaderCell extends ExcelCell {

	public HeaderCell(int index, ExcelRow parent) {
		super(index, parent);
	}

	private String content;

	@Override
	public void accept(CellVistor vistor) {
		vistor.visit(this);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
