/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.ui.main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class Main_BottomToolbar {

	private JToolBar toolBar;
	private JLabel userIconLabel, userLabel, dateIconLabel, dateLabel,currencyUsdIcon, currencyUsdLabel,
	currencyEuroIcon, currencyEuroLabel, currencyPoundIcon, currencyPoundLabel, hotelIconLabel, hotelNameLabel;
	
	public Main_BottomToolbar() {
		
		toolBar = new JToolBar();
		toolBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		toolBar.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		toolBar.setAutoscrolls(true);
		toolBar.setPreferredSize(new Dimension(13, 25));
		toolBar.setMinimumSize(new Dimension(0, 25));
		
		userIconLabel = new JLabel(new ImageIcon(getClass().getResource("/com/coder/hms/icons/main_user.png")));
		toolBar.add(userIconLabel);
		
		userLabel = new JLabel();
		userLabel.setMaximumSize(new Dimension(135, 19));
		userLabel.setFont(new Font("Arial", Font.BOLD, 13));
		userLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		userLabel.setHorizontalAlignment(SwingConstants.LEFT);
		toolBar.add(userLabel);
		toolBar.addSeparator();
		
		dateIconLabel = new JLabel(new ImageIcon(getClass().getResource("/com/coder/hms/icons/main_calendar.png")));
		toolBar.add(dateIconLabel);
		
		dateLabel = new JLabel("");
		dateLabel.setMaximumSize(new Dimension(140, 19));
		dateLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		dateLabel.setHorizontalAlignment(SwingConstants.LEFT);
		dateLabel.setFont(new Font("Arial", Font.BOLD, 13));
		toolBar.add(dateLabel);
		toolBar.addSeparator();
		
		currencyUsdIcon = new JLabel(new ImageIcon(getClass().getResource("/com/coder/hms/icons/main_currency.png")));
		toolBar.add(currencyUsdIcon);
		
		currencyUsdLabel = new JLabel("");
		currencyUsdLabel.setMaximumSize(new Dimension(140, 19));
		currencyUsdLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		currencyUsdLabel.setHorizontalAlignment(SwingConstants.LEFT);
		currencyUsdLabel.setFont(new Font("Arial", Font.BOLD, 13));
		toolBar.add(currencyUsdLabel);
		toolBar.addSeparator();		
		
		currencyEuroIcon = new JLabel(new ImageIcon(getClass().getResource("/com/coder/hms/icons/main_currency_euro.png")));
		toolBar.add(currencyEuroIcon);
		
		currencyEuroLabel = new JLabel("");
		currencyEuroLabel.setMaximumSize(new Dimension(140, 19));
		currencyEuroLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		currencyEuroLabel.setHorizontalAlignment(SwingConstants.LEFT);
		currencyEuroLabel.setFont(new Font("Arial", Font.BOLD, 13));
		toolBar.add(currencyEuroLabel);
		toolBar.addSeparator();	
		
		currencyPoundIcon = new JLabel(new ImageIcon(getClass().getResource("/com/coder/hms/icons/main_currency_pound.png")));
		toolBar.add(currencyPoundIcon);
		
		currencyPoundLabel = new JLabel("");
		currencyPoundLabel.setMaximumSize(new Dimension(140, 19));
		currencyPoundLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		currencyPoundLabel.setHorizontalAlignment(SwingConstants.LEFT);
		currencyPoundLabel.setFont(new Font("Arial", Font.BOLD, 13));
		toolBar.add(currencyPoundLabel);
		toolBar.addSeparator();
		
		hotelIconLabel = new JLabel(new ImageIcon(getClass().getResource("/com/coder/hms/icons/login_hotel.png")));
		toolBar.add(hotelIconLabel);
		
		hotelNameLabel = new JLabel("");
		hotelNameLabel.setMaximumSize(new Dimension(160, 19));
		hotelNameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		hotelNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		hotelNameLabel.setFont(new Font("Arial", Font.BOLD, 13));
		toolBar.add(hotelNameLabel);
		toolBar.addSeparator();
		
	}
	
	protected JToolBar getToolBar() {
		return toolBar;
	}
	
	protected void setUserLabelText(String yourText) {
		
		userLabel.setText(" " + yourText);
	}
	
	protected void setDateLabelText(String yourText) {
		
		if(yourText.length() <= 0 || yourText.equals("")) {
			final Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY EEEE");
			final String newDate = sdf.format(today);

			dateLabel.setText(" " + newDate);
		}
		else {
			dateLabel.setText(yourText);
		}
	}
	
	protected void sethotelNameLabelText(String hoteName) {
		hotelNameLabel.setText(" " + hoteName);
	}
	
	protected void setUsdLabelText(String rate) {
		this.currencyUsdLabel.setText(rate);
	}
	
	protected void setEuroLabelText(String rate) {
		this.currencyEuroLabel.setText(rate);
	}
	
	protected void setPoundLabelText(String rate) {
		this.currencyPoundLabel.setText(rate);
	}
	
}
