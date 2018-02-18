/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.ui.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Optional;

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

import com.coder.hms.beans.LocaleBean;
import com.coder.hms.daoImpl.CustomerDaoImpl;
import com.coder.hms.daoImpl.ReservationDaoImpl;
import com.coder.hms.entities.Customer;
import com.coder.hms.entities.Reservation;
import com.coder.hms.ui.external.CustomerDetailWindow;
import com.coder.hms.ui.extras.CustomTableHeaderRenderer;
import com.coder.hms.ui.extras.CustomersTableRenderer;
import com.coder.hms.utils.ChangeComponentOrientation;
import com.coder.hms.utils.LoggingEngine;

public class Main_CustomersFrame extends JPanel {

    /**
     *
     */
    private Customer theCustomer;
    private JTable customerTable;
    private final LocaleBean bean;
    private JLabel lblTableFilter;
    private JScrollPane scrollPane;
    private CustomerDaoImpl customerDaoImpl;
    private JTextField searchFilterField;
    private static LoggingEngine loggingEngine;
    private JPanel searchPanel = new JPanel();
    private static final long serialVersionUID = 1L;
    private ChangeComponentOrientation componentOrientation;
    private final String[] colsName = {"ROOM", "REZERVATION ", "NAME ", "LASTNAME",
        "AGENCY", "GROUP", "CHECK/IN DATE", "CHECK/OUT DATE", "COUNTRY"};
    private DefaultTableModel model = new DefaultTableModel(colsName, 0);
    final static CustomerDetailWindow custWindow = new CustomerDetailWindow();
    private final CustomTableHeaderRenderer THR = new CustomTableHeaderRenderer();
    private final CustomersTableRenderer renderer = new CustomersTableRenderer();

