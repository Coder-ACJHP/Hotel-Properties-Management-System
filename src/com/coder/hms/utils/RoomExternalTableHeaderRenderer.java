package com.coder.hms.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RoomExternalTableHeaderRenderer extends JLabel implements TableCellRenderer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoomExternalTableHeaderRenderer() {
		
		setFont(new Font("Verdana", Font.BOLD, 15));
        setForeground(Color.WHITE);
        setOpaque(true);
        setBackground(Color.decode("#533963"));
        setBorder(BorderFactory.createEtchedBorder());
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setText(value.toString());
		return this;
	}
	

}
