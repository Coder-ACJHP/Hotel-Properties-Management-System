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

public class CustomReservationrenderer extends DefaultTableCellRenderer {
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			
			final Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);						
			
			if(isSelected || hasFocus) {
				cellComponent.setBackground(Color.decode("#10d6d1"));
			}
			
			else {
				cellComponent.setBackground(table.getBackground());
			}

			return cellComponent;
		}

}
