/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.sueprexcel.export;


import com.bruce.superexcel.anno.ExcelIgnore;
import com.bruce.superexcel.export.ExcelExportEo;
import com.bruce.superexcel.export.anno.CellExportedTypeDefine;
import com.bruce.superexcel.export.anno.ExportedHeaderDefine;
import com.bruce.superexcel.export.anno.ExportedSheetDefine;
import com.bruce.superexcel.export.metadata.CellExportedType;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author xiangshaoxu 2016年6月2日下午6:47:59
 * @version 1.0.0
 */
@ExportedSheetDefine(sheetName = "测试sheet3", defaultColumnWidth = 10)
public class ExcelExportEo3 implements ExcelExportEo {

	@ExcelIgnore
	private static final long serialVersionUID = 8824877718758370676L;

	@ExportedHeaderDefine(headerName = "序号", cellWidth = 5)
	private Integer id;
	
	@ExportedHeaderDefine(headerName = "int-to-String", cellWidth = 5)
	@CellExportedTypeDefine(toCellType = CellExportedType.STRING)
	private int intToString;
	
	@ExportedHeaderDefine(headerName = "number-to-date")
	@CellExportedTypeDefine(toCellType = CellExportedType.DATE, dateFormat = "yyyy-MM-dd")
	private Long numToDate;
	
	@ExportedHeaderDefine(headerName = "number-to-bool")
	@CellExportedTypeDefine(toCellType = CellExportedType.BOOLEAN)
	private Long numtobool;
	
	@ExportedHeaderDefine(headerName = "number-to-calendar")
	@CellExportedTypeDefine(toCellType = CellExportedType.CALENDAR, dateFormat = "yyyy-MM-dd hh:mm:ss")
	private Long numtocalendar;
	
	@ExportedHeaderDefine(headerName = "姓名", cellWidth = 10)
	private String name;
	
	@ExportedHeaderDefine(headerName = "String-to-int", cellWidth = 10)
	@CellExportedTypeDefine(toCellType = CellExportedType.NUMBER)
	private String strtoint;
	
	@ExportedHeaderDefine(headerName = "String-to-double", cellWidth = 10)
	@CellExportedTypeDefine(toCellType = CellExportedType.NUMBER)
	private String strtodouble;
	
	@ExportedHeaderDefine(headerName = "String-to-bigdecimal", cellWidth = 10)
	@CellExportedTypeDefine(toCellType = CellExportedType.NUMBER)
	private String strtobigdecimal;
	
	@ExportedHeaderDefine(headerName = "String-to-date-long", cellWidth = 20)
	@CellExportedTypeDefine(toCellType = CellExportedType.DATE, dateFormat = "yyyy-MM-dd HH:mm:ss")
	private String strtodateLong;
	
	@ExportedHeaderDefine(headerName = "String-to-date-short", cellWidth = 20)
	@CellExportedTypeDefine(toCellType = CellExportedType.DATE, dateFormat = "yyyy-MM-dd")
	private String strtodateShort;
	
	@ExportedHeaderDefine(headerName = "String-to-calendar", cellWidth = 20)
	@CellExportedTypeDefine(toCellType = CellExportedType.CALENDAR, dateFormat = "yyyy-MM-dd")
	private String strtocalendar;
	
	@ExportedHeaderDefine(headerName = "String-to-calendar-long", cellWidth = 20)
	@CellExportedTypeDefine(toCellType = CellExportedType.CALENDAR, dateFormat = "yyyy-MM-dd HH:mm:ss")
	private String strtocalendarlong;
	
	@ExportedHeaderDefine(headerName = "numtostr", cellWidth = 20)
	@CellExportedTypeDefine(toCellType = CellExportedType.STRING)
	private int numtostr;
	
	
	
	@ExportedHeaderDefine(headerName = "String-to-bool", cellWidth = 10)
//	@ExcelCellExportTypeDefine(exportedType = ExcelCellExportedType.BOOLEAN)
	private String strtobool;
	
	public String getStrtobool() {
		return strtobool;
	}

	public void setStrtobool(String strtobool) {
		this.strtobool = strtobool;
	}

	@ExportedHeaderDefine(headerName = "字符串时间", cellWidth = 20)
	private String dateStr;
	
