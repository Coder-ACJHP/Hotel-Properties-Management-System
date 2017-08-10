package com.coder.hms.usrinterface;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import com.coder.hms.utils.BlockadeTableHeaderRenderer;

import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Component;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.Box;

public class Main_Audit extends JPanel {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private final String[] columnNames = {"RESERVATION NO", "GROUP NAME", "ROOM NUMBER", "CHECK/IN DATE", "PRICE", "AGENCY"};
	private final DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	private final BlockadeTableHeaderRenderer THR = new BlockadeTableHeaderRenderer();
	private JTable table;

	public Main_Audit() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel upperPanel = new JPanel();
		upperPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		upperPanel.setPreferredSize(new Dimension(10, 48));
		upperPanel.setBackground(Color.decode("#066d95"));
		upperPanel.setAutoscrolls(true);
		add(upperPanel, BorderLayout.NORTH);
		upperPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		buttonPanel.setBackground(new Color(95, 158, 160));
		buttonPanel.setAutoscrolls(true);
		buttonPanel.setPreferredSize(new Dimension(140, 48));
		add(buttonPanel, BorderLayout.WEST);
		buttonPanel.setLayout(null);
		
		JButton btnUpdate = new JButton("Update res.");
		btnUpdate.setAutoscrolls(true);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnUpdate.setIcon(new ImageIcon(Main_Audit.class.getResource("/com/coder/hms/icons/cleaning-refresh.png")));
		btnUpdate.setBounds(6, 81, 127, 47);
		buttonPanel.add(btnUpdate);
		
		JButton btnCancel = new JButton("Cancel res.");
		btnCancel.setAutoscrolls(true);
		btnCancel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setIcon(new ImageIcon(Main_Audit.class.getResource("/com/coder/hms/icons/room_checkout.png")));
		btnCancel.setBounds(6, 131, 127, 47);
		buttonPanel.add(btnCancel);
		
		JButton btnAudit = new JButton("Audit");
		btnAudit.setAutoscrolls(true);
		btnAudit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAudit.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnAudit.setBackground(UIManager.getColor("Button.select"));
		btnAudit.setIcon(new ImageIcon(Main_Audit.class.getResource("/com/coder/hms/icons/main_audit.png")));
		btnAudit.setBounds(6, 12, 127, 47);
		buttonPanel.add(btnAudit);
		
		final JSeparator sep = new JSeparator();
		sep.setPreferredSize(new Dimension(0, 13));
		sep.setForeground(UIManager.getColor("CheckBoxMenuItem.disabledForeground"));
		sep.setAutoscrolls(true);
		sep.setAlignmentX(Component.CENTER_ALIGNMENT);
		sep.setBounds(6, 62, 127, 14);
		buttonPanel.add(sep);
		
		JLabel lblSystemDalyAudt = new JLabel("SYSTEM DAILY AUDIT");
		lblSystemDalyAudt.setForeground(UIManager.getColor("Button.highlight"));
		lblSystemDalyAudt.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblSystemDalyAudt.setAutoscrolls(true);
		lblSystemDalyAudt.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 25));
		lblSystemDalyAudt.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSystemDalyAudt.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(lblSystemDalyAudt, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(model);
		table.setRowHeight(20);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.getTableHeader().setDefaultRenderer(THR);
		scrollPane.setViewportView(table);
		
		
	}
}
