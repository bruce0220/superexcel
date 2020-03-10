/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.sueprexcel.imported;

import com.bruce.superexcel.InvalidExcelDefineException;
import com.bruce.superexcel.export.PoiExcelExporter;
import com.bruce.superexcel.imported.ExcelImporter;
import com.bruce.superexcel.imported.PoiExcelImporter;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author xiangshaoxu 2016年6月6日下午8:37:48
 * @version 1.0.0
 */
public class ExcelImportTest
 {
	
	@Test
	public void exportFirst() {
		try (OutputStream fos = new FileOutputStream("d:\\exportFirst.xlsx");) {

			List<ExcelImportedEo1> vos = createVos1();
			
			PoiExcelExporter.newInstance().addMetaData(vos, ExcelImportedEo1.class).createExcel().export2Stream(fos);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void importSecond() {
		List<ExcelImportedEo1> retDatas  = null;
		try (InputStream is = new FileInputStream("d:\\exportFirst.xlsx");) {

			ExcelImporter<ExcelImportedEo1, Void> importer = new PoiExcelImporter<ExcelImportedEo1, Void>(false);
			retDatas = importer.setMetaData(ExcelImportedEo1.class).loadExcel(is).parse().getEos();
			
			for (ExcelImportedEo1 d : retDatas) {
				System.out.println();
				System.out.println(d);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidExcelDefineException e) {
			 System.out.println("The excel is invalidate **************************");
			 e.printStackTrace();
		} 
		
	}
	
//	@Test
	public void importAndFeedback() {
		List<ExcelImportedEo1> retDatas  = null;
		try (InputStream is = new FileInputStream("d:\\exportFirst.xlsx");
             OutputStream fos = new FileOutputStream("d:\\feedback.xlsx");
				) {
			
			ExcelImporter<ExcelImportedEo1, Void> importer = new PoiExcelImporter<ExcelImportedEo1, Void>(true);
			retDatas = importer.setMetaData(ExcelImportedEo1.class).loadExcel(is).parse().getEos();
			importer.feebback2Excel().export2Stream(fos).clearExcelWorkBook().closeExcelStream();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidExcelDefineException e) {
			System.out.println("The excel is invalidate **************************");
			e.printStackTrace();
		} 
		
	}
	

	private List<ExcelImportedEo1> createVos1() {
		List<ExcelImportedEo1> vos = new ArrayList<ExcelImportedEo1>();
		for (int i = 0; i < 10; i++) {
			ExcelImportedEo1 vo = new ExcelImportedEo1();
			vo.setAdatalong(new Date(888));
			vo.setAdatashort(new Date());
			vo.setAdatatoString("2008-08-09");
			vo.setAdatatoStringlong("1989-12-01 18:56:08");
			vo.setAdate(new Date());
			vo.setBigdecimal(BigDecimal.valueOf(4.567));
			vo.setBlCampusId("小区");
			vo.setBoolcell(true);
			vo.setBoolcelltoString("TRUE");
			vo.setCalendar(Calendar.getInstance());
			vo.setIntcell1(67);
			vo.setIntcell2(3);
			vo.setInttobool(false);
			vo.setInttocalendarlong(Calendar.getInstance());
			vo.setInttocalendarshort(Calendar.getInstance());
			vo.setInttodatelong(Calendar.getInstance());
			vo.setInttodateshort(Calendar.getInstance());
			vo.setInttostr("454");
			vo.setStrtobool(false);
			vo.setStrtocal(Calendar.getInstance());
			vo.setStrtocallong(Calendar.getInstance());
			vo.setTeststr("dsyrdy");
			vo.setStrtoint(889);
			vo.setStrtodatelong(new Date());
			vo.setStrtodate(new Date());
			vo.setTelephone("1010");
			vos.add(vo);
		} 
		return vos;
	}

}
