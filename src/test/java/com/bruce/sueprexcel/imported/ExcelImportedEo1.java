/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.sueprexcel.imported;

import com.bruce.superexcel.anno.ExcelIgnore;
import com.bruce.superexcel.export.ExcelExportEo;
import com.bruce.superexcel.export.anno.CellExportedTypeDefine;
import com.bruce.superexcel.export.anno.ExportedHeaderDefine;
import com.bruce.superexcel.export.anno.ExportedSheetDefine;
import com.bruce.superexcel.export.metadata.CellExportedType;
import com.bruce.superexcel.imported.GenericExcelImportedEo;
import com.bruce.superexcel.imported.anno.CellImportedTypeDefine;
import com.bruce.superexcel.imported.anno.CellValidationDefine;
import com.bruce.superexcel.imported.anno.ImportedSheetDefine;
import com.bruce.superexcel.imported.metadata.CellImportedType;
import com.bruce.superexcel.imported.metadata.CellValidationType;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author xiangshaoxu 2016年6月2日下午6:47:59
 * @version 1.0.0
 */
@ImportedSheetDefine(sheetName = "测试sheet1")
@ExportedSheetDefine(sheetName = "测试sheet1")
public class ExcelImportedEo1 extends GenericExcelImportedEo implements ExcelExportEo {

	@ExcelIgnore
	private static final long serialVersionUID = 8824877718758370676L;
	
	@ExportedHeaderDefine(headerName = "归属校区")
	private String blCampusId; // 合同归属校区
	
	@ExportedHeaderDefine(headerName = "teststr")
	private String teststr;
	
	@ExportedHeaderDefine(headerName = "str-to-int")
	@CellImportedTypeDefine(importedType = CellImportedType.NUMBER)
	@CellExportedTypeDefine(toCellType = CellExportedType.STRING)
	private int strtoint;
	
	@ExportedHeaderDefine(headerName = "str-to-date")
	@CellImportedTypeDefine(importedType = CellImportedType.DATE, dateFormat = "yyyy-MM-dd")
	@CellExportedTypeDefine(toCellType = CellExportedType.STRING, dateFormat = "yyyy-MM-dd")
	private Date strtodate;
	
	@ExportedHeaderDefine(headerName = "str-to-date-long")
	@CellImportedTypeDefine(importedType = CellImportedType.DATE, dateFormat = "yyyy-MM-dd HH:mm:ss")
	@CellExportedTypeDefine(toCellType = CellExportedType.STRING, dateFormat = "yyyy-MM-dd HH:mm:ss")
	private Date strtodatelong;
	
	@ExcelIgnore
	@ExportedHeaderDefine(headerName = "str-to-calendar")
	@CellImportedTypeDefine(importedType = CellImportedType.CALENDAR, dateFormat = "yyyy-MM-dd")
	@CellExportedTypeDefine(toCellType = CellExportedType.STRING, dateFormat = "yyyy-MM-dd")
	private Calendar strtocal;
	
	@ExcelIgnore
	@ExportedHeaderDefine(headerName = "str-to-calendar-long")
	@CellImportedTypeDefine(importedType = CellImportedType.CALENDAR, dateFormat = "yyyy-MM-dd HH:mm:ss")
	@CellExportedTypeDefine(toCellType = CellExportedType.STRING, dateFormat = "yyyy-MM-dd HH:mm:ss")
	private Calendar strtocallong;
	
	@ExportedHeaderDefine(headerName = "str-to-bool")
	@CellImportedTypeDefine(importedType = CellImportedType.BOOLEAN)
	@CellExportedTypeDefine(toCellType = CellExportedType.STRING)
	private Boolean strtobool;
	
	@ExportedHeaderDefine(headerName = "intcell1")
	@CellImportedTypeDefine(importedType = CellImportedType.NUMBER)
	@CellExportedTypeDefine(toCellType = CellExportedType.NUMBER)
	private int intcell1;
	
	@ExportedHeaderDefine(headerName = "intcell2")
	private Integer intcell2;
	
	@ExportedHeaderDefine(headerName = "int-to-string")
	@CellImportedTypeDefine(importedType = CellImportedType.STRING)
	@CellExportedTypeDefine(toCellType = CellExportedType.NUMBER)
	private String inttostr;
	
