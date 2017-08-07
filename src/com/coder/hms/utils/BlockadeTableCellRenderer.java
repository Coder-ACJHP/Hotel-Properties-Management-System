/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.utils;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class BlockadeTableCellRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		final Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			
		String colrowVal = String.valueOf(value);
		
		if(colrowVal.equals(null) || colrowVal.length() <= 0) {
			
			cellComponent.setForeground(Color.BLACK);
			cellComponent.setBackground(Color.decode("#e3f6fb"));
			
		}else {
			
			if(column == 0) {
				cellComponent.setBackground(Color.decode("#effbad"));
			}
			
			else if(colrowVal.equalsIgnoreCase("FULL") || colrowVal.equalsIgnoreCase("BLOCKED")) {
				cellComponent.setBackground(Color.decode("#f9d692"));
			}
			
			else if(colrowVal.equalsIgnoreCase("EMPTY")) {
				cellComponent.setBackground(Color.decode("#0083fb"));
			}
			
			else if(colrowVal.equalsIgnoreCase("SINGLE") ||colrowVal.equalsIgnoreCase("DOUBLE") || colrowVal.equalsIgnoreCase("TWIN") || colrowVal.equalsIgnoreCase("TRIPLE")) {
				cellComponent.setBackground(Color.decode("#f4a0c4"));
			}
			
			else {
				cellComponent.setBackground(table.getBackground());
			}

		}
		
		if(hasFocus) {
			cellComponent.setBackground(Color.decode("#10d6d1"));
		}

		return cellComponent;
	}

}
