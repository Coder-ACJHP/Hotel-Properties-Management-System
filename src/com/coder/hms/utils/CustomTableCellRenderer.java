package com.coder.hms.utils;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		final Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
		if(isSelected) {
			cellComponent.setBackground(Color.decode("#a0769a"));
		}else if(hasFocus) {
			cellComponent.setForeground(Color.WHITE);
			cellComponent.setBackground(Color.decode("#7f4657"));
		}else if(value != null) {
			cellComponent.setForeground(Color.WHITE);
			cellComponent.setBackground(Color.decode("#00a0cc"));
		}
		
		
		return cellComponent;
	}

}
