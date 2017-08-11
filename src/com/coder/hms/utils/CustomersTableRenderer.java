package com.coder.hms.utils;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomersTableRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		final Component cellComponent =  super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		if(isSelected || hasFocus) {
			
			cellComponent.setBackground(Color.decode("#10d6d1"));
		}
		
		else {
			
			cellComponent.setBackground(row % 2 == 0 ? table.getSelectionBackground() : table.getBackground());
		}
		
		return cellComponent;
	}

}
