/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.superexcel.imported.metadata;

import com.bruce.superexcel.SuperExcelNumberConverter;
import com.bruce.superexcel.InvalidExcelCellDataException;
import com.bruce.superexcel.imported.CellParseCtx;
import com.bruce.superexcel.imported.anno.CellImportedConverterDefine;
import com.bruce.superexcel.imported.anno.CellImportedTypeDefine;
import com.bruce.superexcel.imported.anno.CellValidationDefine;
import com.bruce.superexcel.utils.SuperExcelAssert;
import com.bruce.superexcel.utils.SuperExcelStringUtils;
import com.google.common.base.Function;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.CalendarConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * excel单元格导入类型，默认根据vo.field自动匹配，不需要设置， 有特殊要求时可以加
 * {@link CellImportedTypeDefine}注解设置
 * 
 * @author xiangshaoxu 2016年6月5日下午8:58:59
 * @version 1.0.0
 */
public enum CellImportedType {

	DATE {

		@Override
		public void setValue2Eo(Object value, Field field, Object vo, Method setter, CellParseCtx cellCtx) throws InvalidExcelCellDataException {
			value = convertIfNecessary(value, field, cellCtx);
			if (value == null) {
				ImportedEoUtil.checkNullable(value, field);
				return;
			}
			invokeSetter(value, vo, setter, field);
		}

		@Override
		public Object transferValue(Object value, Field field) throws InvalidExcelCellDataException {
			String dateFormat = getDateFormatPattern(field);

			DateConverter dateConvert = new DateConverter();
			dateConvert.setPattern(dateFormat);
			dateConvert.setUseLocaleFormat(true);
			ConvertUtils.register(dateConvert, Date.class);

			return executeAndTransferException(value, field, new Function<Object, Object>() {

				@Override
				public Object apply(Object input) {
					return ConvertUtils.convert(input, Date.class);
				}
				
			});
		}
		
	},
	STRING {
		@Override
		public void setValue2Eo(Object value, Field field, Object vo, Method setter, CellParseCtx cellCtx) throws InvalidExcelCellDataException{
			validateCell(field, value);
			value = convertIfNecessary(value, field, cellCtx);
			if (value == null) {
				ImportedEoUtil.checkNullable(value, field);
				return;
			}
			invokeSetter(value, vo, setter, field);
		}

		/**根据CellValidationDefine配置进行字段校验
		 * @throws InvalidExcelCellDataException */
		private void validateCell(Field field, Object value) throws InvalidExcelCellDataException {
			CellValidationDefine ann = field.getAnnotation(CellValidationDefine.class);
			if (ann != null) {
				CellValidationType type = ann.type();
				SuperExcelAssert.state(type == CellValidationType.REGULAR, "we only use regular cellValidationType in String cell now.");
				String regEx = ann.regEx();
				if (SuperExcelStringUtils.isNotBlank(regEx)) {
					String message = ann.message();
					Pattern pattern = Pattern.compile(regEx);
					SuperExcelAssert.isInstanceOf(CharSequence.class, value);
					Matcher matcher = pattern.matcher((CharSequence)value);
					if (!matcher.matches()) {
						String fieldName = ImportedEoUtil.getEoFieldName(field);
						String errorMsg = String.format("field: %s validate failed.", fieldName);
						String validateMsg = String.format("[%s]格式有误", fieldName);
						if(SuperExcelStringUtils.isNotBlank(message)){
							validateMsg += '：' + message;
						}
						throw new InvalidExcelCellDataException(errorMsg, validateMsg);
					}
				}
			}
		}

		@Override
		public Object transferValue(Object value, Field field) throws InvalidExcelCellDataException{
			
			return executeAndTransferException(value, field, new Function<Object, Object>() {

				@Override
				public Object apply(Object input) {
					return ConvertUtils.convert(input, String.class);
				}
				
			});
		}
	},
	NUMBER {
		@Override
		public void setValue2Eo(Object value, Field field, Object vo, Method setter, CellParseCtx cellCtx) throws InvalidExcelCellDataException{
			value = convertIfNecessary(value, field, cellCtx);
			if (value == null) {
				ImportedEoUtil.checkNullable(value, field);
				return;
			}
			invokeSetter(value, vo, setter, field);
		}

		@Override
		public Object transferValue(Object value, Field field) throws InvalidExcelCellDataException {
			return executeAndTransferException(value, field, new Function<Object, Object>() {

				@Override
				public Object apply(Object input) {
					return ConvertUtils.convert(input, Number.class);
				}
				
			});
		}
	},
	BOOLEAN {
		@Override
		public void setValue2Eo(Object value, Field field, Object vo, Method setter, CellParseCtx cellCtx) throws InvalidExcelCellDataException{
			value = convertIfNecessary(value, field, cellCtx);
			if (value == null) {
				ImportedEoUtil.checkNullable(value, field);
				return;
			}
			invokeSetter(value, vo, setter, field);
		}

		@Override
		public Object transferValue(Object value, Field field) throws InvalidExcelCellDataException {
			
			return executeAndTransferException(value, field, new Function<Object, Object>() {

				@Override
				public Object apply(Object input) {
					return ConvertUtils.convert(input, Boolean.class);
				}
				
			});
		}
	},
	CALENDAR {
		@Override
		public void setValue2Eo(Object value, Field field, Object vo, Method setter, CellParseCtx cellCtx) throws InvalidExcelCellDataException{
			value = convertIfNecessary(value, field, cellCtx);
			if (value == null) {
				ImportedEoUtil.checkNullable(value, field);
				return;
			}
			invokeSetter(value, vo, setter, field);
		}

		@Override
		public Object transferValue(Object value, Field field) throws InvalidExcelCellDataException {

			String dateFormat = getDateFormatPattern(field);

			CalendarConverter dateConvert = new CalendarConverter();
			dateConvert.setPattern(dateFormat);
			dateConvert.setUseLocaleFormat(true);
			ConvertUtils.register(dateConvert, Calendar.class);

			return executeAndTransferException(value, field, new Function<Object, Object>() {

				@Override
				public Object apply(Object input) {
					return ConvertUtils.convert(input, Calendar.class);
				}
				
			});
		}
	};