    public Main_CustomersFrame() {

        this.setAutoscrolls(true);
        this.setMinimumSize(new Dimension(800, 600));
        /*make it default size of frame maximized */
        this.setMaximumSize(new Dimension(1000, 900));
        this.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        setLayout(new BorderLayout(0, 0));

        bean = LocaleBean.getInstance();
        loggingEngine = LoggingEngine.getInstance();
        componentOrientation = new ChangeComponentOrientation();
        componentOrientation.setThePanel(this);

        searchPanel.setDoubleBuffered(false);
        searchPanel.setAutoscrolls(true);
        add(searchPanel, BorderLayout.NORTH);
        searchPanel.setPreferredSize(new Dimension(10, 30));

        lblTableFilter = new JLabel("Type to search : ");
        lblTableFilter.setForeground(new Color(178, 34, 34));
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
        searchFilterField.setHorizontalAlignment(SwingConstants.LEFT);
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

                final String searchedWord = searchFilterField.getText();
                filter(searchedWord);

            }
        });

        scrollPane = new JScrollPane();
        add(scrollPane);

        //populate main table model with custom method
        populateMainTable(model);

        customerTable = new JTable(model);
        customerTable.setFillsViewportHeight(true);
        customerTable.setRowSelectionAllowed(true);

        THR.setHorizontalAlignment(SwingConstants.CENTER);

        customerTable.setDefaultRenderer(Object.class, renderer);
        customerTable.getTableHeader().setDefaultRenderer(THR);
        customerTable.setFont(new Font("Dialog", Font.PLAIN, 14));
        customerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        customerTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        customerTable.setBackground(new Color(245, 245, 245));
        customerTable.addMouseListener(openCustomerListener());
        scrollPane.setViewportView(customerTable);

        //change component orientation with locale.
        if (bean.getLocale().toString().equals("ar_IQ")) {
            componentOrientation.changeOrientationOfJPanelToRight();
        } else {
            componentOrientation.changeOrientationOfJPanelToLeft();
        }
        //inject action event to Customers detail window to save all changes
        custWindow.setActionListener(saveChanges());
        this.setVisible(true);
    }

    private void filter(String query) {
        String modifiedQuery = "(?i)" + query;
        TableRowSorter<TableModel> tr = new TableRowSorter<TableModel>(model);
        customerTable.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(modifiedQuery));
    }

    private void populateMainTable(DefaultTableModel model) {

        model.setRowCount(0);
        
        customerDaoImpl = new CustomerDaoImpl();
        final List<Customer> customerList = customerDaoImpl.getAllCustomers();
        
        if(!customerList.isEmpty()) {
        	final ReservationDaoImpl reservationDaoImpl = new ReservationDaoImpl();

            for (Customer cust : customerList) {

                final Optional<Reservation> reservation = reservationDaoImpl.findReservationById(cust.getReservationId());

                if (!reservation.isPresent()) {
                    continue;
                }

                final Object[] customerObject = new Object[]{reservation.get().getRentedRoomNum(), reservation.get().getId(),
                    cust.getFirstName(), cust.getLastName(), reservation.get().getAgency(), reservation.get().getGroupName(),
                    reservation.get().getCheckinDate(), reservation.get().getCheckoutDate(), cust.getCountry()};
                model.addRow(customerObject);
            }
        }

    }
    
    private MouseListener openCustomerListener() {
		final MouseAdapter adapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				if (e.getClickCount() == 2) {

					final int rowIndex = customerTable.getSelectedRow();
					final String name = customerTable.getValueAt(rowIndex, 2).toString();
					final String lastname = customerTable.getValueAt(rowIndex, 3).toString();

					theCustomer = customerDaoImpl.findCustomerByName(name, lastname);

					custWindow.setId(theCustomer.getCustomerId() + "");
					custWindow.setName(theCustomer.getFirstName());
					custWindow.setSurname(theCustomer.getLastName());
					custWindow.setDocument(theCustomer.getDocument());
					custWindow.setDocNo(theCustomer.getDocumentNo());
					custWindow.setCountry(theCustomer.getCountry());
					custWindow.setDateOfBirth(theCustomer.getDateOfBirth());
					custWindow.setEmail(theCustomer.getEmail());
					custWindow.setFatherName(theCustomer.getFatherName());
					custWindow.setMotherName(theCustomer.getMotherName());
					custWindow.setGender(theCustomer.getGender());
					custWindow.setPhone(theCustomer.getPhone());
					custWindow.setMariaggeStaus(theCustomer.getMaritalStatus());
					custWindow.setReservationId(theCustomer.getReservationId() + "");
					custWindow.setInfoMessage(" ");
					
					custWindow.setVisible(true);
					
					loggingEngine.setMessage("Displaying customer...");
					loggingEngine.setMessage("Displayed customer details : " + theCustomer.toString());
				}

				super.mousePressed(e);
			}
		};
		return adapter;
	}

	// save all changed properties in customer table
	private ActionListener saveChanges() {
		final ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				theCustomer.setCountry(custWindow.getCountry());
				theCustomer.setFirstName(custWindow.getName());
				theCustomer.setLastName(custWindow.getSurname());
				theCustomer.setDocument(custWindow.getDocument());
				theCustomer.setDocumentNo(custWindow.getDocNo());
				theCustomer.setCountry(custWindow.getCountry());
				theCustomer.setDateOfBirth(custWindow.getDateOfBirth());
				theCustomer.setEmail(custWindow.getEmail());
				theCustomer.setFatherName(custWindow.getFatherName());
				theCustomer.setMotherName(custWindow.getMotherName());
				theCustomer.setGender(custWindow.getGender());
				theCustomer.setPhone(custWindow.getPhone());
				theCustomer.setMaritalStatus(custWindow.getMariageStatus());
				theCustomer.setReservationId(Long.parseLong(custWindow.getReservationId()));

				boolean success = customerDaoImpl.update(theCustomer);

				if (success) {
					
					custWindow.setInfoMessage("<html>SUCCESSFULLY ACCOMPLISHED</html>");
					custWindow.setInfoLabelColor(Color.decode("#00FF00"));
					loggingEngine.setMessage("Customer details updated : " + theCustomer.toString());
					success = false;
				} else {
					custWindow.setInfoMessage("<html>OPERTION IS FAILED!</html>");
					custWindow.setInfoLabelColor(Color.decode("#cd2626"));
				}
				
				theCustomer = null;
			}
		};
                //refresh table to update datails.
                populateMainTable(this.model);
		return listener;
	}

}
