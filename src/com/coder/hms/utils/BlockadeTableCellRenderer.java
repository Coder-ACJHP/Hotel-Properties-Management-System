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
				cellComponent.setBackground(table.getBackground());
			}
			
			else if(colrowVal.equalsIgnoreCase("FULL") || colrowVal.equalsIgnoreCase("BLOCKED")) {
				cellComponent.setBackground(Color.decode("#f9d692"));
			}
			
			else if(colrowVal.equalsIgnoreCase("EMPTY")) {
				cellComponent.setBackground(Color.decode("#ddf6f3"));
			}
			
			else if(colrowVal.equalsIgnoreCase("SINGLE") ||colrowVal.equalsIgnoreCase("DOUBLE") || colrowVal.equalsIgnoreCase("TWIN") || colrowVal.equalsIgnoreCase("TRIPLE")) {
				cellComponent.setBackground(Color.decode("#f5f6dd"));
			}
			
			else if(column == 3 && !colrowVal.isEmpty()) {
				cellComponent.setBackground(Color.decode("#f7cbfe"));
			}
			
			else if(column == 4 && !colrowVal.isEmpty()) {
				cellComponent.setBackground(Color.decode("#f9aeff"));
			}
			
			else if(column == 5 && !colrowVal.isEmpty()) {
				cellComponent.setBackground(Color.decode("#e096f7"));
			}
			
			else if(column == 6 && !colrowVal.isEmpty()) {
				cellComponent.setBackground(Color.decode("#d28efb"));
			}
			
			else if(column == 7 && !colrowVal.equals(" ")) {
				cellComponent.setBackground(Color.decode("#b683f9"));
			}
			
			else if(column == 8 && !colrowVal.isEmpty()) {
				cellComponent.setBackground(Color.decode("#a465f6"));
			}
			
			else if(column == 9 && !colrowVal.isEmpty()) {
				cellComponent.setBackground(Color.decode("#944af4"));
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
