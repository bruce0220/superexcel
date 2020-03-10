package com.bruce.superexcel.export.template;

import com.bruce.superexcel.export.CellVistor;
import com.bruce.superexcel.export.metadata.ExcelCell;
import com.bruce.superexcel.export.metadata.ExcelRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 座位表模板
 * @author yuanzhidong 2016-09-12
 *
 */
public class ClassroomSeatTemplate {

	private Map<String,ExcelCell> keycell = new HashMap<String,ExcelCell>();
	private List<ExcelRow> rowlist = new ArrayList<ExcelRow>();
	private Integer startRowIndex = 0;
	private Integer startColumnIndex = 0;
	
	/*
	 * 创建默认模板
	 * */
	public ClassroomSeatTemplate(){
		startRowIndex = 1;
		startColumnIndex = 1;
		ExcelRow row = new ExcelRow(0, null);
		putCell(new TemplateCell(0, "studentName", row));
		putCell(new TemplateCell(1, "seatNumber", row));
		rowlist.add(row);
		
		row = new ExcelRow(1, null);
		putCell(new TemplateCell(0, "studentContact", row));
		rowlist.add(row);
		
		row = new ExcelRow(2, null);
		putCell(new TemplateCell(0,"parentContact",row));
		rowlist.add(row);
		
		row =  new ExcelRow(3, null);
		putCell(new TemplateCell(0,"studentSchool",row));
		rowlist.add(row);
		
		row =  new ExcelRow(4, null);
		putCell(new TemplateCell(0,"classAndGrade",row));
		rowlist.add(row);

		row =  new ExcelRow(5, null);
		putCell(new TemplateCell(0,"end",row));
		rowlist.add(row);
	}
	
	private void putCell(TemplateCell cell){
		keycell.put(cell.getKeyName(), cell);
		cell.getParent().addCell(cell);
	}
	
	/**
	 * 移除不显示数据
	 * @param keyName
	 */
	public void removeCell(String keyName){
		ExcelCell cell =  keycell.get(keyName);
		if(cell!=null){
			ExcelRow parentRow = cell.getParent();
			parentRow.getCells().remove(cell);
			if(parentRow.getCells().isEmpty()){
				rowlist.remove(parentRow);
			}			
		}
	}
	
	/**
	 * 创建字段位置模板
	 * @return
	 */
	public HashMap<String,String> getTempMap(){
		HashMap<String,String> map  =  new HashMap<String,String>();
		for (int i = 0; i < rowlist.size(); i++) {
			ExcelRow parentRow = rowlist.get(i);
			for(int j = 0 ; j < parentRow.getCells().size() ; j++){
				String keyPos = Integer.toString(j+startRowIndex) + "," + Integer.toString(i+startColumnIndex);
				map.put(((TemplateCell)parentRow.getCells().get(j)).getKeyName(), keyPos);
			}
		}
		return map;
	} 
	
	
	
	public class TemplateCell extends ExcelCell {

		private String keyName;
		
		public TemplateCell(int index, ExcelRow parent) {
			super(index, parent);
		}

		public TemplateCell(int index, String keyName , ExcelRow parent){
			super(index, parent);
			this.keyName = keyName;
		}

		@Override
		public void accept(CellVistor vistor) {
			
			
		}
		
		public void setKeyName(String keyName) {
			this.keyName = keyName;
		}
		
		public String getKeyName() {
			return keyName;
		}
	}
}
