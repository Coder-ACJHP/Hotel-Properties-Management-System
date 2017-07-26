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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import com.coder.hms.daoImpl.RoomDaoImpl;
import com.coder.hms.entities.Room;

public class CustomAllRooms {

	private final List<Room> roomList;
	private final RoomDaoImpl roomDaoImpl;
	private JPanel contentPanel = new JPanel();
	private final RoomsAction theAction = new RoomsAction();
	int counter = 100;
	int lastNum = 0;
	private String num;

	/**
	 * Create the dialog.
	 */
	public CustomAllRooms(int roomCount) {

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Color.decode("#066d95"));
		contentPanel.setPreferredSize(new Dimension(700, 1000));
		contentPanel.setLayout(new FlowLayout());

		roomDaoImpl = new RoomDaoImpl();
		roomList = roomDaoImpl.getAllRooms();

		MatteBorder dirtyBorder = BorderFactory.createMatteBorder(8, 0, 0, 0, Color.decode("#ce1d1d"));
		MatteBorder cleanBorder = BorderFactory.createMatteBorder(8, 0, 0, 0, Color.WHITE);

		for (int i = 1; i <= roomCount; i++) {
			++lastNum;
			final JButton roomBtn = new JButton();

			roomBtn.setFont(new Font("Arial", Font.BOLD, 12));
			roomBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			roomBtn.setText(counter + "" + lastNum);
			roomBtn.setBorderPainted(true);

			for (Room room : roomList) {

				if (room.getNumber().equals(counter + "" + lastNum)) {
					roomBtn.setToolTipText(room.getType());
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

					switch (room.getUsageStatus()) {
					case "EMPTY":
						roomBtn.setBackground(Color.decode("#afe2fb"));
						break;
					case "FULL":
						roomBtn.setBackground(Color.decode("#fffcbe"));
						break;
					default:
						break;
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

		contentPanel.setVisible(true);
	}

	public JPanel getWindow() {
		return this.contentPanel;
	}

	public void setWindow(JPanel thePanel) {
		this.contentPanel = thePanel;
	}

	private MouseListener rightClickListener() {
		MouseAdapter adapter = new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					final String trimmingText = e.getSource().toString();
					num = trimmingText.substring(trimmingText.length() - 25, trimmingText.length() - 21);

				}
				super.mouseClicked(e);
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
		cut.setIcon(new ImageIcon(CustomAllRooms.class.getResource("/com/coder/hms/icons/room_cut.png")));
		popupMenu.add(cut);

		JMenuItem copy = new JMenuItem(new DefaultEditorKit.CopyAction());
		copy.setText("Copy");
		copy.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_C, (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
		copy.setIcon(new ImageIcon(CustomAllRooms.class.getResource("/com/coder/hms/icons/room_copy.png")));
		popupMenu.add(copy);

		JMenuItem paste = new JMenuItem(new DefaultEditorKit.PasteAction());
		paste.setText("Paste");
		paste.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_V, (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
		paste.setIcon(new ImageIcon(CustomAllRooms.class.getResource("/com/coder/hms/icons/room_paste.png")));
		popupMenu.add(paste);

		JMenuItem clean = new JMenuItem(new DefaultEditorKit.PasteAction());
		clean.setText("Set as clean");
		clean.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_N, (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
		clean.setIcon(new ImageIcon(CustomAllRooms.class.getResource("/com/coder/hms/icons/cleaning_single.png")));
		clean.addActionListener(ActionListener -> {
			final Room theRoom = roomDaoImpl.getRoomByRoomNumber(num);
			
			if(theRoom.getCleaningStatus().equals("CLEAN")) {
				JOptionPane.showMessageDialog(null, "Room is already clean!", JOptionPane.MESSAGE_PROPERTY,
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			else {
				roomDaoImpl.setSingleRoomAsCleanByRoomNumber(num);
				SwingUtilities.updateComponentTreeUI(contentPanel);
				contentPanel.repaint();
			}
		});
		popupMenu.add(clean);

		JMenuItem checkout = new JMenuItem(new DefaultEditorKit.PasteAction());
		checkout.setText("Do checkout");
		checkout.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_O, (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
		checkout.setIcon(new ImageIcon(CustomAllRooms.class.getResource("/com/coder/hms/icons/room_checkout.png")));
		checkout.addActionListener(ActionListener -> {
			final Room checkingRoom = roomDaoImpl.getRoomByRoomNumber(num);

			if(checkingRoom.getUsageStatus().equals("FULL")) {
				if (checkingRoom.getBalance() == 0) {

					roomDaoImpl.setRoomCheckedOut(num);
					SwingUtilities.updateComponentTreeUI(contentPanel);
					contentPanel.repaint();
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

		return popupMenu;
	}

}
