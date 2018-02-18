/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.ui.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.DefaultEditorKit;

import com.coder.hms.beans.LocaleBean;
import com.coder.hms.daoImpl.CustomerDaoImpl;
import com.coder.hms.daoImpl.HotelDaoImpl;
import com.coder.hms.daoImpl.HotelSystemStatusImpl;
import com.coder.hms.daoImpl.PaymentDaoImpl;
import com.coder.hms.daoImpl.ReservationDaoImpl;
import com.coder.hms.daoImpl.RoomDaoImpl;
import com.coder.hms.entities.Customer;
import com.coder.hms.entities.HotelSystemStatus;
import com.coder.hms.entities.Payment;
import com.coder.hms.entities.Reservation;
import com.coder.hms.entities.Room;
import com.coder.hms.ui.external.NewReservationWindow;
import com.coder.hms.ui.external.ReservedCheckinWindow;
import com.coder.hms.ui.external.RoomWindow;
import com.coder.hms.ui.external.Walkin_CheckinWindow;
import com.coder.hms.utils.ChangeComponentOrientation;

public class Main_AllRooms {

    protected JButton roomBtn;
    public int dndCounter = 0;
    public int cleanCounter = 0;
    public int dirtyCounter = 0;
    private List<Room> roomList;
    private String currentRoomNumber;
    private static LocaleBean bean;
    private final HotelSystemStatus systemStatus;
    private final JPanel contentPanel = new JPanel();
    private final ChangeComponentOrientation componentOrientation;
    private final RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
    private final HotelDaoImpl hotelDaoImpl = new HotelDaoImpl();
    private final ReservationDaoImpl rImpl = new ReservationDaoImpl();
    private final PaymentDaoImpl paymentDaoImpl = new PaymentDaoImpl();
    private final HotelSystemStatusImpl systemStatusImpl = new HotelSystemStatusImpl();

    /**
     * Create the dialog.
     */
    public Main_AllRooms() {

        bean = LocaleBean.getInstance();
        componentOrientation = new ChangeComponentOrientation();
        componentOrientation.setThePanel(contentPanel);

        systemStatus = systemStatusImpl.getSystemStatus();

        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setBackground(Color.decode("#066d95"));
        contentPanel.setPreferredSize(new Dimension(700, 1000));
        contentPanel.setLayout(new FlowLayout());

        cookRooms(contentPanel);

        //change component orientation with locale.
        if (bean.getLocale().toString().equals("ar_IQ")) {
            componentOrientation.changeOrientationOfJPanelToRight();
        } else {
            componentOrientation.changeOrientationOfJPanelToLeft();
        }

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

        final MatteBorder cleanBorder = BorderFactory.createMatteBorder(11, 1, 1, 1, Color.WHITE);
        final MatteBorder dndBorder = BorderFactory.createMatteBorder(11, 1, 1, 1, Color.decode("#ffc300"));
        final MatteBorder dirtyBorder = BorderFactory.createMatteBorder(11, 1, 1, 1, Color.decode("#ce1d1d"));

        final int roomCount = hotelDaoImpl.getHotel().getRoomCapacity();

        for (int i = 1; i <= roomCount; i++) {
            ++lastNum;
            roomBtn = new JButton();

            roomBtn.setFont(new Font("Arial", Font.BOLD, 14));
            roomBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
            roomBtn.setText(counter + "" + lastNum);
            roomBtn.setBorderPainted(true);

            for (Room room : roomList) {

                if (room.getNumber().equals(counter + "" + lastNum)) {

                    roomBtn.addMouseListener(rightClickListener());

                    roomBtn.setToolTipText("<html>" + "Type : " + room.getType() + "<br>"
                            + "Number : " + room.getNumber() + "<br>"
                            + "Status : " + room.getUsageStatus() + " / " + room.getCleaningStatus() + "<br>"
                            + "Person Count : " + room.getPersonCount() + "<br>"
                            + "Group Name : " + room.getCustomerGrupName() + "</html>");

                    switch (room.getCleaningStatus()) {
                        case "CLEAN":
                            roomBtn.setBorder(cleanBorder);
                            cleanCounter++;
                            break;
                        case "DIRTY":
                            roomBtn.setBorder(dirtyBorder);
                            dirtyCounter++;
                            break;
                        case "DND":
                            roomBtn.setBorder(dndBorder);
                            dndCounter++;
                            break;
                        default:
                            break;
                    }

                    final String ROOM_STATUS = room.getUsageStatus();
                    final long reservId = room.getReservationId();

                    if (reservId != 0) {
                        final Optional<Reservation> theReservation = rImpl.findReservationById(room.getReservationId());

                        ///////////////////////////////////////////////////////////////////////////
                        //Convert check in, check out, today from String to date than compare all// 
                        LocalDate localDate = LocalDate.parse(theReservation.get().getCheckoutDate()); //
                        final Date checkoutDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                        final Date defaultDate = Date.from(systemStatus.getDateTime().atStartOfDay(ZoneId.systemDefault()).toInstant());

                        localDate = LocalDate.parse(theReservation.get().getCheckinDate());            //
                        final Date checkinDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                        ///////////////////////////////////////////////////////////////////////////

                        if (ROOM_STATUS.equals("FULL")) {

                            if (checkoutDate.equals(defaultDate) && checkoutDate.after(defaultDate)) {
                                roomBtn.setBackground(Color.decode("#990033"));
                            } else {
                                roomBtn.setForeground(Color.WHITE);
                                roomBtn.setBackground(Color.decode("#0051ab"));
                            }
                        } else if (ROOM_STATUS.equals("BLOCKED")) {
                            if (checkinDate.equals(defaultDate)) {
                                roomBtn.setBackground(Color.decode("#eca7d0"));
                            } else {
                                roomBtn.setBackground(Color.decode("#afe2fb"));
                            }
                        } else {
                            roomBtn.setBackground(Color.decode("#afe2fb"));
                        }
                    } else {

                        roomBtn.setBackground(Color.decode("#afe2fb"));
                    }
                }
            }

            if (i % 6 == 0) {

                counter += 100;
                lastNum = 0;
            }

            roomBtn.setHorizontalTextPosition(SwingConstants.LEFT);
            roomBtn.setVerticalAlignment(SwingConstants.BOTTOM);
            roomBtn.setPreferredSize(new Dimension(100, 65));
            roomBtn.setMaximumSize(new Dimension(100, 65));
            contentPanel.add(roomBtn);

        }

    }

