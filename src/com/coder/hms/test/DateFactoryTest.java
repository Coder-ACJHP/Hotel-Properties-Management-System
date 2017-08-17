package com.coder.hms.test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class DateFactoryTest {

	private LocalDate currentDate;
	private LocalDateTime localDateTime;

	public static void main(String[] args) throws InterruptedException {

		DateFactoryTest dateFactoryTest = new DateFactoryTest();
		dateFactoryTest.setTheDate(false);
		while(true) {
			Thread.sleep(1000);
			System.out.println(dateFactoryTest.getDate());
		}

	}

	public DateFactoryTest() {
		currentDate = LocalDate.now();
		localDateTime = LocalDateTime.now();
	}

	private void setTheDate(boolean isAuditted) {

		if (localDateTime.getHour() <= 6 && isAuditted == false) {
			
			currentDate.minusDays(1);
			
		} else if (localDateTime.getHour() > 6 && isAuditted == true) {

			isAuditted = false;

		}

		else if (isAuditted) {

			ImageIcon icon = new ImageIcon(getClass().getResource("/com/coder/hms/icons/dialogPane_question.png"));

			int choosedVal = JOptionPane.showOptionDialog(null, "You're doing early audit, are you sure about this?",
					"Approving question", 0, JOptionPane.YES_NO_OPTION, icon, null, null);

			if (choosedVal == JOptionPane.YES_OPTION) {
				isAuditted = false;
			}
		}
		
	}

	private LocalDate getDate() {
		return currentDate;
	}

}
