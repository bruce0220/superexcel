package com.bruce.superexcel.export;

import com.bruce.superexcel.export.metadata.*;
import com.bruce.superexcel.utils.SuperExcelAssert;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Excel创建、导出工具, 非线程安全
 * eg: 
 * export single eo/sheet: ExcelExporter.newInstance().addMetaData(eos).createExcel().export2Stream(fos)
 * export multi eo/sheet: ExcelExporter.newInstance().addMetaData(eos).addMetaData(eos2).addMetaData(eos3).createExcel().export2Stream(fos)
 * @author xiangshaoxu 2016年6月2日
 * @version 1.0.0
 */
public class PoiExcelExporter implements  ExcelExporter  {

    @SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(PoiExcelExporter.class);

    private boolean excelCreated = false;
	private ExcelData excelData;
	private PoiCellVisitor cellVisitor;
	private XSSFWorkbook workbook = new XSSFWorkbook();
	private static Map<Class<?>, XSSFCellStyle> styleMap = new HashMap<>();
	StyleGenerator styleGenerator = new StyleGenerator(workbook);

	private PoiExcelExporter() {
		initCellStyleMap();
		excelData = new ExcelData();
		cellVisitor = new PoiCellVisitor(this);
	}

	private void initCellStyleMap() {
		styleMap.put(StringCell.class, styleGenerator.defaultStringCellStyle());
		styleMap.put(HeaderCell.class, styleGenerator.defaultHeaderStyle());
		styleMap.put(DoubleCell.class, styleGenerator.defaultStringCellStyle());
		styleMap.put(BooleanCell.class, styleGenerator.defaultStringCellStyle());
		styleMap.put(UrlCell.class, styleGenerator.defaultUrlCellStyle());
		styleMap.put(DateCell.class, styleGenerator.defaultDateCellStyle());
		styleMap.put(CalendarCell.class, styleGenerator.defaultDateCellStyle());
	}
	
	public static PoiExcelExporter newInstance() {
		return new PoiExcelExporter();
	}

	@Override
	public <T extends ExcelExportEo> ExcelExporter addMetaData(List<T> eos, Class<T> voClass) {
		excelData.addExcelMetadata(eos, voClass);
		return  this;
	}
	
	@Override
	public <T extends ExcelExportEo> ExcelExporter addMetaData(String sheetName, String[] headers, String[] columns, List<Map<String, Serializable>> mapList) {
		excelData.addExcelMetadata(sheetName, headers, columns, mapList);
		return this;
	}

	@Override
	public ExcelExporter createExcel() {
		return createExcel(TimeZone.getDefault(), Locale.getDefault());
	}
	
	@Override
	public ExcelExporter createExcel(TimeZone zone, Locale local) {
		SuperExcelAssert.state(excelData.getSheets().size() > 0, "at leaset one sheet in Excel.");
		
		for (ExcelSheet excelSheet : excelData.getSheets()) {
			
			XSSFSheet sheet = createSheet(excelSheet);
	
			createRows(sheet, excelSheet, zone, local);
		}
		
		excelCreated = true;
		
		excelData = null;
		return this;
	}

	@Override
	public void export2Stream(OutputStream out) throws IOException {
		SuperExcelAssert.state(excelCreated, "you need to add data and createExcel first.");
		this.workbook.write(out);
		workbook = null;
	}

	XSSFCellStyle getDefaultCellStyle(ExcelCell excelCell) {
		XSSFCellStyle style = styleMap.get(excelCell.getClass());
		if (style == null) {
			style =  styleMap.get(StringCell.class);
		}
		return style;
	}
	
	XSSFCellStyle getDateCellStyle(String dateformat) {
		return styleGenerator.dateCellStyle(dateformat);
	}
	
	private XSSFSheet createSheet(ExcelSheet excelSheet) {
		XSSFSheet sheet = workbook.createSheet(excelSheet.getSheetName());
		List<ExcelCell> headers = excelSheet.getRows().get(0).getCells();
		for (int i = 0; i < headers.size(); i++) {
			ExcelCell header = headers.get(i);
			sheet.setColumnWidth(i, header.getWidth()*256);
		}
		
		sheet.setDefaultColumnWidth(excelSheet.getDefaultColumnWidth());
		return sheet;
	}

	private void createRows(XSSFSheet sheet, ExcelSheet excelSheet, TimeZone zone, Locale local) {
		int rowIndex = -1; //row num counter begin at 1
		for(ExcelRow excelRow : excelSheet.getRows()) {
			rowIndex++;
			XSSFRow row = sheet.createRow(rowIndex);
			row.setHeightInPoints(excelSheet.getRowHeight());
			createCell(row, excelRow, zone, local);
		}
	}

	private void createCell(XSSFRow row, ExcelRow excelRow, TimeZone zone, Locale local) {
		int columnIndex = -1;
		for(ExcelCell excelCell : excelRow.getCells()) {
			columnIndex++;
			XSSFCell cell = row.createCell(columnIndex);
			cellVisitor.setCell(cell);
			excelCell.accept(cellVisitor);
		}
	}

}
