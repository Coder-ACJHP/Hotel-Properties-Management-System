/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coder.hms.ui.external;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.coder.hms.daoImpl.CompanyDaoImpl;
import com.coder.hms.entities.Company;
import com.coder.hms.ui.extras.CustomTableHeaderRenderer;
import com.coder.hms.ui.extras.CustomersTableRenderer;


public class AllCompaniesWindow extends JFrame {

    /**
	 * 
	 */
	private JLabel searchLbl;
	private int selectedRowIndex;
    private JPanel buttonHolder;
    private Box.Filler spaceStrut;
    private JTable companiesTable;
    private JTextField searchField;
    private String selectedCompanyName;
    private JScrollPane tableScrooler;
    private CompanyDaoImpl companyDaoImpl;
    private InformationFrame informationFrame;
    private JButton deleteBtn, newBtn, editBtn;
	private static final long serialVersionUID = 1L;
    private TableRowSorter<DefaultTableModel> tableRowSorter;
    private final CustomersTableRenderer CTR = new CustomersTableRenderer();
    private final CustomTableHeaderRenderer THR = new CustomTableHeaderRenderer();
    final String [] columnNames = {"TITLE", "KIND", "EMAIL", "ADDRESS", "PHONE", "ACTIVATE STATUS"};
    private final DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";
    
    public AllCompaniesWindow() {
    	companyDaoImpl = new CompanyDaoImpl();
    	informationFrame = new InformationFrame();
        initComponents();
    }

