package com.coder.hms.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

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
		
		Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		cellComponent.setFont(new Font("Arial", Font.BOLD, 12));

		if(!table.getValueAt(row, column).toString().equals(" ")) {
			cellComponent.setBackground(Color.decode("#a0769a"));
		}else if(cellComponent.isCursorSet()) {
			cellComponent.setBackground(Color.decode("#7f4657"));
		}
		
		
		return cellComponent;
	}

}
