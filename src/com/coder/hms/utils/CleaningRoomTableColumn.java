package com.coder.hms.utils;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class CleaningRoomTableColumn {

	private TableColumn column;
	
	public CleaningRoomTableColumn() {

	}
	
	public void setHeadersWidth(JTable theTable) {
		
		int index = theTable.getColumnCount();
		
		for(int i=0; i < index; i++) {
			column = theTable.getColumnModel().getColumn(i);
			
			if (i == 0 || i == 1) {
		        column.setPreferredWidth(100); 
		    } else {
		        column.setPreferredWidth(450);//third column is bigger
		    }
		}
	}
}
