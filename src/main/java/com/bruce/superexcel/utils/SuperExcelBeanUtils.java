/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.superexcel.utils;

import org.apache.commons.beanutils.PropertyUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 实现bean map 互转, 如果字段为空返回null
 * 
 * @author xiangshaoxu 2016年6月2日下午7:57:14
 * @version 1.0.0
 */
public class SuperExcelBeanUtils {

	public static Map<String, Object> convertBean(Object bean) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
		Class<?> type = bean.getClass();
		Map<String, Object> returnMap = new HashMap<>();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);

		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				SuperExcelAssert.isNotNull(readMethod, String.format("can't get default getter of bean: %s", bean.getClass().getSimpleName()));
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					returnMap.put(propertyName, result);
				} else {
					returnMap.put(propertyName, null);
				}
			}
		}
		return returnMap;
	}
	
	/**
	 * 返回属性名与setter map, 仅仅支持默认setter
	 * */
	public static Map<String, Method> getFieldName2SetterByBean(Class<?> clazz) throws IntrospectionException {
		Map<String, Method> returnMap = new HashMap<>();
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
		
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method writeMethod = descriptor.getWriteMethod();
				returnMap.put(propertyName, writeMethod);
			}
		}
		return returnMap;
	}
	
	
	/**
	 * 返回属性名与setter map, 使用set+FieldName方式返回
	 * */
	public static Map<String, Method> getFieldName2Setter(Class<?> clazz) throws IntrospectionException {
		Map<String, Method> returnMap = new HashMap<>();
		Method[] methods = clazz.getMethods();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			String setterName = "set" + SuperExcelStringUtils.firstCharUpper(fieldName);
			for (Method m : methods) {
				if (m.getName().equals(setterName)) {
					returnMap.put(fieldName, m);
				}
			}
		}
		return returnMap;
	}
	
	/**
	 * 使用getter取得object的field值
	 *
	 * @param field
	 * @return
	 */
	public static Object getFieldValue(Object target, Field field) {

		String fieldName = field.getName();
		Object ret = null;
		try {
			Method readMethod = PropertyUtils.getPropertyDescriptor(target, fieldName).getReadMethod();
			ret = readMethod.invoke(target);
		} catch (ReflectiveOperationException e) {
			SuperExcelAssert.state(false, String.format("failed to reflect getter, Target:%s, Field:%s", target, field), e);
		}
		return ret;
	}
	
	/**根据名字取得方法， 未找到则返回null*/
	public static Method getMethod(String methodName, Class clazz ) {
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				return method;
			}
		}
		return null;
	}
}
