package com.coder.hms.utils;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class PayPostTableCellRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		final Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		String rowVal = (String) table.getValueAt(row, 1);
		
		if(isSelected || hasFocus) {
			
			cellComponent.setBackground(table.getSelectionBackground());
		}
		
		else {
		
			if(rowVal.equals("CASH PAYMENT")) {
					
				cellComponent.setBackground(Color.decode("#75dbcf"));
					
			}
			else if(rowVal.equals("SYSTEM")) {
				
				cellComponent.setBackground(Color.decode("#d66c7f"));
			}
			else {
				
				cellComponent.setBackground(table.getBackground());
			}
		}
		return cellComponent;
	}

}
