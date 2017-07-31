/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.usrinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.DefaultEditorKit;

import com.coder.hms.actionlisteners.RoomsAction;
import com.coder.hms.daoImpl.CustomerDaoImpl;
import com.coder.hms.daoImpl.HotelDaoImpl;
import com.coder.hms.daoImpl.ReservationDaoImpl;
import com.coder.hms.daoImpl.RoomDaoImpl;
import com.coder.hms.entities.Customer;
import com.coder.hms.entities.Reservation;
import com.coder.hms.entities.Room;

public class Main_AllRooms {


	private String num;
	protected JButton roomBtn;
	private List<Room> roomList;
	private JPanel contentPanel = new JPanel();
	private final RoomsAction theAction = new RoomsAction();
	private final RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
//	private final CustomerDaoImpl cImpl = new CustomerDaoImpl();
	private final HotelDaoImpl hotelDaoImpl = new HotelDaoImpl();
	private final ReservationDaoImpl rImpl = new ReservationDaoImpl();

	/**
	 * Create the dialog.
	 */
	public JPanel getWindow() {
		return this.contentPanel;
	}

	public void setWindow(JPanel thePanel) {
		this.contentPanel = thePanel;
	}
	
	public Main_AllRooms() {

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Color.decode("#066d95"));
		contentPanel.setPreferredSize(new Dimension(700, 1000));
		contentPanel.setLayout(new FlowLayout());

		cookRooms(contentPanel);
		
