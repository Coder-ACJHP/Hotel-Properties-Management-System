package com.coder.hms.ui.inner;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

public class AllRooms_ColorInfoTable extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblClean, lblDrty, lblDnd, lblEmpty, lblTitle, lblFull, lblBlocked, lblCheckout;

	public AllRooms_ColorInfoTable() {
		
		setAutoscrolls(true);
		setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(70, 130, 180), null, null, null));
		
		final Dimension dimension = new Dimension(187, 215);
		setToolTipText("Which color, what does it mean.");
		setPreferredSize(new Dimension(193, 299));
		setMaximumSize(dimension);
		setMinimumSize(dimension);
		setBackground(Color.decode("#066d95"));
		setLayout(null);
		setOpaque(false);
		
		lblClean = new JLabel("CLEAN");
		lblClean.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblClean.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblClean.setOpaque(true);
		lblClean.setBackground(Color.WHITE);
		lblClean.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblClean.setBounds(4, 41, 184, 30);
		lblClean.setAutoscrolls(true);
		lblClean.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblClean);
		
		lblDrty = new JLabel("DIRTY");
		lblDrty.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblDrty.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblDrty.setOpaque(true);
		lblDrty.setBackground(Color.decode("#ce1d1d"));
		lblDrty.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrty.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblDrty.setAutoscrolls(true);
		lblDrty.setBounds(4, 73, 184, 30);
		add(lblDrty);
		
		lblEmpty = new JLabel("EMPTY");
		lblEmpty.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblEmpty.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblEmpty.setOpaque(true);
		lblEmpty.setBackground(Color.decode("#afe2fb"));
		lblEmpty.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpty.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblEmpty.setAutoscrolls(true);
		lblEmpty.setBounds(4, 139, 184, 30);
		add(lblEmpty);
		
		lblFull = new JLabel("FULL");
		lblFull.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblFull.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblFull.setOpaque(true);
		lblFull.setBackground(Color.decode("#0051ab"));
		lblFull.setHorizontalAlignment(SwingConstants.CENTER);
		lblFull.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblFull.setAutoscrolls(true);
		lblFull.setBounds(4, 172, 184, 30);
		add(lblFull);
		
		lblBlocked = new JLabel("BLOCKED");
		lblBlocked.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblBlocked.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblBlocked.setOpaque(true);
		lblBlocked.setBackground(Color.decode("#eca7d0"));
		lblBlocked.setHorizontalAlignment(SwingConstants.CENTER);
		lblBlocked.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblBlocked.setAutoscrolls(true);
		lblBlocked.setBounds(4, 205, 184, 30);
		add(lblBlocked);
		
		lblTitle = new JLabel("COLOR INFO TABLE");
		lblTitle.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblTitle.setAutoscrolls(true);
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(192, 192, 192), null, null, null));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(0, 191, 255));
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 15));
		lblTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 1, 193, 36);
		add(lblTitle);
		
		setSize(dimension);
		
		lblCheckout = new JLabel("CHECKOUT");
		lblCheckout.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblCheckout.setOpaque(true);
		lblCheckout.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckout.setFont(new Font("Dialog", Font.BOLD, 13));
		lblCheckout.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblCheckout.setBackground(new Color(255, 0, 0));
		lblCheckout.setAutoscrolls(true);
		lblCheckout.setBounds(5, 238, 184, 30);
		add(lblCheckout);
		
		lblDnd = new JLabel("DND");
		lblDnd.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblDnd.setOpaque(true);
		lblDnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblDnd.setFont(new Font("Dialog", Font.BOLD, 13));
		lblDnd.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblDnd.setBackground(Color.decode("#ffc300"));
		lblDnd.setAutoscrolls(true);
		lblDnd.setBounds(4, 106, 184, 30);
		add(lblDnd);
	}
	
	public void setCleanLabelCount(int count) {
		lblClean.setText("CLEAN : " + count);
	}
	
	public void setDirtyLabelCount(int count) {
		lblDrty.setText("DIRTY : " + count);
	}
	
	public void setDndLabelCount(int count) {
		lblDnd.setText("DND : " + count);
	}
}
