package com.coder.hms.actionlistners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class RoomsActionListener {

	private ActionListener customActionListener;
	private String roomText = "";

	public RoomsActionListener() {
	
		customActionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int counter = 100;
				int lastNum = 0;
				String command = e.getActionCommand();

				for (int i = 1; i <= 48; i++) {
					++lastNum;
					
					roomText = counter + "" + lastNum;
					
					if (i % 6 == 0) {
						counter += 100;
						lastNum = 0;
					}

					if (command.equals(roomText)) {
						
						JOptionPane.showMessageDialog(null, roomText);
						break;
					}
				}
				

			}

		};
	}

	public ActionListener getActionListener() {
		return this.customActionListener;
	}
}
