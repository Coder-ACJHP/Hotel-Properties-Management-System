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
		
		
		Object type = table.getModel().getValueAt(row, 1);
		
		if(isSelected || hasFocus) {
			cellComponent.setBackground(table.getBackground());
		}
		
		else {
			if(type.toString().equalsIgnoreCase("CASH PAYMENT") || type.toString().equalsIgnoreCase("CREDIT CARD")) {
				
				cellComponent.setBackground(Color.decode("#1ba999"));
				
			}
			
			else if(type.toString().equalsIgnoreCase("CITY LEDGER")) {
				
				cellComponent.setBackground(Color.decode("#fd7a16"));
			}
			
			else {
				
				cellComponent.setBackground(Color.decode("#ff2d2d"));
			}
		}
		return cellComponent;
	}

}
