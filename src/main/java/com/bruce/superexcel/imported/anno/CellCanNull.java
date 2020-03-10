/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.superexcel.imported.anno;

import java.lang.annotation.*;

/**
 * 标识导入的字段可以为空
 * 
 * @author xiangshaoxu 2016年6月8日下午12:19:51
 * @version 1.0.0
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CellCanNull {

}