    private MouseListener rightClickListener() {
        MouseAdapter adapter = new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getButton() == MouseEvent.BUTTON3) {

                    final String trimmingText = e.getSource().toString();
                    final int textLength = trimmingText.length();
                    currentRoomNumber = trimmingText.substring(textLength - 25, textLength - 21);

                }

                // set the button when click one time just focus on
                if (e.getClickCount() == 1) {

                    JButton comp = (JButton) e.getComponent();
                    comp.setFocusPainted(true);
                } // in double click do some thing other
                else if (e.getClickCount() == 2) {

                    int counter = 100;
                    int lastNum = 0;
                    String roomText = "";

                    // get the button text
                    String command = e.getSource().toString();

                    // get new date as String
                    String innerDate = systemStatus.getDateTime().toString();

                    // start loop to get all rooms as room capacity
                    for (int i = 1; i <= hotelDaoImpl.getHotel().getRoomCapacity(); i++) {
                        ++lastNum;

                        // create the button text with same algorithm like in
                        // 'AllRooms.class'
                        roomText = counter + "" + lastNum;

                        if (i % 6 == 0) {
                            counter += 100;
                            lastNum = 0;
                        }

                        ///////////////////////////////////////////////
                        // check the clicked button if contains same //
                        // looping button text work with that button //
                        ///////////////////////////////////////////////
                        // NOTE: Don't forget to add 'break' command when you
                        // finished
                        // your job to quit from the loop.
                        if (command.contains(roomText)) {

                            final Room theRoom = roomDaoImpl.getRoomByRoomNumber(roomText);
                            final Optional<Reservation> foundedReserv = rImpl.findReservationById(theRoom.getReservationId());

                            if (theRoom.getUsageStatus().equals("FULL")) {
                                SwingUtilities.invokeLater(() -> {
                                    new RoomWindow(theRoom.getNumber());
                                    cookRooms(contentPanel);
                                    refresh();
                                });
                                break;
                            } else if (theRoom.getUsageStatus().equals("BLOCKED")
                                    && innerDate.equals(foundedReserv.get().getCheckinDate())) {

                                SwingUtilities.invokeLater(() -> {
                                    new ReservedCheckinWindow(theRoom).setVisible(true);
                                    cookRooms(contentPanel);
                                    refresh();
                                });
                                break;
                            } else if (theRoom.getUsageStatus().equals("EMPTY")) {

                                SwingUtilities.invokeLater(() -> {
                                    new Walkin_CheckinWindow(theRoom).setVisible(true);
                                    cookRooms(contentPanel);
                                    refresh();
                                });
                                break;
                            }
                        }
                    }
                    
                }
                super.mouseClicked(e);
                refresh();
            }

            @Override
            public void mousePressed(MouseEvent e) {

                if (e.isPopupTrigger()) {
                    getPopupMenu().show(e.getComponent(), e.getX(), e.getY());

                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    getPopupMenu().show(e.getComponent(), e.getX(), e.getY());
                }
            }

        };
        return adapter;
    }

    private JPopupMenu getPopupMenu() {

        final JPopupMenu popupMenu = new JPopupMenu();
        final JMenuItem cut = new JMenuItem(new DefaultEditorKit.CutAction());
        cut.setText("Cut");
        cut.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_X, (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
        cut.setIcon(new ImageIcon(Main_AllRooms.class.getResource("/com/coder/hms/icons/room_cut.png")));
        popupMenu.add(cut);

        final JMenuItem copy = new JMenuItem(new DefaultEditorKit.CopyAction());
        copy.setText("Copy");
        copy.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_C, (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
        copy.setIcon(new ImageIcon(Main_AllRooms.class.getResource("/com/coder/hms/icons/room_copy.png")));
        popupMenu.add(copy);

        final JMenuItem paste = new JMenuItem(new DefaultEditorKit.PasteAction());
        paste.setText("Paste");
        paste.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_V, (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
        paste.setIcon(new ImageIcon(Main_AllRooms.class.getResource("/com/coder/hms/icons/room_paste.png")));
        popupMenu.add(paste);

        final JMenu changeCleaning = new JMenu();
        changeCleaning.setText("Change status");
        changeCleaning.setIcon(new ImageIcon(Main_AllRooms.class.getResource("/com/coder/hms/icons/room_changeStatus.png")));
        popupMenu.add(changeCleaning);

        final JMenuItem clean = new JMenuItem();
        clean.setText("Set as clean");
        clean.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
        clean.setIcon(new ImageIcon(Main_AllRooms.class.getResource("/com/coder/hms/icons/cleaning_single.png")));
        clean.addActionListener(ActionListener -> {
            final Room theRoom = roomDaoImpl.getRoomByRoomNumber(currentRoomNumber);

            if (theRoom.getCleaningStatus().equals("CLEAN")) {
                JOptionPane.showMessageDialog(null, "Room is already clean!", JOptionPane.MESSAGE_PROPERTY,
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                roomDaoImpl.setSingleRoomAsCleanByRoomNumber(currentRoomNumber);
                cookRooms(contentPanel);
                refresh();
            }
        });
        changeCleaning.add(clean);

        final JMenuItem dirty = new JMenuItem();
        dirty.setText("Set as dirty");
        dirty.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
        dirty.setIcon(new ImageIcon(Main_AllRooms.class.getResource("/com/coder/hms/icons/room_dirty.png")));
        dirty.addActionListener(ActionListener -> {
            final Room theRoom = roomDaoImpl.getRoomByRoomNumber(currentRoomNumber);

            if (theRoom.getCleaningStatus().equals("DIRTY")) {
                JOptionPane.showMessageDialog(null, "Room is already dirty!", JOptionPane.MESSAGE_PROPERTY,
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                roomDaoImpl.setSingleRoomAsDirtyByRoomNumber(currentRoomNumber);
                cookRooms(contentPanel);
                refresh();
            }
        });
        changeCleaning.add(dirty);

        JMenuItem dnd = new JMenuItem();
        dnd.setText("Set as DND");
        dnd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
        dnd.setIcon(new ImageIcon(Main_AllRooms.class.getResource("/com/coder/hms/icons/room_dnd.png")));
        dnd.addActionListener(ActionListener -> {
            final Room theRoom = roomDaoImpl.getRoomByRoomNumber(currentRoomNumber);

            if (theRoom.getCleaningStatus().equals("DND")) {
                JOptionPane.showMessageDialog(null, "Room is already DND!", JOptionPane.MESSAGE_PROPERTY,
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                roomDaoImpl.setSingleRoomAsDNDByRoomNumber(currentRoomNumber);
                cookRooms(contentPanel);
                refresh();
            }
        });
        changeCleaning.add(dnd);

        JMenuItem checkout = new JMenuItem(new DefaultEditorKit.PasteAction());
        checkout.setText("Do checkout");
        checkout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
        checkout.setIcon(new ImageIcon(Main_AllRooms.class.getResource("/com/coder/hms/icons/room_checkout.png")));
        checkout.addActionListener(ActionListener -> {
            final Room checkingRoom = roomDaoImpl.getRoomByRoomNumber(currentRoomNumber);

            if (checkingRoom.getUsageStatus().equals("FULL")) {

                final double price = Math.ceil(checkingRoom.getRemainingDebt());

                if (price == 0) {

                    roomDaoImpl.setRoomCheckedOut(currentRoomNumber);
                    cookRooms(contentPanel);
                    refresh();
                    
                } else {
                    JOptionPane.showMessageDialog(null, "All room balances need to be zero!", JOptionPane.MESSAGE_PROPERTY,
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
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
            final Room checkingRoom = roomDaoImpl.getRoomByRoomNumber(currentRoomNumber);
            final Optional<Reservation> foundReserv = rImpl.findReservationById(checkingRoom.getReservationId());

            if (foundReserv.isPresent()) {

                SwingUtilities.invokeLater(() -> {
                    final CustomerDaoImpl cImpl = new CustomerDaoImpl();
                    final List<Customer> customerList = cImpl.getCustomerByReservId(foundReserv.get().getId());

                    Payment payment = null;
                    String customerCountry = "";
                    String customerName = "";
                    String customerSurName = "";

                    for (Customer cst : customerList) {
                        customerCountry = cst.getCountry();
                        customerName = cst.getFirstName();
                        customerSurName = cst.getLastName();
                    }

                    final NewReservationWindow nex = new NewReservationWindow();
                    nex.setRezIdField(foundReserv.get().getId());
                    nex.setAgency(foundReserv.get().getAgency());
                    nex.setRoomNumber(checkingRoom.getNumber());
                    nex.setRoomType(checkingRoom.getType());
                    nex.setCustomerCountry(customerCountry);
                    nex.setNameSurnameField(foundReserv.get().getGroupName());
                    nex.setCheckinDate(foundReserv.get().getCheckinDate());
                    nex.setCheckoutDate(foundReserv.get().getCheckoutDate());
                    nex.setTotalDaysField(foundReserv.get().getTotalDays());
                    nex.setHostType(foundReserv.get().getHostType());
                    nex.setCreditType(foundReserv.get().getCreditType());
                    nex.setReservStatus(foundReserv.get().getBookStatus());
                    nex.setReservNote(foundReserv.get().getNote());
                    nex.setCurrency(checkingRoom.getCurrency());
                    nex.setPriceOfRoom(checkingRoom.getPrice());
                    nex.setAgencyRefNo(foundReserv.get().getAgencyRefNo());
                    nex.setReferanceNo(foundReserv.get().getReferanceNo());
                    nex.setPersonCountSpinner(checkingRoom.getPersonCount());

                    nex.setRoomCountTableRows(new Object[]{checkingRoom.getNumber(), checkingRoom.getType(),
                        checkingRoom.getPersonCount(), checkingRoom.getPrice(), checkingRoom.getCurrency()});

                    nex.setRoomInfoTableRows(new Object[]{checkingRoom.getNumber(), checkingRoom.getType(),
                        customerName, customerSurName});

                    if (foundReserv.get().getPaymentStatus()) {

                        payment = paymentDaoImpl.getEarlyPaymentByRoomNumber(checkingRoom.getNumber());
                        nex.setEarlyPaymetTableRows(new Object[]{payment.getTitle(), payment.getPaymentType(),
                            payment.getPrice(), payment.getCurrency(), payment.getExplanation()});
                    }

                    nex.setVisible(true);

                    if (foundReserv.get().getPaymentStatus()) {
                        JOptionPane.showMessageDialog(new JFrame(), "Early payment " + payment.getPrice() + payment.getCurrency(),
                                JOptionPane.MESSAGE_PROPERTY, JOptionPane.INFORMATION_MESSAGE);
                    }
                });
            } else {
                JOptionPane.showMessageDialog(null, "Choosed room is not reserved!",
                        JOptionPane.MESSAGE_PROPERTY, JOptionPane.ERROR_MESSAGE);
                return;
            }

        });
        popupMenu.add(getReservation);

        return popupMenu;
    }

    public JPanel getWindow() {
        return this.contentPanel;
    }
    
    private void refresh() {
        contentPanel.revalidate();
        contentPanel.repaint();
        roomBtn.revalidate();
        roomBtn.repaint();
    }
}
