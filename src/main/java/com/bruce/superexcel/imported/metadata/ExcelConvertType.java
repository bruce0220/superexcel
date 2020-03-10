/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.superexcel.imported.metadata;

import com.bruce.superexcel.InvalidExcelCellDataException;
import com.bruce.superexcel.imported.CellParseCtx;
import com.bruce.superexcel.imported.anno.CellImportedConverterDefine;
import com.bruce.superexcel.utils.SuperExcelAssert;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * @author xiangshaoxu 2016年6月8日上午12:47:57
 * @version 1.0.0
 */
public enum ExcelConvertType {

//	SQL {
//		/**尝试转换sql语句，如果没有记录返回null
//		 * @throws InvalidExcelCellDataException */
//		@Override
//		protected Object doTransfer(CellImportedConverterDefine ann, Object value, Field field) throws InvalidExcelCellDataException {
//			String sql = ann.sql();//TODO 增加ctx
//			sql = sql.replace("${value}", value.toString());//FIXME if not string, change to prepare statement
//			sql = roleQLConfigService.replaceExpression(sql);
//			boolean throwIfMoreThanOneReturn = ann.throwIfMoreThanOneReturn();
//
//			RowMapper<Object> extractor = new RowMapper<Object>() {
//
//				@Override
//				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//					return rs.getObject(1);
//				}
//
//			};
//			List<Object> list = jdbcTemplate.query(sql, extractor);
//			if (list.size() > 1 && throwIfMoreThanOneReturn) {
//				String message = String.format("more than one result returned , but not allowed. Filed: %s, Value: %s", field.getName(), value);
//				String validateMsg = String.format("系统存在同名[%s]，请修改", ImportedEoUtil.getEoFieldName(field));
//				throw new InvalidExcelCellDataException(message, validateMsg);
//			}
//			if(list.size() > 0) {
//				return list.get(0);
//			} else {
//				String message = String.format("cannot find any record. Filed: %s, Value: %s", field.getName(), value);
//				String validateMsg = String.format("[%s]不存在", ImportedEoUtil.getEoFieldName(field));
//				throw new InvalidExcelCellDataException(message, validateMsg);
//			}
//		}
//	},
	ENUM {
		@Override
		protected Object doTransfer(CellImportedConverterDefine ann, Object value, Field field) throws InvalidExcelCellDataException{
			Class<?> enumClazz = ann.enumClazz();
			try {
				Method method = enumClazz.getMethod("fromLabel", String.class);
				method.setAccessible(true);
				return method.invoke(enumClazz, value);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				SuperExcelAssert.state(false,
						String.format("%s enum define error, can't invoke fromLabel method, field: %s ", ann.getClass().getSimpleName(), field.getName()), e);
				return null; // can't reach here
			}
		}
	};

//	JdbcTemplate jdbcTemplate = ApplicationContextUtil.getContext().getBean(JdbcTemplate.class);//FIXME
//	RoleQLConfigService roleQLConfigService = ApplicationContextUtil.getContext().getBean(RoleQLConfigService.class);
	
	public Object transfer(CellImportedConverterDefine ann, Object value, Field field, CellParseCtx ctx) throws InvalidExcelCellDataException{
		Object transfered = ctx.getObject(field, value);
		if (transfered == null) {
			transfered = doTransfer(ann, value, field);
			ctx.putObject(field, value, transfered);
		}  
		return transfered;
	}
			
	protected abstract Object doTransfer(CellImportedConverterDefine ann, Object value, Field field) throws InvalidExcelCellDataException;
}
