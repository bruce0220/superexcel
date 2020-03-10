/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.superexcel.export.metadata;

import com.bruce.superexcel.anno.ExcelIgnore;
import com.bruce.superexcel.export.ExcelExportEo;
import com.bruce.superexcel.export.anno.CellExportedTypeDefine;
import com.bruce.superexcel.export.anno.ExportedHeaderDefine;
import com.bruce.superexcel.export.anno.ExportedSheetDefine;
import com.bruce.superexcel.utils.SuperExcelAssert;
import com.bruce.superexcel.utils.SuperExcelBeanUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 导出excel元数据，包括ExcelSheet列表, 非线程安全
 * 
 * @author xiangshaoxu 2016年6月2日下午1:40:51
 * @version 1.0.0
 */
public class ExcelData {

	private static Logger logger = LoggerFactory.getLogger(ExcelData.class);
	
	public List<ExcelSheet> sheets = new ArrayList<>();
	
	private int currentSheetIdx = -1;

	public ExcelData() {
	}
	
	public void addSheet(ExcelSheet sheet) {
		sheets.add(sheet);
	}

	public List<ExcelSheet> getSheets() {
		return sheets;
	}
	
	public <T extends ExcelExportEo> void addExcelMetadata(String sheetName, String[] headers, String[] columns, List<Map<String, Serializable>> mapList) {
		SuperExcelAssert.isNotBlank(sheetName, "sheetName of Excel can't be blank");
		SuperExcelAssert.isNotEmpty(headers, "headers of Excel can't be empty");
		SuperExcelAssert.isNotEmpty(columns, "columns of Excel can't be empty");
		int headerLength = headers.length;
		SuperExcelAssert.state(headerLength == columns.length, "the count of headers need to equal to columns");
		
		
		currentSheetIdx++;
		//create sheet
		ExcelSheet sheet = new ExcelSheet(currentSheetIdx);
		sheet.setSheetName(sheetName);
		
		// createHeaderRow
		int rowIndex = 0;
		ExcelRow headerRow = new ExcelRow(rowIndex, sheet);
		for (int i = 0; i < headerLength; i++) {
			HeaderCell cell = new HeaderCell(i, headerRow);
			cell.setContent(headers[i]);
			headerRow.addCell(cell);
		}
		sheet.addRow(headerRow);
		
		//create other row
		int mapSize = mapList.size();
		for (int i = 0; i < mapSize; i++) {
			rowIndex++;
			Map<String,? extends Serializable> innerMap = mapList.get(i);
			ExcelRow row = new ExcelRow(rowIndex, sheet);
			for (int j = 0; j < columns.length; j++) {
				String columnStr = columns[j];
				ExcelCell cell = transferContent2Cell(innerMap.get(columnStr), null, j, row);
				row.addCell(cell);
			}
			sheet.addRow(row);
		}
		
		addSheet(sheet);
	}

	/**
	 * 增加一种类型的vo,可以连续加入多个类型的vo导出为多个sheet
	 * */
	public <T extends ExcelExportEo> void addExcelMetadata(List<T> eos, Class<T> eoClass) {
		currentSheetIdx++;
		ExcelSheet sheet = new ExcelSheet(currentSheetIdx);

		//createSheet
		ExportedSheetDefine sheetAnn = eoClass.getAnnotation(ExportedSheetDefine.class);
		if (sheetAnn != null) {
			sheet.setSheetName(sheetAnn.sheetName());
			sheet.setDefaultColumnWidth(sheetAnn.defaultColumnWidth());
			sheet.setRowHeight(sheetAnn.rowHeight());
		} else {
			sheet.setSheetName(eoClass.getSimpleName());
			sheet.setDefaultColumnWidth(20);
			sheet.setRowHeight(20);
		}

		// createHeaderRow
		int rowIndex = 0;
		ExcelRow headerRow = new ExcelRow(rowIndex, sheet);
		Field[] fields = getNotIgnoreFields(eoClass);
		
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			ExportedHeaderDefine headerAnn = field.getAnnotation(ExportedHeaderDefine.class);
			HeaderCell header = new HeaderCell(i, headerRow);
			if (headerAnn != null) {
				header.setContent(headerAnn.headerName());
				header.setWidth(headerAnn.cellWidth());
			} else {
				header.setContent(" ");
				header.setWidth(20);
			}
			headerRow.addCell(header);
			
		}
		sheet.addRow(headerRow);

