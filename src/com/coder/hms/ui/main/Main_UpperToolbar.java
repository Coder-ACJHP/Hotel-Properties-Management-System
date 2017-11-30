/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.ui.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import com.coder.hms.beans.LocaleBean;
import com.coder.hms.ui.inner.ColorInfoTable;
import com.coder.hms.utils.ChangeComponentOrientation;
import com.coder.hms.utils.ResourceControl;

public class Main_UpperToolbar extends JPanel {

    
    /**
	 * 
	 */
    private Main_Audit audit;
    private Main_AllRooms theRooms;
    private Main_CashDesk cashdesk;
    private static LocaleBean bean;
    private Main_Blockade blockadeFrame;
    private Main_Reservations rezervFrame;
    private ColorInfoTable infoColorTable;
    private Main_RoomCleaning cleaningFrame;
    private Main_CustomersFrame customersFrame;
    private static final long serialVersionUID = 1L;
    private final ChangeComponentOrientation componentOrientation;
    private JButton roomsBtn, guestsBtn, rezervationBtn, blockadeBtn, 
                        roomCleaningBtn, cashBtn, auditBtn, refreshBtn;

    
    
    public Main_UpperToolbar(final JPanel mainFramePanel) {
    	setAlignmentX(Component.LEFT_ALIGNMENT);
    	setMaximumSize(new Dimension(32767, 55));
    	setBounds(new Rectangle(0, 0, 1224, 55));
    	
    	setAutoscrolls(true);
    	setSize(new Dimension(1224, 55));
    	setPreferredSize(new Dimension(1224, 55));
    	setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        setBackground(SystemColor.activeCaption);
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
    	
        bean = LocaleBean.getInstance();
        componentOrientation = new ChangeComponentOrientation();
        
        

        roomsBtn = new JButton("Rooms Plain");
        roomsBtn.setMinimumSize(new Dimension(137, 40));
        roomsBtn.setMaximumSize(new Dimension(137, 40));
        roomsBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        roomsBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        roomsBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/main_room.png")));
        roomsBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
        roomsBtn.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
        roomsBtn.setPreferredSize(new Dimension(137, 40));
        roomsBtn.addActionListener(UpperToolbarActionListener(mainFramePanel));
        this.add(roomsBtn);

        guestsBtn = new JButton("Guests");
        guestsBtn.setMinimumSize(new Dimension(137, 40));
        guestsBtn.setMaximumSize(new Dimension(137, 40));
        guestsBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        guestsBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        guestsBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/main_guests.png")));
        guestsBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
        guestsBtn.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
        guestsBtn.setPreferredSize(new Dimension(137, 40));
        guestsBtn.addActionListener(UpperToolbarActionListener(mainFramePanel));
        this.add(guestsBtn);

        rezervationBtn = new JButton("Reservations");
        rezervationBtn.setMinimumSize(new Dimension(137, 40));
        rezervationBtn.setMaximumSize(new Dimension(137, 40));
        rezervationBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        rezervationBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        rezervationBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/main_rezerv.png")));
        rezervationBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
        rezervationBtn.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
        rezervationBtn.setPreferredSize(new Dimension(137, 40));
        rezervationBtn.addActionListener(UpperToolbarActionListener(mainFramePanel));
        this.add(rezervationBtn);

        blockadeBtn = new JButton("Blockade");
        blockadeBtn.setPreferredSize(new Dimension(137, 40));
        blockadeBtn.setMinimumSize(new Dimension(137, 40));
        blockadeBtn.setMaximumSize(new Dimension(137, 40));
        blockadeBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        blockadeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        blockadeBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/main_blockade.png")));
        blockadeBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
        blockadeBtn.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
        blockadeBtn.addActionListener(UpperToolbarActionListener(mainFramePanel));
        this.add(blockadeBtn);

        roomCleaningBtn = new JButton("Room Cleaning");
        roomCleaningBtn.setMinimumSize(new Dimension(137, 40));
        roomCleaningBtn.setMaximumSize(new Dimension(137, 40));
        roomCleaningBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        roomCleaningBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        roomCleaningBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/login_clear.png")));
        roomCleaningBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
        roomCleaningBtn.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
        roomCleaningBtn.setPreferredSize(new Dimension(137, 40));
        roomCleaningBtn.addActionListener(UpperToolbarActionListener(mainFramePanel));
        this.add(roomCleaningBtn);

        cashBtn = new JButton("Cash Desk");
        cashBtn.setMinimumSize(new Dimension(137, 40));
        cashBtn.setMaximumSize(new Dimension(137, 40));
        cashBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        cashBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cashBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/main_cash.png")));
        cashBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
        cashBtn.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
        cashBtn.setPreferredSize(new Dimension(137, 40));
        cashBtn.addActionListener(UpperToolbarActionListener(mainFramePanel));
        this.add(cashBtn);

        final JSeparator separator = new JSeparator();
        separator.setBackground(Color.DARK_GRAY);
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setFocusable(true);
        separator.setForeground(Color.DARK_GRAY);
        separator.setAutoscrolls(true);
        separator.setPreferredSize(new Dimension(10, 40));
        this.add(separator);

        auditBtn = new JButton("Audit");
        auditBtn.setMinimumSize(new Dimension(137, 40));
        auditBtn.setMaximumSize(new Dimension(137, 40));
        auditBtn.setIcon(new ImageIcon(Main_UpperToolbar.class.getResource("/com/coder/hms/icons/main_audit.png")));
        auditBtn.setPreferredSize(new Dimension(137, 40));
        auditBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
        auditBtn.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
        auditBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        auditBtn.addActionListener(UpperToolbarActionListener(mainFramePanel));
        this.add(auditBtn);

        JSeparator secondSeparator = new JSeparator();
        secondSeparator.setPreferredSize(new Dimension(10, 40));
        secondSeparator.setOrientation(SwingConstants.VERTICAL);
        secondSeparator.setForeground(Color.DARK_GRAY);
        secondSeparator.setFocusable(true);
        secondSeparator.setBackground(Color.DARK_GRAY);
        secondSeparator.setAutoscrolls(true);
        this.add(secondSeparator);

        refreshBtn = new JButton("");
        refreshBtn.setPreferredSize(new Dimension(75, 40));
        refreshBtn.setMinimumSize(new Dimension(75, 40));
        refreshBtn.setMaximumSize(new Dimension(75, 40));
        refreshBtn.setToolTipText("Refresh the application main window.");
        refreshBtn.setMnemonic(KeyEvent.VK_F5);
        refreshBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        refreshBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        refreshBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        refreshBtn.setIcon(new ImageIcon(Main_UpperToolbar.class.getResource("/com/coder/hms/icons/menubar_exchange_calculate.png")));
        refreshBtn.setActionCommand("Refresh");
        refreshBtn.addActionListener(UpperToolbarActionListener(mainFramePanel));
        this.add(refreshBtn);

        componentOrientation.setThePanel(this);

        changeLanguage(bean.getLocale());
        //change component orientation with locale.
        if (bean.getLocale().toString().equals("ar_IQ")) {
            componentOrientation.changeOrientationOfJPanelToRight();
        } else {
            componentOrientation.changeOrientationOfJPanelToLeft();
        }

    }

