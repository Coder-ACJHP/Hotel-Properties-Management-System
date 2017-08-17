package com.coder.hms.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class CustomDateFactory {

	private static LocalDate currentDate;
	private static LocalDateTime localDateTime;
	 private static CustomDateFactory instance = null;
	private static final ImageIcon icon = new ImageIcon(CustomDateFactory.
			class.getResource("/com/coder/hms/icons/dialogPane_question.png"));


	protected CustomDateFactory() {
		
		localDateTime = LocalDateTime.now();
	}

	public static CustomDateFactory getInstance() {
	      if(instance == null) {
	         instance = new CustomDateFactory();
	      }
	      return instance;
	}
	
	public void setValidDateUntilAudit(boolean isAuditted) {

		if (localDateTime.getHour() <= 6 && isAuditted == false) {
			currentDate = LocalDate.now();
			currentDate = currentDate.minusDays(1);

		}
		
		else if(localDateTime.getHour() < 6 && isAuditted == true) {
			
			int choosedVal = JOptionPane.showOptionDialog(null, "You're doing early audit, are you sure about this?",
					"Approving question", 0, JOptionPane.YES_NO_OPTION, icon, null, null);

			if (choosedVal == JOptionPane.YES_OPTION) {
				currentDate = LocalDate.now();
				isAuditted = false;
			}
			else {
				currentDate = LocalDate.now();
				currentDate = currentDate.minusDays(1);
			}
			
		} 
		
		else if (localDateTime.getHour() > 6 && isAuditted == true) {
			
			int choosedVal = JOptionPane.showOptionDialog(null, "Are you sure about this?",
					"Approving question", 0, JOptionPane.YES_NO_OPTION, icon, null, null);

			if (choosedVal == JOptionPane.YES_OPTION) {
				currentDate = LocalDate.now();
				isAuditted = false;
			}
			else {
				currentDate = LocalDate.now();
				currentDate = currentDate.minusDays(1);
				isAuditted = false;
			}

		}
		
	}

	public LocalDate getDate() {
		return currentDate;
	}

}
