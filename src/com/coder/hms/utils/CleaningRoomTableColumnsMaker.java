package com.coder.hms.utils;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class CleaningRoomTableColumnsMaker {

	private TableColumn column;
	
	public CleaningRoomTableColumnsMaker() {

	}
	
	public void setHeadersWidth(JTable theTable) {
		
		int index = theTable.getColumnCount();
		
		for(int i=0; i < index; i++) {
			column = theTable.getColumnModel().getColumn(i);
			if (i == 0 || i == 1) {
		        column.setPreferredWidth(50); 
		    } else {
		        column.setPreferredWidth(450);//third column is bigger
		    }
		}
	}
}
