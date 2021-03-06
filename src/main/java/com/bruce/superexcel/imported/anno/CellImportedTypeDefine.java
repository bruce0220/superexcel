/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.superexcel.imported.anno;

import com.bruce.superexcel.imported.metadata.CellImportedType;

import java.lang.annotation.*;

/**
 * 通过注解把field转换为特殊的类型，tag注解
 * 
 * @author xiangshaoxu 2016年6月5日下午6:02:28
 * @version 1.0.0
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CellImportedTypeDefine {
	
	CellImportedType importedType();
	
	String dateFormat() default "";
	
	 boolean isUrl() default false;
	
}
