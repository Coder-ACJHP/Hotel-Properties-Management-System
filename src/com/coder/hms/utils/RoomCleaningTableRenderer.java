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
			cellComponent.setBackground(Color.decode("#10d6d1"));
		}
		
		else {
			
			if(column == 0) {
				cellComponent.setBackground(Color.decode("#effbad"));
			}
			
			else if (colrowVal.equalsIgnoreCase("CLEAN")) {
				cellComponent.setBackground(Color.decode("#afe2fb"));
			}

			else if (colrowVal.equalsIgnoreCase("DIRTY")) {
				cellComponent.setBackground(Color.decode("#d24760"));
			}

			else if(colrowVal.equalsIgnoreCase("DND")) {
				cellComponent.setBackground(Color.decode("#ffc300"));
			}
			
			else {
				cellComponent.setBackground(Color.decode("#f4a0c4"));
			}

		}

		return cellComponent;
	}

}