	@ExportedHeaderDefine(headerName = "字符串时间转date", cellWidth = 20)
	@CellExportedTypeDefine(toCellType = CellExportedType.DATE, dateFormat = "yyyy-MM-dd")
	private String dateStr2Date;
	
	@ExportedHeaderDefine(headerName = "时间")
	private Date date;
	
	@ExportedHeaderDefine(headerName = "日历")
	private Calendar cal;
	
	@ExportedHeaderDefine(headerName = "整数")
	private int inta;
	
	@ExportedHeaderDefine(headerName = "Bool")
	private Boolean big;
	
	@ExportedHeaderDefine(headerName = "bigdecimal")
    BigDecimal dec;
	
	@ExportedHeaderDefine(headerName = "sssboolean")
	boolean smallbool;
	
	@ExportedHeaderDefine(headerName = "bbbboolean")
	boolean bigbool;
	
	@ExportedHeaderDefine(headerName = "url", cellWidth = 20)
	@CellExportedTypeDefine(toCellType = CellExportedType.STRING ,isUrl = true, urlLinkLabel = "link")
    String url;
	
	@ExportedHeaderDefine(headerName = "jpg")
	byte[] pics;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Calendar getCal() {
		return cal;
	}

	public void setCal(Calendar cal) {
		this.cal = cal;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public byte[] getPics() {
		return pics;
	}

	public void setPics(byte[] pics) {
		this.pics = pics;
	}

	public int getInta() {
		return inta;
	}

	public void setInta(int inta) {
		this.inta = inta;
	}

	public String getStrtocalendarlong() {
		return strtocalendarlong;
	}

	public void setStrtocalendarlong(String strtocalendarlong) {
		this.strtocalendarlong = strtocalendarlong;
	}

	public BigDecimal getDec() {
		return dec;
	}

	public void setDec(BigDecimal dec) {
		this.dec = dec;
	}

	public Boolean getBig() {
		return big;
	}

	public void setBig(Boolean big) {
		this.big = big;
	}

	public int getIntToString() {
		return intToString;
	}

	public void setIntToString(int intToString) {
		this.intToString = intToString;
	}

	public Long getNumToDate() {
		return numToDate;
	}

	public void setNumToDate(Long numToDate) {
		this.numToDate = numToDate;
	}

	public Long getNumtobool() {
		return numtobool;
	}

	public void setNumtobool(Long numtobool) {
		this.numtobool = numtobool;
	}

	public Long getNumtocalendar() {
		return numtocalendar;
	}

	public void setNumtocalendar(Long numtocalendar) {
		this.numtocalendar = numtocalendar;
	}

	public String getDateStr2Date() {
		return dateStr2Date;
	}

	public void setDateStr2Date(String dateStr2Date) {
		this.dateStr2Date = dateStr2Date;
	}


	public String getStrtodouble() {
		return strtodouble;
	}

	public void setStrtodouble(String strtodouble) {
		this.strtodouble = strtodouble;
	}

	public String getStrtobigdecimal() {
		return strtobigdecimal;
	}

	public void setStrtobigdecimal(String strtobigdecimal) {
		this.strtobigdecimal = strtobigdecimal;
	}


	public String getStrtodateLong() {
		return strtodateLong;
	}

	public void setStrtodateLong(String strtodateLong) {
		this.strtodateLong = strtodateLong;
	}

	public String getStrtodateShort() {
		return strtodateShort;
	}

	public void setStrtodateShort(String strtodateShort) {
		this.strtodateShort = strtodateShort;
	}

	public String getStrtoint() {
		return strtoint;
	}

	public void setStrtoint(String strtoint) {
		this.strtoint = strtoint;
	}

	public String getStrtocalendar() {
		return strtocalendar;
	}

	public void setStrtocalendar(String strtocalendar) {
		this.strtocalendar = strtocalendar;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getNumtostr() {
		return numtostr;
	}

	public void setNumtostr(int numtostr) {
		this.numtostr = numtostr;
	}

	public boolean isSmallbool() {
		return smallbool;
	}

	public void setSmallbool(boolean smallbool) {
		this.smallbool = smallbool;
	}

	public boolean isBigbool() {
		return bigbool;
	}

	public void setBigbool(boolean bigbool) {
		this.bigbool = bigbool;
	}



	
}
