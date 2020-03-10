/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.sueprexcel.export;

import com.bruce.superexcel.export.PoiExcelExporter;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * 
 * @author xiangshaoxu 2016年6月2日下午6:46:20
 * @version 1.0.0
 */
public class ExcelExportTest {

	
	@Test
	public void testSingleVo() {
		try (OutputStream fos = new FileOutputStream("d:\\testSingleVo.xlsx");) {

			List<ExcelExportEo1> vos = createVos1();

			PoiExcelExporter.newInstance().addMetaData(vos, ExcelExportEo1.class).createExcel().export2Stream(fos);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSingleVoWithErrorData() {
		try (OutputStream fos = new FileOutputStream("d:\\testSingleVoWithErrorData.xlsx");) {
			
			List<ExcelExportEo1> vos = createVos1WithErrorData();
			
			PoiExcelExporter.newInstance().addMetaData(vos, ExcelExportEo1.class).createExcel().export2Stream(fos);
			fail("need one eduRuntimeException at least.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMultiVo() {
		try (OutputStream fos = new FileOutputStream("d:\\testMultiVo.xlsx");) {
			
			List<ExcelExportEo1> vos = createVos1();
			List<ExcelExportEo2> vos2 = createVos2();
			List<ExcelExportEo3> vos3 = createVos3();
			
			PoiExcelExporter.newInstance().addMetaData(vos, ExcelExportEo1.class).addMetaData(vos2, ExcelExportEo2.class).addMetaData(vos3, ExcelExportEo3.class).createExcel().export2Stream(fos);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOneMap() {
		try (OutputStream fos = new FileOutputStream("d:\\testOneMap.xlsx");) {
			
			List<ExcelExportEo1> vos = createVos1();
			
			String sheetName = "sheet名from map";
			String[] headers = {"ID", "姓名", "日期", "日历", "长整型", "BigDecimal"};
			String[] columns = {"id", "name", "date","calendar", "long", "big"} ;
			List<Map<String, Serializable>> mapList = new ArrayList<>();
			Map<String, Serializable> row1 = new HashMap<>();
			row1.put("id", Integer.valueOf(1));
			row1.put("name", "向王");
			row1.put("date", new Date());
			row1.put("long", 1L);
			row1.put("big", BigDecimal.valueOf(2.234));
			row1.put("calendar", Calendar.getInstance());
			mapList.add(row1);
			mapList.add(row1);
			
			PoiExcelExporter.newInstance().addMetaData(sheetName, headers, columns, mapList).createExcel().export2Stream(fos);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMapAndVo() {
		try (OutputStream fos = new FileOutputStream("d:\\testMapAndVo.xlsx");) {
			
			List<ExcelExportEo1> vos = createVos1();
			
			String sheetName = "sheet名from map";
			String[] headers = {"ID", "姓名", "日期", "日历", "长整型", "BigDecimal"};
			String[] columns = {"id", "name", "date","calendar", "long", "big"} ;
			List<Map<String, Serializable>> mapList = new ArrayList<>();
			Map<String, Serializable> row1 = new HashMap<>();
			row1.put("id", Integer.valueOf(1));
			row1.put("name", "向王");
			row1.put("date", new Date());
			row1.put("long", 1L);
			row1.put("big", BigDecimal.valueOf(2.234));
			row1.put("calendar", Calendar.getInstance());
			mapList.add(row1);
			mapList.add(row1);
			
			PoiExcelExporter.newInstance().addMetaData(vos, ExcelExportEo1.class).addMetaData(sheetName, headers, columns, mapList).createExcel().export2Stream(fos);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTwoMap() {
		try (OutputStream fos = new FileOutputStream("d:\\testTwoMap.xlsx");) {
			
			List<ExcelExportEo1> vos = createVos1();
			
			String sheetName = "sheet名from map";
			String[] headers = {"ID", "姓名", "日期", "日历", "长整型", "BigDecimal"};
			String[] columns = {"id", "name", "date","calendar", "long", "big"} ;
			List<Map<String, Serializable>> mapList = new ArrayList<>();
			Map<String, Serializable> row1 = new HashMap<>();
			row1.put("id", Integer.valueOf(1));
			row1.put("name", "向王");
			row1.put("date", new Date());
			row1.put("long", 1L);
			row1.put("big", BigDecimal.valueOf(2.234));
			row1.put("calendar", Calendar.getInstance());
			mapList.add(row1);
			mapList.add(row1);
			
			PoiExcelExporter.newInstance().addMetaData(sheetName, headers, columns, mapList).addMetaData("shett2", headers, columns, mapList).createExcel()
					.export2Stream(fos);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<ExcelExportEo1> createVos1() {
		List<ExcelExportEo1> vos = new ArrayList<ExcelExportEo1>();
		vos.add(createVo());
		vos.add(createVo());
		vos.add(createVo());
		vos.add(createVo());
		vos.add(createVo());
		return vos;
	}
	
	private List<ExcelExportEo1> createVos1WithErrorData() {
		List<ExcelExportEo1> vos = new ArrayList<ExcelExportEo1>();
		vos.add(createVoWithError());
		vos.add(createVoWithError());
		vos.add(createVoWithError());
		vos.add(createVoWithError());
		vos.add(createVoWithError());
		return vos;
	}
	
	private List<ExcelExportEo2> createVos2() {
		List<ExcelExportEo2> vos = new ArrayList<ExcelExportEo2>();
		vos.add(createVo2());
		vos.add(createVo2());
		vos.add(createVo2());
		vos.add(createVo2());
		vos.add(createVo2());
		return vos;
	}
	private List<ExcelExportEo3> createVos3() {
		List<ExcelExportEo3> vos = new ArrayList<ExcelExportEo3>();
		vos.add(createVo3());
		vos.add(createVo3());
		vos.add(createVo3());
		vos.add(createVo3());
		vos.add(createVo3());
		return vos;
	}

	private ExcelExportEo1 createVo() {
		ExcelExportEo1 vo1 = new ExcelExportEo1();
		 vo1.setCal(Calendar.getInstance());
		 vo1.setDate(new Date());
		 vo1.setDate2(new Date());
		 vo1.setDateStr("2001-09-08");
		 vo1.setDec(new BigDecimal(9.23456));
		 
		 vo1.setId(1);
		 vo1.setIntToString(40000000);
		 vo1.setNumtobool(0L);
		 vo1.setNumtocalendar(999988888L);
		 vo1.setNumToDate(999999999L);
		 
		 vo1.setStrtobigdecimal("3454.67");
		 vo1.setStrtobool("Y");
		 
		 vo1.setStrtodateLong("1999-12-16 16:12:02");
		 vo1.setStrtodateShort("1999-12-16");
		 
		 vo1.setStrtocalendar("2009-09-08");
		 vo1.setStrtocalendarlong("1999-12-16 16:19:02");
		 
		 vo1.setStrtodouble("5456.8");
		 vo1.setStrtoint("4");
		 
		 vo1.setDateStr2Date("2003-12-08");
		 
		 vo1.setNumtostr(8888);
		 
		 vo1.setInta(4);
		 vo1.setBig(true);
		 
		 vo1.setName("中文");
		 
		 vo1.setSmallbool(false);
		 vo1.setBigbool(true);
		 
		 vo1.setUrl("http://www.baidu.com");
		 return vo1;
	}
	
	private ExcelExportEo1 createVoWithError() {
		ExcelExportEo1 vo1 = new ExcelExportEo1();
		vo1.setCal(Calendar.getInstance());
		vo1.setDate(new Date());
		vo1.setDateStr("2001-09-08");
		vo1.setDec(new BigDecimal(9.23456));
		
		vo1.setId(1);
		vo1.setIntToString(40000000);
		vo1.setNumtobool(0L);
		vo1.setNumtocalendar(999988888L);
		vo1.setNumToDate(999999999L);
		
		vo1.setStrtobigdecimal("3454.67");
		vo1.setStrtobool("Y");
		
		vo1.setStrtodateLong("1999-12-16 16:12:02");
		vo1.setStrtodateShort("1999-12-16");
		
		vo1.setStrtocalendar("2009-09-08");
		vo1.setStrtocalendarlong("1999-12-16 16:19:02");
		
		vo1.setStrtodouble("89");//ERR
		vo1.setStrtoint("4");
		
		vo1.setNumtostr(8888);
		
		vo1.setInta(4);
		vo1.setBig(true);
		
		vo1.setName("中文");
		
		vo1.setSmallbool(false);
		vo1.setBigbool(true);
		
		vo1.setUrl("http://www.baidu.com");
		return vo1;
	}
	
	private ExcelExportEo2 createVo2() {
		ExcelExportEo2 vo1 = new ExcelExportEo2();
		vo1.setCal(Calendar.getInstance());
		vo1.setDate(new Date());
		vo1.setDateStr("2001-09-08");
		vo1.setDec(new BigDecimal(9.23456));
		vo1.setId(1);
		vo1.setInta(4);
		vo1.setBig(true);
		vo1.setName("中文");
		vo1.setUrl("http://www.baidu.com");
		return vo1;
	}
	
	private ExcelExportEo3 createVo3() {
		ExcelExportEo3 vo1 = new ExcelExportEo3();
		vo1.setCal(Calendar.getInstance());
		vo1.setDate(new Date());
		vo1.setDateStr("2001-09-08");
		vo1.setDec(new BigDecimal(9.23456));
		vo1.setId(1);
		vo1.setInta(4);
		vo1.setBig(true);
		vo1.setName("中文");
		vo1.setUrl("http://www.baidu.com");
		return vo1;
	}

}
