package com.coder.hms.utils;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class LogRecordsListRenderer extends DefaultListCellRenderer {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(@SuppressWarnings("rawtypes") JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		
		if(isSelected || cellHasFocus) {
			
			c.setBackground(Color.decode("#10d6d1"));
		}
		
		else {
			
			c.setBackground(index % 2 == 0 ? list.getSelectionBackground() : list.getBackground());
		}
		return c;
	}
}