    private void initComponents() {

        buttonHolder = new JPanel();
        newBtn = new JButton();
        editBtn = new JButton();
        deleteBtn = new JButton();
        spaceStrut = new Box.Filler(new Dimension(0, 0), new Dimension(600, 0), new Dimension(600, 0));
        spaceStrut.setPreferredSize(new Dimension(600, 0));
        spaceStrut.setAutoscrolls(true);
        searchLbl = new JLabel();
        searchField = new JTextField();
        tableScrooler = new JScrollPane();
        companiesTable = new JTable();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Coder HPMSA - [All Companies]");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(LOGOPATH)));
        setMinimumSize(new Dimension(1100, 750));

        buttonHolder.setBackground(new Color(6, 109, 149));
        buttonHolder.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
        buttonHolder.setAutoscrolls(true);
        buttonHolder.setPreferredSize(new Dimension(1031, 60));
        buttonHolder.setLayout(new FlowLayout(FlowLayout.LEFT));

        newBtn.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12)); // NOI18N
        newBtn.setIcon(new ImageIcon(getClass().getResource("/com/coder/hms/icons/room_posting.png"))); // NOI18N
        newBtn.setText("New");
        newBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        newBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newBtn.setPreferredSize(new Dimension(130, 40));
        newBtn.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent evt) {
                newBtnActionPerformed(evt);
            }
        });
        buttonHolder.add(newBtn);

        editBtn.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12)); // NOI18N
        editBtn.setIcon(new ImageIcon(getClass().getResource("/com/coder/hms/icons/main_edit.png"))); // NOI18N
        editBtn.setText("Show & Edit");
        editBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        editBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editBtn.setPreferredSize(new Dimension(130, 40));
        editBtn.addActionListener(new ActionListener() {
		
            @Override
			public void actionPerformed(ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        buttonHolder.add(editBtn);

        deleteBtn.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12)); // NOI18N
        deleteBtn.setIcon(new ImageIcon(getClass().getResource("/com/coder/hms/icons/room_checkout.png"))); // NOI18N
        deleteBtn.setText("Delete");
        deleteBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteBtn.setPreferredSize(new Dimension(130, 40));
        deleteBtn.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        buttonHolder.add(deleteBtn);
        buttonHolder.add(spaceStrut);

        searchLbl.setFont(new Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        searchLbl.setForeground(new Color(255, 255, 0));
        searchLbl.setText("Search : ");
        buttonHolder.add(searchLbl);

        searchField.setHorizontalAlignment(JTextField.CENTER);
        searchField.setPreferredSize(new Dimension(130, 25));
        searchField.addKeyListener(new KeyAdapter() {
		
            @Override
			public void keyTyped(KeyEvent evt) {
                searchFieldKeyTyped(evt);
            }
        });
        buttonHolder.add(searchField);

        getContentPane().add(buttonHolder, BorderLayout.PAGE_START);

        companiesTable.setAutoCreateRowSorter(true);
        companiesTable.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 14)); // NOI18N
        companiesTable.setModel(model);
        companiesTable.setRowHeight(25);
        companiesTable.setDragEnabled(true);
        companiesTable.setDefaultRenderer(Object.class, CTR);
        companiesTable.getTableHeader().setDefaultRenderer(THR);
        companiesTable.addMouseListener(customMouseEvent());
        tableScrooler.setViewportView(companiesTable);

        getContentPane().add(tableScrooler, BorderLayout.CENTER);
        populateMainTable();
        pack();
        setVisible(true);
    }

    private void populateMainTable() {
    	model.setRowCount(0);
    	final List<Company> companiesList = companyDaoImpl.getAllCompanies();
    	for (Company company : companiesList) {
			model.addRow(new Object[]{company.getTitle(), company.getKind(),
					company.getEmail(), company.getAddress(), company.getPhoneNumber(), company.isActivateStatus()});
		}
    	
    }
    
    private MouseListener customMouseEvent() {
		final MouseAdapter adapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedRowIndex = companiesTable.getSelectedRow();
				if(selectedRowIndex >= 0) {
					
					selectedCompanyName = companiesTable.getValueAt(selectedRowIndex, 0).toString(); 
				}
				super.mouseClicked(e);
			}
		};
		return adapter;
	}

	private void newBtnActionPerformed(ActionEvent evt) {
    	SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				CompanyDetails companyDetails = new CompanyDetails();
				companyDetails.setVisible(true);
				
				//if detail window save operation finished successfully
				//refresh the main table to add new rows.
				if(companyDetails.checkIsFinished()) {
					populateMainTable();
				}
			}
		});
    }

    private void editBtnActionPerformed(ActionEvent evt) {
    	if(selectedCompanyName.trim().length() >= 0) {
    		
    		final Company theCompany = companyDaoImpl.getCompanyByName(selectedCompanyName);
    		final CompanyDetails detailsWin = new CompanyDetails();
    		detailsWin.setIdFieldText(String.valueOf(theCompany.getId()));
    		detailsWin.setTitleField(theCompany.getTitle());
    		detailsWin.setKindField(theCompany.getKind());
    		detailsWin.setDistrictField(theCompany.getDistrict());
    		detailsWin.setProvinceField(theCompany.getProvince());
    		detailsWin.setTaxAdminField(theCompany.getTaxAdministration());
    		detailsWin.setTaxNumField(theCompany.getTaxNumber());
    		detailsWin.setEmailField(theCompany.getEmail());
    		detailsWin.setAddresField(theCompany.getAddress());
    		detailsWin.setPhoneNumField(theCompany.getPhoneNumber());
    		detailsWin.setFacesColor(new Color(theCompany.getFaceColorRgb()));
    		detailsWin.setActivateSts(theCompany.isActivateStatus());
    		detailsWin.setVisible(true);
    		
    		//refresh main table
    		populateMainTable();
    	}
    	else {
    		informationFrame.setMessage("First you have to select a row!");
    		informationFrame.setVisible(true);
    	}
    }

    private void deleteBtnActionPerformed(ActionEvent evt) {
    	if(selectedCompanyName.trim().length() >= 0) {
    		companyDaoImpl.deleteByName(selectedCompanyName);
    		
    		//refresh main table
    		populateMainTable();
    	}
    	else {
    		informationFrame.setMessage("First you have to select a row!");
    		informationFrame.setVisible(true);
    	}
    }

    private void searchFieldKeyTyped(java.awt.event.KeyEvent evt) {
        String modifiedQuery = "(?i)" + searchField.getText();
    	tableRowSorter.setRowFilter(RowFilter.regexFilter(modifiedQuery));
    }

}