	@ExportedHeaderDefine(headerName = "int-to-bool")
	@CellImportedTypeDefine(importedType = CellImportedType.BOOLEAN)
	@CellExportedTypeDefine(toCellType = CellExportedType.NUMBER)
	private boolean inttobool;

	@ExcelIgnore
	@ExportedHeaderDefine(headerName = "int-to-calendar-long")
	@CellImportedTypeDefine(importedType = CellImportedType.CALENDAR, dateFormat = "yyyy-MM-dd HH:mm:ss")
	@CellExportedTypeDefine(toCellType = CellExportedType.NUMBER, dateFormat = "yyyy-MM-dd HH:mm:ss")
	private Calendar inttocalendarlong;
	
	@ExcelIgnore
	@ExportedHeaderDefine(headerName = "int-to-calendar-short")
	@CellImportedTypeDefine(importedType = CellImportedType.CALENDAR, dateFormat = "yyyy-MM-dd")
	@CellExportedTypeDefine(toCellType = CellExportedType.NUMBER, dateFormat = "yyyy-MM-dd")
	private Calendar inttocalendarshort;
	
	@ExcelIgnore
	@ExportedHeaderDefine(headerName = "int-to-date-long")
	@CellImportedTypeDefine(importedType = CellImportedType.DATE, dateFormat = "yyyy-MM-dd HH:mm:ss")
	@CellExportedTypeDefine(toCellType = CellExportedType.NUMBER, dateFormat = "yyyy-MM-dd HH:mm:ss")
	private Calendar inttodatelong;
	
	@ExcelIgnore
	@ExportedHeaderDefine(headerName = "int-to-date-short")
	@CellImportedTypeDefine(importedType = CellImportedType.DATE, dateFormat = "yyyy-MM-dd")
	@CellExportedTypeDefine(toCellType = CellExportedType.NUMBER, dateFormat = "yyyy-MM-dd")
	private Calendar inttodateshort;
	
	@ExportedHeaderDefine(headerName = "adatelong")
	@CellImportedTypeDefine(importedType = CellImportedType.DATE, dateFormat = "yyyy-MM-dd HH:mm:ss")
	@CellExportedTypeDefine(toCellType = CellExportedType.DATE, dateFormat = "yyyy-MM-dd HH:mm:ss")
	private Date adatalong;
	
	@ExportedHeaderDefine(headerName = "adateshort")
	@CellImportedTypeDefine(importedType = CellImportedType.DATE, dateFormat = "yyyy-MM-dd")
	@CellExportedTypeDefine(toCellType = CellExportedType.DATE, dateFormat = "yyyy-MM-dd")
	private Date adatashort;
	
	@ExportedHeaderDefine(headerName = "adate")
	@CellImportedTypeDefine(importedType = CellImportedType.DATE, dateFormat = "yyyy-MM-dd")
	private Date adate;
	
	@ExportedHeaderDefine(headerName = "adatatoString")
	@CellImportedTypeDefine(importedType = CellImportedType.STRING, dateFormat = "yyyy-MM-dd")
	@CellExportedTypeDefine(toCellType = CellExportedType.DATE, dateFormat = "yyyy-MM-dd")
	private String adatatoString;
	
	@ExportedHeaderDefine(headerName = "adatatoStringlong")
	@CellImportedTypeDefine(importedType = CellImportedType.STRING, dateFormat = "yyyy-MM-dd HH:mm")
	@CellExportedTypeDefine(toCellType = CellExportedType.DATE, dateFormat = "yyyy-MM-dd HH:mm:ss")
	private String adatatoStringlong;
	
	@ExportedHeaderDefine(headerName = "boolcell")
	private Boolean boolcell;
	
	@ExportedHeaderDefine(headerName = "boolcell-to-string")
	private String boolcelltoString;
	
	
	@ExportedHeaderDefine(headerName = "bigdecimal")
	private BigDecimal bigdecimal;
	
	@ExportedHeaderDefine(headerName = "calendar")
	private Calendar calendar;
	
