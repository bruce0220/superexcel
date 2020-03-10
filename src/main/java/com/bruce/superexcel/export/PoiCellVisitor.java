/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.superexcel.export;

import com.bruce.superexcel.export.metadata.*;
import com.bruce.superexcel.utils.SuperExcelAssert;
import com.bruce.superexcel.utils.SuperExcelStringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.springframework.util.Assert;

/**
 * ExcelCell 基于poi的访问者， visit前必须设定HssfCell
 * 
 * @author xiangshaoxu 2016年6月2日下午2:50:36
 * @version 1.0.0
 */
public class PoiCellVisitor implements CellVistor {
	
	XSSFCell cell;
	PoiExcelExporter accessor;
	
	public PoiCellVisitor(PoiExcelExporter accessor) {
		this.accessor = accessor;
	}
	
	public void setCell(XSSFCell cell) {
		this.cell = cell;
	}

	@Override
	public void visit(final StringCell excelCell) {
		checkAndRemoveCell(new AbstractFunction() {

			@Override
			public void apply() {
				cell.setCellValue(excelCell.getContent());
				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
				XSSFCellStyle style = getStyle(excelCell);
				cell.setCellStyle(style);
			}
			
		});
	}
	
	@Override
	public void visit(final DoubleCell excelCell) {
		checkAndRemoveCell(new AbstractFunction() {

			@Override
			public void apply() {
				cell.setCellValue(excelCell.getContent());
				cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
				XSSFCellStyle style = getStyle(excelCell);
				cell.setCellStyle(style);
			}
			
		});
	}
	
	@Override
	public void visit(final BooleanCell excelCell) {
		checkAndRemoveCell(new AbstractFunction() {

			@Override
			public void apply() {
				cell.setCellValue(excelCell.getContent());
				cell.setCellType(XSSFCell.CELL_TYPE_BOOLEAN);
				XSSFCellStyle style = getStyle(excelCell);
				cell.setCellStyle(style);
			}
			
		});
	}
	
	@Override
	public void visit(final CalendarCell excelCell) {
		checkAndRemoveCell(new AbstractFunction() {

			@Override
			public void apply() {
				cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
				cell.setCellValue(excelCell.getContent());
				XSSFCellStyle style = getStyle(excelCell);
				cell.setCellStyle(style);
			}
			
		});
	}
	
	@Override
	public void visit(final DateCell excelCell) {
		checkAndRemoveCell(new AbstractFunction() {

			@Override
			public void apply() {
				cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
				cell.setCellValue(excelCell.getContent());
				XSSFCellStyle style = null;
				String dateformat = excelCell.getDateformat();
				if (SuperExcelStringUtils.isNotBlank(dateformat)) {
					style = accessor.getDateCellStyle(dateformat);
				}  else {
					style = getStyle(excelCell);
				}
				cell.setCellStyle(style);
			}
			
		});
	}
	
	@Override
	public void visit(final UrlCell excelCell) {

		checkAndRemoveCell(new AbstractFunction() {

			@Override
			public void apply() {
				String linkLabel = excelCell.getLinkLabel();
				String linkUrl = excelCell.getLinkUrl();
				if (SuperExcelStringUtils.isBlank(linkLabel)) {
					linkLabel = linkUrl;
				}
				if (SuperExcelStringUtils.isNotBlank(linkUrl)) {
					cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
					cell.setCellFormula("HYPERLINK(\"" + linkUrl + "\",\"" + linkLabel + "\")");
				}
				XSSFCellStyle style = getStyle(excelCell);
				cell.setCellStyle(style);
			}

		});
	}
	
	@Override
	public void visit(final HeaderCell excelCell) {
	
		checkAndRemoveCell(new AbstractFunction() {
	
			@Override
			public void apply() {
				XSSFRichTextString text = new XSSFRichTextString(excelCell.getContent());
				cell.setCellValue(text);
				XSSFCellStyle style = getStyle(excelCell);
				cell.setCellStyle(style);
			}
			
		});
	}

	public  void checkAndRemoveCell(AbstractFunction fun) {
		SuperExcelAssert.isNotNull(cell, "XSSFCell in PoiExcelVistor can't be null");
		fun.apply();
		cell = null;
	}
	
	abstract class  AbstractFunction {
		abstract void apply();
		
		public XSSFCellStyle getStyle(ExcelCell excelCell) {
			XSSFCellStyle style = accessor.getDefaultCellStyle(excelCell);
			Assert.state(style != null);
			return style;
		}
	}
	
	
}