		// createRows
		for (ExcelExportEo eo : eos) {
			rowIndex++;
			ExcelRow row = new ExcelRow(rowIndex, sheet);

			Map<?, ?> eoMap = null;
			try {
				eoMap = SuperExcelBeanUtils.convertBean(eo);
			} catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
				SuperExcelAssert.state(false, String.format("excel vo: %s reflecting failed, Be sure that there is one Getter for each field.", eo.getClass().getSimpleName()), e);
			}
			
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				Object content = eoMap.get(field.getName());
				ExcelCell cell = transferContent2Cell(content, field, i, row);
				row.addCell(cell);
			}
			sheet.addRow(row);
		}

		addSheet(sheet);
	}

	private Field[] getNotIgnoreFields(Class<? extends ExcelExportEo> eoClass) {

		Field[] fieldsFromFather = tryGetFileldsFromFather(eoClass);
		Field[] fields = getFieldsSelf(eoClass);
		if (fieldsFromFather != null) {
			fields = ArrayUtils.addAll(fieldsFromFather, fields);
		}
		return fields;
	}

	private Field[] getFieldsSelf(Class<? extends ExcelExportEo> eoClass) {
		return doGetFields(eoClass);
	}

	private Field[] tryGetFileldsFromFather(Class<? extends ExcelExportEo> eoClass) {
		Field[] fieldsFromFather = null;
		Class<?> superclass = eoClass.getSuperclass();
		if (ExcelExportEo.class.isAssignableFrom(superclass)) {
			fieldsFromFather = doGetFields((Class<? extends ExcelExportEo>) superclass);
		}
		return fieldsFromFather;
	}

	private Field[] doGetFields(Class<? extends ExcelExportEo> eoClass) {
		Field[] fields = eoClass.getDeclaredFields();
		for (Field field : fields) {
			ExcelIgnore ignoreAnn = field.getAnnotation(ExcelIgnore.class);
			if (ignoreAnn != null) {
				fields = ArrayUtils.removeElement(fields, field);
			}
		}
		return fields;
	}

	/**
	 * 把eo里面的每个字段内容，转换成对应的excelCell
	 * @param content : the value of this filed, CAN be null
	 * @param field : the annotation attached , CAN be null 
	 * */
	private ExcelCell transferContent2Cell(Object content , Field field, int index, ExcelRow parent) {
		
		if (content == null) {
			return CellExportedType.STRING.transferContent2Cell(content, field, index, parent);
		}
		
		content = transferValueIfNecessary(content, field); //从excel cell类型转换为目标类型
		
		if (content instanceof CharSequence) {
			CellExportedType exportedType = getExportedType(field, CellExportedType.STRING);
			return exportedType.transferContent2Cell(content, field, index, parent);
		}
		if (content instanceof Boolean) {
			CellExportedType exportedType = getExportedType(field, CellExportedType.BOOLEAN);
			return exportedType.transferContent2Cell(content, field, index, parent);
		}
		if (content instanceof Number) {
			CellExportedType exportedType = getExportedType(field, CellExportedType.NUMBER);
			return exportedType.transferContent2Cell(content, field, index, parent);
		}
		if (content instanceof Date) {
			CellExportedType exportedType = getExportedType(field, CellExportedType.DATE);
			return exportedType.transferContent2Cell(content, field, index, parent);
		}
		if (content instanceof Calendar) {
			CellExportedType exportedType = getExportedType(field, CellExportedType.CALENDAR);
			return exportedType.transferContent2Cell(content, field, index, parent);
		} 
		// alternatively, we can ignore this cell
		SuperExcelAssert.state(false, String.format("unsupported field type: %s  , can't be set to excel cell.", content.getClass().getSimpleName()));
		return null;
	}

	private Object transferValueIfNecessary(Object value, Field field) {
		if (field != null) {
			CellExportedTypeDefine typeDefineAnn = field.getAnnotation(CellExportedTypeDefine.class);
			if (typeDefineAnn != null) {
				CellExportedType exportedType = typeDefineAnn.toCellType();
				value = exportedType.transferValue(value, field);
			}
		}
		return value;
	}

	/**
	 * @param field : the annotation attached , CAN be null 
	 * */
	private CellExportedType getExportedType(Field field , CellExportedType defaultValue) {
		if (field == null) {
			return defaultValue;
		}
		CellExportedType exportedType;
		CellExportedTypeDefine toTypeAnn = field.getAnnotation(CellExportedTypeDefine.class);
		if (toTypeAnn != null) {
			exportedType = toTypeAnn.toCellType();
		} else {
			exportedType = defaultValue;
		}
		return exportedType;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
