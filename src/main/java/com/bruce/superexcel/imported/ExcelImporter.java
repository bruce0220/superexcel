/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.superexcel.imported;

import com.bruce.superexcel.InvalidExcelDefineException;
import com.google.common.base.Function;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * excel导入
 * @author xiangshaoxu 2016年6月6日下午5:38:33
 * @version 1.0.0
 */
public interface ExcelImporter<F extends GenericExcelImportedEo, T> {

	ExcelImporter<F, T> loadExcel(InputStream is) throws IOException;

	ExcelImporter<F, T> setMetaData(Class<F> voClazz);

	List<F> getEos();

	ExcelImporter<F, T> parse() throws InvalidExcelDefineException;

	ExcelImporter<F, T> feebback2Excel();

	ExcelImporter<F, T> export2Stream(OutputStream out) throws IOException;

	ExcelImporter<F, T> closeExcelStream();

	ExcelImporter<F, T> clearExcelWorkBook();

	ExcelImporter<F, T> addTransferFunction(Function<F, T> f);

	List<T> getDtos();

	ExcelImporter<F, T> executeInFeedback(ExecuteFunction<T> fun);


}