	@ExportedHeaderDefine(headerName = "telephone")
	@CellValidationDefine(type = CellValidationType.REGULAR, regEx = "^[0-9]*$", message = "电话号码必须为数字")
	private String telephone;

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getBlCampusId() {
		return blCampusId;
	}

	public void setBlCampusId(String blCampusId) {
		this.blCampusId = blCampusId;
	}

	public String getTeststr() {
		return teststr;
	}

	public void setTeststr(String teststr) {
		this.teststr = teststr;
	}

	public int getStrtoint() {
		return strtoint;
	}

	public void setStrtoint(int strtoint) {
		this.strtoint = strtoint;
	}

	public Date getStrtodate() {
		return strtodate;
	}

	public void setStrtodate(Date strtodate) {
		this.strtodate = strtodate;
	}

	public Date getStrtodatelong() {
		return strtodatelong;
	}

	public void setStrtodatelong(Date strtodatelong) {
		this.strtodatelong = strtodatelong;
	}

	public Calendar getStrtocal() {
		return strtocal;
	}

	public void setStrtocal(Calendar strtocal) {
		this.strtocal = strtocal;
	}

	public Calendar getStrtocallong() {
		return strtocallong;
	}

	public void setStrtocallong(Calendar strtocallong) {
		this.strtocallong = strtocallong;
	}

	public Boolean getStrtobool() {
		return strtobool;
	}

	public void setStrtobool(Boolean strtobool) {
		this.strtobool = strtobool;
	}

	public int getIntcell1() {
		return intcell1;
	}

	public void setIntcell1(int intcell1) {
		this.intcell1 = intcell1;
	}

	public Integer getIntcell2() {
		return intcell2;
	}

	public void setIntcell2(Integer intcell2) {
		this.intcell2 = intcell2;
	}

	public String getInttostr() {
		return inttostr;
	}

	public void setInttostr(String inttostr) {
		this.inttostr = inttostr;
	}

	public boolean isInttobool() {
		return inttobool;
	}

	public void setInttobool(boolean inttobool) {
		this.inttobool = inttobool;
	}

	public Calendar getInttocalendarlong() {
		return inttocalendarlong;
	}

	public void setInttocalendarlong(Calendar inttocalendarlong) {
		this.inttocalendarlong = inttocalendarlong;
	}

	public Calendar getInttocalendarshort() {
		return inttocalendarshort;
	}

	public void setInttocalendarshort(Calendar inttocalendarshort) {
		this.inttocalendarshort = inttocalendarshort;
	}

	public Calendar getInttodatelong() {
		return inttodatelong;
	}

	public void setInttodatelong(Calendar inttodatelong) {
		this.inttodatelong = inttodatelong;
	}

	public Calendar getInttodateshort() {
		return inttodateshort;
	}

	public void setInttodateshort(Calendar inttodateshort) {
		this.inttodateshort = inttodateshort;
	}

	public Date getAdatalong() {
		return adatalong;
	}

	public void setAdatalong(Date adatalong) {
		this.adatalong = adatalong;
	}

	public Date getAdatashort() {
		return adatashort;
	}

	public void setAdatashort(Date adatashort) {
		this.adatashort = adatashort;
	}

	public Date getAdate() {
		return adate;
	}

	public void setAdate(Date adate) {
		this.adate = adate;
	}

	public String getAdatatoString() {
		return adatatoString;
	}

	public void setAdatatoString(String adatatoString) {
		this.adatatoString = adatatoString;
	}

	public String getAdatatoStringlong() {
		return adatatoStringlong;
	}

	public void setAdatatoStringlong(String adatatoStringlong) {
		this.adatatoStringlong = adatatoStringlong;
	}

	public Boolean getBoolcell() {
		return boolcell;
	}

	public void setBoolcell(Boolean boolcell) {
		this.boolcell = boolcell;
	}

	public String getBoolcelltoString() {
		return boolcelltoString;
	}

	public void setBoolcelltoString(String boolcelltoString) {
		this.boolcelltoString = boolcelltoString;
	}

	public BigDecimal getBigdecimal() {
		return bigdecimal;
	}

	public void setBigdecimal(BigDecimal bigdecimal) {
		this.bigdecimal = bigdecimal;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
