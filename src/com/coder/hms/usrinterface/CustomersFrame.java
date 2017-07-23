/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.usrinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.coder.hms.utils.CustomReservationrenderer;
import com.coder.hms.utils.CustomTableHeaderRenderer;


public class CustomersFrame extends JPanel {
	
	/**
	 * 
	 */
	private JTable customerTable;
	private JLabel lblTableFilter;
	private JScrollPane scrollPane;
	private JTextField searchFilterField;
	private JPanel searchPanel = new JPanel();
	private static final long serialVersionUID = 1L;
	private final String[] colsName = {"ROOM", "REZERVATION ", "NAME ", "LASTNAME", 
					"AGENCY", "GROUP", "CHECK/IN DATE", "CHECK/OUT DATE", "COUNTRY"};
	private DefaultTableModel model = new DefaultTableModel(colsName, 100);
	private final CustomTableHeaderRenderer THR = new CustomTableHeaderRenderer();
	private final CustomReservationrenderer renderer = new CustomReservationrenderer();

	public CustomersFrame() {
		
		this.setAutoscrolls(true);
		this.setMinimumSize(new Dimension(800, 600));
		/*make it default size of frame maximized */
		this.setMaximumSize(new Dimension(1000, 900));
		this.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		setLayout(new BorderLayout(0, 0));
		searchPanel.setDoubleBuffered(false);
		searchPanel.setAutoscrolls(true);
		add(searchPanel, BorderLayout.NORTH);
		searchPanel.setPreferredSize(new Dimension(10, 30));
		
		lblTableFilter = new JLabel("Type to search : ");
		lblTableFilter.setSize(new Dimension(130, 25));
		lblTableFilter.setPreferredSize(new Dimension(130, 22));
		lblTableFilter.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTableFilter.setAutoscrolls(true);
		lblTableFilter.setHorizontalAlignment(SwingConstants.LEFT);
		lblTableFilter.setFont(new Font("Dialog", Font.BOLD, 15));
		
		searchFilterField = new JTextField();
		searchFilterField.setDragEnabled(true);
		searchFilterField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), null, null, null));
		searchFilterField.setPreferredSize(new Dimension(200, 22));
		searchFilterField.setIgnoreRepaint(true);
		searchFilterField.setColumns(10);
		searchFilterField.setFont(new Font("Dialog", Font.BOLD, 13));
		searchFilterField.setHorizontalAlignment(JTextField.LEFT);
		GroupLayout gl_searchPanel = new GroupLayout(searchPanel);
		gl_searchPanel.setHorizontalGroup(
			gl_searchPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_searchPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTableFilter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(searchFilterField, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
					.addGap(118))
		);
		gl_searchPanel.setVerticalGroup(
			gl_searchPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_searchPanel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_searchPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTableFilter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchFilterField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		searchPanel.setLayout(gl_searchPanel);
		
		searchFilterField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String searchedWord = searchFilterField.getText();
				filter(searchedWord);
				super.keyTyped(e);
			}
		});
		
		scrollPane = new JScrollPane();
		add(scrollPane);
		
		customerTable = new JTable(model);
		customerTable.setFillsViewportHeight(true);
		customerTable.setCellSelectionEnabled(true);
		
		THR.setHorizontalAlignment(SwingConstants.CENTER);
		
		customerTable.setDefaultRenderer(String.class, renderer);
		customerTable.getTableHeader().setDefaultRenderer(THR);
		customerTable.setFont(new Font("Dialog", Font.PLAIN, 14));
		customerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		customerTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		customerTable.setBackground(new Color(245, 245, 245));
		scrollPane.setViewportView(customerTable);
		
		this.setVisible(true);
	}

	private void filter(String query) {
		TableRowSorter<TableModel> tr = new TableRowSorter<TableModel>(model);
		customerTable.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(query));
	}
	
}
