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

public class ReservationTableRenderer extends DefaultTableCellRenderer {
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			
			final Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);						
	
			Object colrowVal = table.getModel().getValueAt(row, column);
			
			if(isSelected || hasFocus) {
				cellComponent.setBackground(Color.decode("#10d6d1"));
			}
			
			else if(column == 0) {
				cellComponent.setBackground(Color.decode("#effbad"));
			}
			
			else if(column == 2) {
				String trimmed = colrowVal.toString().substring(0, colrowVal.toString().length()-1);
				float val = Float.parseFloat(trimmed);
				if(val > 75f) {
					cellComponent.setBackground(Color.decode("#ffcdd5"));
				}
				
			}
			
			else if(column == 3) {
				String trimmed = colrowVal.toString().substring(0, colrowVal.toString().length()-1);
				float val = Float.parseFloat(trimmed);
				if(val > 25f) {
					cellComponent.setBackground(Color.decode("#baffc6"));
				}
				
			}
			
			else if(column == 5) {
				if(Integer.parseInt(colrowVal.toString()) > 0) {
					cellComponent.setBackground(Color.decode("#dc143c"));
				}
				
			}
			
			else {
				cellComponent.setBackground(table.getBackground());
			}

			return cellComponent;
		}

}