    private void changeLanguage(Locale locale) throws MissingResourceException {

        final ResourceBundle bundle = ResourceBundle
                .getBundle("com/coder/hms/languageFiles/LocalizationBundle", locale, new ResourceControl());
        this.roomsBtn.setText(bundle.getString("RoomsPlan"));
        this.guestsBtn.setText(bundle.getString("Guests"));
        this.auditBtn.setText(bundle.getString("Audit"));
        this.blockadeBtn.setText(bundle.getString("Blockade"));
        this.rezervationBtn.setText(bundle.getString("Reservations"));
        this.roomCleaningBtn.setText(bundle.getString("RoomCleaning"));
        this.cashBtn.setText(bundle.getString("CashDesk"));
        this.revalidate();
        this.repaint();
    }

    public ActionListener UpperToolbarActionListener(final JPanel mainPanel) {

        ActionListener actionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == roomsBtn) {

                    theRooms = new Main_AllRooms();
                    infoColorTable = new ColorInfoTable();

                    // Set the usage of room into info table
                    infoColorTable.setCleanLabelCount(theRooms.cleanCounter);
                    infoColorTable.setDirtyLabelCount(theRooms.dirtyCounter);
                    infoColorTable.setDndLabelCount(theRooms.dndCounter);

                    mainPanel.removeAll();
                    mainPanel.add(theRooms.getWindow(), BorderLayout.WEST);
                    mainPanel.add(infoColorTable, BorderLayout.EAST);
                    mainPanel.revalidate();
                    mainPanel.repaint();

                } else if (e.getSource() == guestsBtn) {

                    customersFrame = new Main_CustomersFrame();
                    mainPanel.removeAll();
                    mainPanel.add(customersFrame, BorderLayout.CENTER);
                    mainPanel.revalidate();
                    mainPanel.repaint();
                } else if (e.getSource() == rezervationBtn) {

                    rezervFrame = new Main_Reservations();
                    mainPanel.removeAll();
                    rezervFrame.populateMainTable();
                    mainPanel.add(rezervFrame, BorderLayout.CENTER);
                    mainPanel.revalidate();
                    mainPanel.repaint();
                } else if (e.getSource() == blockadeBtn) {

                    blockadeFrame = new Main_Blockade();
                    mainPanel.removeAll();
                    mainPanel.add(blockadeFrame, BorderLayout.CENTER);
                    mainPanel.revalidate();
                    mainPanel.repaint();

                } else if (e.getSource() == roomCleaningBtn) {

                    cleaningFrame = new Main_RoomCleaning();
                    mainPanel.removeAll();
                    mainPanel.add(cleaningFrame, BorderLayout.CENTER);
                    mainPanel.revalidate();
                    mainPanel.repaint();
                } else if (e.getSource() == cashBtn) {

                    cashdesk = new Main_CashDesk();
                    mainPanel.removeAll();
                    mainPanel.add(cashdesk, BorderLayout.CENTER);
                    mainPanel.revalidate();
                    mainPanel.repaint();
                } else if (e.getSource() == auditBtn) {

                    audit = new Main_Audit();
                    audit.initializeAuditPane();
                    mainPanel.removeAll();
                    mainPanel.add(audit, BorderLayout.CENTER);
                    mainPanel.revalidate();
                    mainPanel.repaint();

                } else if (e.getSource() == refreshBtn) {
                    mainPanel.removeAll();
                    mainPanel.revalidate();
                    mainPanel.repaint();
                }
            }
        };
        return actionListener;
    }
}