		contentPanel.setVisible(true);
	}

	private void cookRooms(final JPanel panel) {
		
		/////////////////////////////////////////////////////////////////////////////////////
		// In this method we will create room buttons and will set all the room properties,//
		// but we need to call this method in any action about the rooms because we need   //
		// to repaint these buttons when data changed in database. thats why in first step //
		// make all variables new initialized and make the panel empty.When we call it     //
		// over and over every components will work as desire. (Will create from scratch)  //
		/////////////////////////////////////////////////////////////////////////////////////
		
		int lastNum = 0;
		int counter = 100;
		
		panel.removeAll();
		
		roomList = roomDaoImpl.getAllRooms();

		MatteBorder dirtyBorder = BorderFactory.createMatteBorder(8, 0, 0, 0, Color.decode("#ce1d1d"));
		MatteBorder cleanBorder = BorderFactory.createMatteBorder(8, 0, 0, 0, Color.WHITE);

		final int roomCount =  hotelDaoImpl.getHotel().getRoomCapacity();
		
		for (int i = 1; i <= roomCount; i++) {
			++lastNum;
			roomBtn = new JButton();

			roomBtn.setFont(new Font("Arial", Font.BOLD, 12));
			roomBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			roomBtn.setText(counter + "" + lastNum);
			roomBtn.setBorderPainted(true);

			for (Room room : roomList) {

				if (room.getNumber().equals(counter + "" + lastNum)) {
					roomBtn.setToolTipText(room.getType() +"\n"+ room.getUsageStatus());
					roomBtn.addMouseListener(rightClickListener());

					switch (room.getCleaningStatus()) {
					case "CLEAN":
						roomBtn.setBorder(cleanBorder);
						break;
					case "DIRTY":
						roomBtn.setBorder(dirtyBorder);
					default:
						break;
					}

					final String innerDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
					
					final String ROOM_STATUS = room.getUsageStatus();
					
					final Reservation reservation = rImpl.getReservationById(room.getReservationId());
					
					if(ROOM_STATUS.equals("FULL")) {
						roomBtn.setBackground(Color.decode("#fffcbe"));
					}
					else if(ROOM_STATUS.equals("BLOCKED") && reservation.getCheckinDate().equals(innerDate)) {
						roomBtn.setBackground(Color.decode("#e13580"));
					}
					else {
						roomBtn.setBackground(Color.decode("#afe2fb"));
					}
					
				}
			}

			if (i % 6 == 0) {

				counter += 100;
				lastNum = 0;
			}

			roomBtn.setHorizontalTextPosition(SwingUtilities.LEFT);
			roomBtn.setVerticalAlignment(SwingUtilities.BOTTOM);
			roomBtn.setPreferredSize(new Dimension(100, 60));
			roomBtn.setMaximumSize(new Dimension(100, 60));
			roomBtn.addActionListener(theAction.getActionListener());
			contentPanel.add(roomBtn);

		}
		
	}
	
	private MouseListener rightClickListener() {
		MouseAdapter adapter = new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				String trimmingText = "";
				
				if (e.getButton() == MouseEvent.BUTTON3) {
					trimmingText = e.getSource().toString();
					num = trimmingText.substring(trimmingText.length() - 25, trimmingText.length() - 21);

				}
			}

			public void mousePressed(MouseEvent e) {

				if (e.isPopupTrigger()) {
					getPopupMenu().show(e.getComponent(), e.getX(), e.getY());

				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					getPopupMenu().show(e.getComponent(), e.getX(), e.getY());
				}
			}

		};
		return adapter;
	}

	private JPopupMenu getPopupMenu() {

		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem cut = new JMenuItem(new DefaultEditorKit.CutAction());
		cut.setText("Cut");
		cut.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_X, (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
		cut.setIcon(new ImageIcon(Main_AllRooms.class.getResource("/com/coder/hms/icons/room_cut.png")));
		popupMenu.add(cut);

		JMenuItem copy = new JMenuItem(new DefaultEditorKit.CopyAction());
		copy.setText("Copy");
		copy.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_C, (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
		copy.setIcon(new ImageIcon(Main_AllRooms.class.getResource("/com/coder/hms/icons/room_copy.png")));
		popupMenu.add(copy);

		JMenuItem paste = new JMenuItem(new DefaultEditorKit.PasteAction());
		paste.setText("Paste");
		paste.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_V, (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
		paste.setIcon(new ImageIcon(Main_AllRooms.class.getResource("/com/coder/hms/icons/room_paste.png")));
		popupMenu.add(paste);

		JMenu changeCleaning = new JMenu();
		changeCleaning.setText("Change status");
		changeCleaning.setIcon(new ImageIcon(Main_AllRooms.class.getResource("/com/coder/hms/icons/room_changeStatus.png")));
		popupMenu.add(changeCleaning);
		
		JMenuItem clean = new JMenuItem();
		clean.setText("Set as clean");
		clean.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_W, (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
		clean.setIcon(new ImageIcon(Main_AllRooms.class.getResource("/com/coder/hms/icons/cleaning_single.png")));
		clean.addActionListener(ActionListener -> {
			final Room theRoom = roomDaoImpl.getRoomByRoomNumber(num);
			
			if(theRoom.getCleaningStatus().equals("CLEAN")) {
				JOptionPane.showMessageDialog(null, "Room is already clean!", JOptionPane.MESSAGE_PROPERTY,
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			else {
				roomDaoImpl.setSingleRoomAsCleanByRoomNumber(num);
				cookRooms(contentPanel);
				roomBtn.revalidate();
				roomBtn.repaint();
			}
		});
		changeCleaning.add(clean);
		
		JMenuItem dirty = new JMenuItem();
		dirty.setText("Set as dirty");
		dirty.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_D, (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
		dirty.setIcon(new ImageIcon(Main_AllRooms.class.getResource("/com/coder/hms/icons/room_dirty.png")));
		dirty.addActionListener(ActionListener -> {
			final Room theRoom = roomDaoImpl.getRoomByRoomNumber(num);
			
			if(theRoom.getCleaningStatus().equals("DIRTY")) {
				JOptionPane.showMessageDialog(null, "Room is already dirty!", JOptionPane.MESSAGE_PROPERTY,
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			else {
				roomDaoImpl.setSingleRoomAsDirtyByRoomNumber(num);
				cookRooms(contentPanel);
				roomBtn.revalidate();
				roomBtn.repaint();
			}
		});
		changeCleaning.add(dirty);

		JMenuItem checkout = new JMenuItem(new DefaultEditorKit.PasteAction());
		checkout.setText("Do checkout");
		checkout.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_O, (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
		checkout.setIcon(new ImageIcon(Main_AllRooms.class.getResource("/com/coder/hms/icons/room_checkout.png")));
		checkout.addActionListener(ActionListener -> {
			final Room checkingRoom = roomDaoImpl.getRoomByRoomNumber(num);

			if(checkingRoom.getUsageStatus().equals("FULL")) {
				if (checkingRoom.getBalance() == 0) {
					
					roomDaoImpl.setRoomCheckedOut(num);
					cookRooms(contentPanel);
					roomBtn.revalidate();
					roomBtn.repaint();
				}

				else {
					JOptionPane.showMessageDialog(null, "All room balances need to be zero!", JOptionPane.MESSAGE_PROPERTY,
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			else {
				JOptionPane.showMessageDialog(null, "Choosed room is empty!\nFor checkingout it must be full.", 
						JOptionPane.MESSAGE_PROPERTY, JOptionPane.ERROR_MESSAGE);
				return;
			}
			

		});
		popupMenu.add(checkout);
		
		JMenuItem getReservation = new JMenuItem();
		getReservation.setText("Open reservation");
		getReservation.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_R, (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
		getReservation.setIcon(new ImageIcon(Main_AllRooms.class.getResource("/com/coder/hms/icons/main_new_rez.png")));
		getReservation.addActionListener(ActionListener -> {
			final Room checkingRoom = roomDaoImpl.getRoomByRoomNumber(num);
			final Reservation rr = rImpl.getReservationById(checkingRoom.getReservationId());
			
			
			if(rr != null) {
				
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						
						final CustomerDaoImpl cImpl = new CustomerDaoImpl();
						final List<Customer> customerList = cImpl.getCustomerByReservId(rr.getId());
						
						String customerCountry = "";
						String customerName = "";
						String customerSurName = "";
						
						for(Customer cst: customerList) {
							customerCountry = cst.getCountry();
							customerName = cst.getFirstName();
							customerSurName = cst.getLastName();
						}
						
						NewReservExternalWindow nex = new NewReservExternalWindow();
						nex.setRezIdField(rr.getId());
						nex.setAgency(rr.getAgency());
						nex.setRoomNumber(checkingRoom.getNumber());
						nex.setRoomType(checkingRoom.getType());
						nex.setCustomerCountry(customerCountry);
						nex.setNameSurnameField(rr.getGroupName());
						nex.setCheckinDate(rr.getCheckinDate());
						nex.setCheckoutDate(rr.getCheckoutDate());
						nex.setTotalDaysField(rr.getTotalDays());
						nex.setHostType(rr.getHostType());
						nex.setCreditType(rr.getCreditType());
						nex.setReservStatus(rr.getBookStatus());
						nex.setReservNote(rr.getNote());
						nex.setCurrency(checkingRoom.getCurrency());
						nex.setPriceOfRoom(Double.parseDouble(checkingRoom.getPrice()));
						nex.setPersonCountSpinner(checkingRoom.getPersonCount());
						
						nex.setRoomCountTableRows(new Object[]{checkingRoom.getNumber(), checkingRoom.getType(),
								checkingRoom.getPersonCount(), checkingRoom.getPrice(), checkingRoom.getCurrency()});
						
						nex.setRoomInfoTableRows(new Object[]{checkingRoom.getNumber(), checkingRoom.getType(),
								customerName, customerSurName});
						nex.setVisible(true);
						
					}
				});
			}
			
			else {
				JOptionPane.showMessageDialog(null, "Choosed room is not reserved!", 
						JOptionPane.MESSAGE_PROPERTY, JOptionPane.ERROR_MESSAGE);
				return;
			}
			

		});
		popupMenu.add(getReservation);

		return popupMenu;
	}
}
