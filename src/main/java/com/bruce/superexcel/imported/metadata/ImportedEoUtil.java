/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.superexcel.imported.metadata;

import com.bruce.superexcel.InvalidExcelCellDataException;
import com.bruce.superexcel.imported.anno.CellCanNull;
import com.bruce.superexcel.imported.anno.CellImportedHeaderDefine;

import java.lang.reflect.Field;

/**
 * 
 * @author xiangshaoxu 2016年6月13日下午11:33:28
 * @version 1.0.0
 */
public abstract class ImportedEoUtil {
	
	/**
	 * 取得ImportedEo.field名字，优先取得{@link CellImportedHeaderDefine} ， 然后去field.getName()
	 * */
	public static String getEoFieldName(Field field) {
		CellImportedHeaderDefine ann = field.getAnnotation(CellImportedHeaderDefine.class);
		if (ann != null) {
			return ann.value();
		} else {
			return field.getName();
		}
	}
	
	/**
	 * 检查是否违背必填约束
	 * */
	public static void checkNullable(Object value, Field field) throws InvalidExcelCellDataException {
		CellCanNull ann = field.getAnnotation(CellCanNull.class);
		if (ann == null) { //不能为空
			String fieldName = ImportedEoUtil.getEoFieldName(field);
			String errorMsg = String.format(
					"the trasfered value is null, field: %s, value: %s", fieldName, value);
			String validateMsg = String.format("[%s]不能为空", fieldName);
			throw new InvalidExcelCellDataException(errorMsg, validateMsg);
		}
	}
}
