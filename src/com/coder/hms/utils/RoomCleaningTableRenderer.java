package com.coder.hms.utils;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RoomCleaningTableRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		final Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
				column);

		String colrowVal = String.valueOf(value);

		if(isSelected || hasFocus) {
			cellComponent.setBackground(table.getBackground());
		}
		
		else {
			
			if (colrowVal.equalsIgnoreCase("CLEAN")) {
				cellComponent.setBackground(Color.decode("#afe2fb"));
			}

			else if (colrowVal.equalsIgnoreCase("DIRTY")) {
				cellComponent.setBackground(Color.decode("#d24760"));
			}

			else if (colrowVal.contains("00")) {
				cellComponent.setBackground(Color.decode("#bfaec9"));
			}
			
			else {
				cellComponent.setBackground(Color.decode("#ffeec9"));
			}

		}

		return cellComponent;
	}

}
