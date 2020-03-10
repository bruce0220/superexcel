/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.superexcel.export;

import com.bruce.superexcel.export.metadata.*;

/**
 * 
 * @author xiangshaoxu 2016年6月2日下午3:14:06
 * @version 1.0.0
 */
public interface CellVistor {

	void visit(HeaderCell excelCell);
	
	void visit(StringCell excelCell);

	void visit(DoubleCell excelCell);

	void visit(BooleanCell excelCell);

	void visit(CalendarCell excelCell);

	void visit(DateCell excelCell);

	void visit(UrlCell excelCell);

}