	static {
		ConvertUtils.register(new SuperExcelNumberConverter(), Number.class);
		DoubleConverter doubleConverter = new DoubleConverter();
		doubleConverter.setUseLocaleFormat(true);
		ConvertUtils.register(doubleConverter, Double.class);
	}

	/**把excel单元格中的数据调用vo的反射方法进行设置*/
	public abstract void setValue2Eo(Object value, Field field, Object vo, Method setter, CellParseCtx cellCtx) throws InvalidExcelCellDataException;

	/**当数据vo定义类型与excel cell不一致的时候进行转换*/
	public abstract Object transferValue(Object value, Field field) throws InvalidExcelCellDataException;
	
	protected void invokeSetter(Object value, Object vo, Method setter, Field field) throws InvalidExcelCellDataException {
		try {
			Class<?>[] paramTypes = setter.getParameterTypes();
			SuperExcelAssert.state(paramTypes != null && paramTypes.length == 1, String.format("invalid setter: %s of vo, only one param allowed ", setter.getName()));
			Class<?> clazz = paramTypes[0];
			//内部转换，例如number转为方法调用特定的double
			value = ConvertUtils.convert(value, clazz);
			setter.invoke(vo, value);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			SuperExcelAssert.state(false, String.format("unbale to invoke setter: %s", setter.getName()), e);
		} catch (RuntimeException e) {
			String fieldName = ImportedEoUtil.getEoFieldName(field);
			String errorMsg = String.format("failed to convert ,field: %s", fieldName);
			String validateMsg = String.format("\"%s\"格式错误", fieldName);
			throw new InvalidExcelCellDataException(errorMsg, e , validateMsg);
		}
	}
	
	protected String getDateFormatPattern(Field field) {
		if (field == null) {
			return null;
		} else {
			CellImportedTypeDefine ann = field.getAnnotation(CellImportedTypeDefine.class);
			SuperExcelAssert.state(ann != null, String.format("ExcelCellImportedTypeDefine needed of field: %s", field.getName()));
			String dateFormat = ann.dateFormat();
			SuperExcelAssert.isNotBlank(dateFormat, String.format("dateFormat of  ExcelCellImportedTypeDefine needed of field: %s", field.getName()));
			return dateFormat;
		}
	}

	/**执行function,并转换运行时异常为受控异常*/
	protected Object executeAndTransferException(Object object, Field field, Function<Object, Object> f) throws InvalidExcelCellDataException {
		try {
			return f.apply(object);
		} catch (RuntimeException e) {
			String fieldName = ImportedEoUtil.getEoFieldName(field);
			String errorMsg = String.format("failed to convert ,field: %s", fieldName);
			String validateMsg = String.format("\"%s\"格式错误", fieldName);
			throw new InvalidExcelCellDataException(errorMsg, e , validateMsg);
		}
	}
	
	/**根据{@link CellImportedConverterDefine} 进行转换
	 * @throws InvalidExcelCellDataException */
	protected Object convertIfNecessary(Object value, Field field, CellParseCtx cellCtx) throws InvalidExcelCellDataException {
		CellImportedConverterDefine ann = field.getAnnotation(CellImportedConverterDefine.class);
		if (ann != null) {
			value = ann.type().transfer(ann, value, field, cellCtx);
		}
		return value;
	}
	
}
