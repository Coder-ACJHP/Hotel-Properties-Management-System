package com.coder.hms.usrinterface;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class ColorInfoTable extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ColorInfoTable() {
		setOpaque(false);
		
		final Dimension dimension = new Dimension(187, 215);
		setToolTipText("Which color, what does it mean.");
		setPreferredSize(new Dimension(187, 197));
		setMaximumSize(dimension);
		setMinimumSize(dimension);
		setBackground(SystemColor.window);
		setLayout(null);
		
		JLabel lblClean = new JLabel("CLEAN");
		lblClean.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblClean.setOpaque(true);
		lblClean.setBackground(new Color(255, 255, 255));
		lblClean.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblClean.setBounds(1, 37, 184, 30);
		lblClean.setAutoscrolls(true);
		lblClean.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblClean);
		
		JLabel lblDrty = new JLabel("DIRTY");
		lblDrty.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblDrty.setOpaque(true);
		lblDrty.setBackground(Color.decode("#ce1d1d"));
		lblDrty.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrty.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblDrty.setAutoscrolls(true);
		lblDrty.setBounds(1, 68, 184, 30);
		add(lblDrty);
		
		JLabel lblEmpty = new JLabel("EMPTY");
		lblEmpty.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblEmpty.setOpaque(true);
		lblEmpty.setBackground(Color.decode("#afe2fb"));
		lblEmpty.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpty.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblEmpty.setAutoscrolls(true);
		lblEmpty.setBounds(1, 99, 184, 30);
		add(lblEmpty);
		
		JLabel lblFull = new JLabel("FULL");
		lblFull.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblFull.setOpaque(true);
		lblFull.setBackground(Color.decode("#fffcbe"));
		lblFull.setHorizontalAlignment(SwingConstants.CENTER);
		lblFull.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblFull.setAutoscrolls(true);
		lblFull.setBounds(1, 130, 184, 30);
		add(lblFull);
		
		JLabel lblBlocked = new JLabel("BLOCKED");
		lblBlocked.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblBlocked.setOpaque(true);
		lblBlocked.setBackground(Color.decode("#e13580"));
		lblBlocked.setHorizontalAlignment(SwingConstants.CENTER);
		lblBlocked.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblBlocked.setAutoscrolls(true);
		lblBlocked.setBounds(1, 161, 184, 30);
		add(lblBlocked);
		
		JLabel lblNewLabel = new JLabel("COLOR INFO TABLE");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(192, 192, 192), null, null, null));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(0, 139, 139));
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 2, 186, 30);
		add(lblNewLabel);
		
		setSize(dimension);
	}
}
