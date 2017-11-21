package com.coder.hms.ui.inner;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class ColorInfoTable extends JPanel {

	/**
	 * 
	 */
	private JPanel panelInstance = null;
	private static final long serialVersionUID = 1L;
	private JButton lblClean, lblDrty, lblDnd, lblEmpty, lblTitle, lblFull, lblBlocked, lblCheckout, btnPinIt; 

	public ColorInfoTable() {
		
		setAutoscrolls(true);
		setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(70, 130, 180), null, null, null));
		
		final Dimension dimension = new Dimension(187, 215);
		setToolTipText("Which color, what does it mean.");
		setPreferredSize(new Dimension(34, 299));
		setMaximumSize(dimension);
		setMinimumSize(dimension);
		setBackground(Color.decode("#066d95"));
		setLayout(null);
		setOpaque(false);
		
		panelInstance = this;
		
		lblClean = new JButton("CLEAN");
		lblClean.setPreferredSize(new Dimension(184, 30));
		lblClean.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblClean.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		lblClean.setOpaque(true);
		lblClean.setBackground(Color.WHITE);
		lblClean.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblClean.setBounds(4, 41, 184, 30);
		lblClean.setAutoscrolls(true);
		lblClean.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblClean);
		
		lblDrty = new JButton("DIRTY");
		lblDrty.setPreferredSize(new Dimension(184, 30));
		lblDrty.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblDrty.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		lblDrty.setOpaque(true);
		lblDrty.setBackground(Color.decode("#ce1d1d"));
		lblDrty.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrty.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblDrty.setAutoscrolls(true);
		lblDrty.setBounds(4, 72, 184, 30);
		add(lblDrty);
		
		lblEmpty = new JButton("EMPTY");
		lblEmpty.setPreferredSize(new Dimension(184, 30));
		lblEmpty.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblEmpty.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		lblEmpty.setOpaque(true);
		lblEmpty.setBackground(Color.decode("#afe2fb"));
		lblEmpty.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpty.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblEmpty.setAutoscrolls(true);
		lblEmpty.setBounds(4, 134, 184, 30);
		add(lblEmpty);
		
		lblFull = new JButton("FULL");
		lblFull.setPreferredSize(new Dimension(184, 30));
		lblFull.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblFull.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		lblFull.setOpaque(true);
		lblFull.setBackground(Color.decode("#0051ab"));
		lblFull.setHorizontalAlignment(SwingConstants.CENTER);
		lblFull.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblFull.setAutoscrolls(true);
		lblFull.setBounds(4, 164, 184, 30);
		add(lblFull);
		
		lblBlocked = new JButton("BLOCKED");
		lblBlocked.setPreferredSize(new Dimension(184, 30));
		lblBlocked.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblBlocked.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		lblBlocked.setOpaque(true);
		lblBlocked.setBackground(Color.decode("#eca7d0"));
		lblBlocked.setHorizontalAlignment(SwingConstants.CENTER);
		lblBlocked.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblBlocked.setAutoscrolls(true);
		lblBlocked.setBounds(4, 195, 184, 30);
		add(lblBlocked);
		
		lblTitle = new JButton("COLOR INFO TABLE");
		lblTitle.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblTitle.setAutoscrolls(true);
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(0, 191, 255));
		lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(32, 3, 156, 36);
		add(lblTitle);
		
		setSize(dimension);
		
		lblCheckout = new JButton("CHECKOUT");
		lblCheckout.setPreferredSize(new Dimension(184, 30));
		lblCheckout.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblCheckout.setOpaque(true);
		lblCheckout.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckout.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		lblCheckout.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblCheckout.setBackground(new Color(255, 0, 0));
		lblCheckout.setAutoscrolls(true);
		lblCheckout.setBounds(4, 226, 184, 30);
		add(lblCheckout);
		
		lblDnd = new JButton("DND");
		lblDnd.setPreferredSize(new Dimension(184, 30));
		lblDnd.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblDnd.setOpaque(true);
		lblDnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblDnd.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		lblDnd.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblDnd.setBackground(Color.decode("#ffc300"));
		lblDnd.setAutoscrolls(true);
		lblDnd.setBounds(4, 103, 184, 30);
		add(lblDnd);
		
		btnPinIt = new JButton("");
		btnPinIt.setToolTipText("Pin it!");
		btnPinIt.setAutoscrolls(true);
		btnPinIt.setOpaque(true);
		btnPinIt.setBackground(new Color(0, 191, 255));
		btnPinIt.setIcon(new ImageIcon(ColorInfoTable.class.getResource("/com/coder/hms/icons/main_unpin.png")));
		btnPinIt.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnPinIt.setBounds(3, 3, 29, 36);
		btnPinIt.setActionCommand("Pin it");
		btnPinIt.addActionListener(actionEvent());
		add(btnPinIt);
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
	
	private ActionListener actionEvent() {
		
		final ActionListener actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String command = e.getActionCommand();
				if(command.equalsIgnoreCase("Pin it")) {
					btnPinIt.setIcon(new ImageIcon(ColorInfoTable.class.getResource("/com/coder/hms/icons/main_pin.png")));
					setPreferredSize(new Dimension(190, 299));
					btnPinIt.setActionCommand("Un pin");
					panelInstance.revalidate();
					panelInstance.repaint();
				} else {
					btnPinIt.setIcon(new ImageIcon(ColorInfoTable.class.getResource("/com/coder/hms/icons/main_unpin.png")));
					setPreferredSize(new Dimension(34, 299));
					btnPinIt.setActionCommand("Pin it");
					panelInstance.revalidate();
					panelInstance.repaint();
				}
			}
		};
		return actionListener;
	}
}
