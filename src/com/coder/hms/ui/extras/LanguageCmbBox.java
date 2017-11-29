package com.coder.hms.ui.extras;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

public class LanguageCmbBox extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ImageIcon[] iconArray;
	private ComboBoxRenderer renderer;
	public JComboBox<Object> comboBox;
	private String[] langArray = {"ENG", "AR", "ES", "TR"};

	public LanguageCmbBox() {
		super(new BorderLayout());
		
		iconArray = new ImageIcon[langArray.length];
		Integer[] intArray = new Integer[langArray.length];
		
		for (int i = 0; i < langArray.length; i++) {
			intArray[i] = new Integer(i);
			iconArray[i] = createImageIcon("/com/coder/hms/icons/" + langArray[i] + ".png");
			
			if(iconArray[i] != null) {
				iconArray[i].setDescription(langArray[i]);
			}
		}
		
		comboBox = new JComboBox<>(intArray);
		comboBox.setActionCommand("languagesCmbBox");
		comboBox.setSelectedIndex(3);
		comboBox.setMaximumRowCount(4);
		
		renderer = new ComboBoxRenderer();
		renderer.setPreferredSize(new Dimension(40, 20));
		comboBox.setRenderer(renderer);
		
		this.add(comboBox, BorderLayout.PAGE_START);
		
	}
	
	public void addActionToLanguageBox(ActionListener listener) {
		this.comboBox.addActionListener(listener);
	}
	
	private static ImageIcon createImageIcon(String imgPath) {
		URL imgUrl = LanguageCmbBox.class.getResource(imgPath);
		
		if(imgUrl != null) {
			return new ImageIcon(imgUrl);
		}
		else {
			return null;
		}
	}
	
	private class ComboBoxRenderer extends JLabel implements ListCellRenderer<Object>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ComboBoxRenderer() {
			
			setOpaque(true);
			setHorizontalAlignment(SwingConstants.CENTER);
		}
		
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			

			int selectedIndex = ((Integer) value).intValue();

			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}

			ImageIcon icon = iconArray[selectedIndex];
			setIcon(icon);
			
			return this;
		}

	}
}
