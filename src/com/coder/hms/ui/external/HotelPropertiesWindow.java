package com.coder.hms.ui.external;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

import org.apache.commons.codec.binary.Base64;

import com.coder.hms.beans.SessionBean;
import com.coder.hms.daoImpl.HotelDaoImpl;
import com.coder.hms.entities.Hotel;
import com.coder.hms.utils.LoggingEngine;

public class HotelPropertiesWindow extends JDialog {

    /**
     *
     */
    private Hotel theHotel;
    private JLabel stars = null;
    private BufferedImage newImage;
    private String roomTypeVal = "";
    private JFrame modalFrame = null;
    private JTextPane fullAdressField;
    int starValue, typeValue, capacityVal;
    private JLabel hotelNameTitle, pictlabel;
    private static LoggingEngine loggingEngine;
    private MaskFormatter maskFormatter = null;
    private JFormattedTextField phoneFrmtField;
    private JLabel[] starlabels = new JLabel[5];
    private static final long serialVersionUID = 1L;
    private JButton addPictureBtn, btnClear, btnLogin;
    private final HotelDaoImpl hotelDaoImpl = new HotelDaoImpl();
    private JTextField[] roomTypeFields = new JTextField[3];
    private JTextField nameField, ownerNameField, cityField, roomTypes;
    private JComboBox<String> hotelTypeCmbBox, countryCmbBox;
    private JSpinner starsSpinner, roomTypeSpinner, capacitySpinner;
    private static SessionBean sessionBean = SessionBean.getSESSION_BEAN();
    private JPanel upperPanel, picturePanel, bottomPanel, panel, buttonsPanel, starHolder;
    private final String[] HOTEL_TYPES = {"Airport Hotel", "Extended Stay Hote", "Serviced Apartment", "Suite Hotel",
        "Standart Hotel", "Resort Hotel", "Casino Hotel", "Boutique"};
    private final String[] COUNTRY_LIST = {"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra",
        "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria",
        "Brazil", "Egypt", "Finland", "France", "Germany", "Hong Kong", "India", "Iran", "Iraq", "Ireland", "Israel", "Islands",
        "Italy", "Jamaica", "Japan", "Republic of Korea", "Kuwait", "Lebanon", "Malaysia", "Mexico", "Nigeria", "Poland",
        "Portugal", "Puerto Rico,PR", "Qatar", "Romania", "Russian Federation", "Saudi Arabia", "Singapore", "Spain", "Sweden",
        "Switzerland", "Syrian Arab Republic", "Thailand", "Tunisia", "Turkey", "Turkmenistan", "Ukraine", "United Arab Emirates",
        "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Virgin Islands U.S.", "Yemen", "Zambia", "Zimbabwe"};

    /**
     * Create the dialog.
     */
    public HotelPropertiesWindow() {

        loggingEngine = LoggingEngine.getInstance();
        loggingEngine.setMessage("User is : " + sessionBean.getNickName());

        setResizable(false);
        setBounds(100, 50, 637, 694);
        getContentPane().setBackground(Color.decode("#066d95"));
        getContentPane().setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Coder HPMSA - [Hotel Properties]");

        upperPanel = new JPanel();
        upperPanel.setBorder(null);
        upperPanel.setPreferredSize(new Dimension(10, 320));
        upperPanel.setAutoscrolls(true);
        upperPanel.setBackground(Color.decode("#066d95"));
        getContentPane().add(upperPanel, BorderLayout.NORTH);
        upperPanel.setLayout(null);

        hotelNameTitle = new JLabel("HOTEL NAME");
        hotelNameTitle.setHorizontalTextPosition(SwingConstants.CENTER);
        hotelNameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        hotelNameTitle.setForeground(new Color(220, 20, 60));
        hotelNameTitle.setFont(new Font("Verdana", Font.BOLD, 30));
        hotelNameTitle.setAutoscrolls(true);
        hotelNameTitle.setBounds(17, 7, 379, 50);
        upperPanel.add(hotelNameTitle);

        picturePanel = new JPanel();
        picturePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        picturePanel.setBounds(16, 60, 604, 229);
        upperPanel.add(picturePanel);
        picturePanel.setLayout(new BorderLayout(0, 0));

        JScrollPane picHolderScroll = new JScrollPane();
        picHolderScroll.setBorder(null);
        picHolderScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        picHolderScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        picturePanel.add(picHolderScroll, BorderLayout.CENTER);

        pictlabel = new JLabel("");
        pictlabel.setAutoscrolls(true);
        pictlabel.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.LIGHT_GRAY, null));
        pictlabel.setHorizontalTextPosition(SwingConstants.CENTER);
        pictlabel.setHorizontalAlignment(SwingConstants.CENTER);
        picHolderScroll.setViewportView(pictlabel);

        starHolder = new JPanel();
        starHolder.setBounds(397, 10, 222, 50);
        starHolder.setBackground(Color.decode("#066d95"));
        upperPanel.add(starHolder);

        addPictureBtn = new JButton("Add picture");
        addPictureBtn.setBounds(467, 293, 130, 24);
        upperPanel.add(addPictureBtn);
        addPictureBtn.addActionListener(addPictureListener());
        addPictureBtn.setIcon(new ImageIcon(HotelPropertiesWindow.class.getResource("/com/coder/hms/icons/hotel_external_pic.png")));
        addPictureBtn.setPreferredSize(new Dimension(130, 40));
        addPictureBtn.setOpaque(true);
        addPictureBtn.setForeground(new Color(218, 165, 32));
        addPictureBtn.setFont(new Font("Verdana", Font.BOLD, 13));
        addPictureBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));

        JLabel lblInfoFit = new JLabel("Recommended size is (586 * 218) px");
        lblInfoFit.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        lblInfoFit.setForeground(new Color(255, 255, 153));
        lblInfoFit.setBounds(35, 294, 277, 16);
        upperPanel.add(lblInfoFit);

        bottomPanel = new JPanel();
        bottomPanel.setFont(new Font("Arial", Font.PLAIN, 16));
        bottomPanel.setBackground(Color.decode("#066d95"));
        getContentPane().add(bottomPanel, BorderLayout.CENTER);
        bottomPanel.setLayout(null);

        final JLabel lblName = new JLabel("Name : ");
        lblName.setHorizontalTextPosition(SwingConstants.CENTER);
        lblName.setHorizontalAlignment(SwingConstants.LEFT);
        lblName.setForeground(new Color(0, 0, 0));
        lblName.setFont(new Font("Arial", Font.PLAIN, 15));
        lblName.setBounds(31, 25, 119, 20);
        bottomPanel.add(lblName);

        final JLabel lblNewLabel = new JLabel("Owner Name : ");
        lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNewLabel.setBounds(31, 60, 119, 20);
        bottomPanel.add(lblNewLabel);

        final JLabel lblTelephone = new JLabel("Telephone : ");
        lblTelephone.setHorizontalTextPosition(SwingConstants.CENTER);
        lblTelephone.setHorizontalAlignment(SwingConstants.LEFT);
        lblTelephone.setForeground(new Color(0, 0, 0));
        lblTelephone.setFont(new Font("Arial", Font.PLAIN, 15));
        lblTelephone.setBounds(31, 98, 119, 20);
        bottomPanel.add(lblTelephone);

        final JLabel lblType = new JLabel("Type : ");
        lblType.setHorizontalTextPosition(SwingConstants.CENTER);
        lblType.setHorizontalAlignment(SwingConstants.LEFT);
        lblType.setForeground(new Color(0, 0, 0));
        lblType.setFont(new Font("Arial", Font.PLAIN, 15));
        lblType.setBounds(31, 173, 119, 20);
        bottomPanel.add(lblType);

        JLabel lblCountry = new JLabel("Country : ");
        lblCountry.setHorizontalTextPosition(SwingConstants.CENTER);
        lblCountry.setHorizontalAlignment(SwingConstants.LEFT);
        lblCountry.setForeground(new Color(0, 0, 0));
        lblCountry.setFont(new Font("Arial", Font.PLAIN, 15));
        lblCountry.setBounds(31, 135, 119, 20);
        bottomPanel.add(lblCountry);

        final JLabel lblCity = new JLabel("City : ");
        lblCity.setHorizontalTextPosition(SwingConstants.CENTER);
        lblCity.setHorizontalAlignment(SwingConstants.LEFT);
        lblCity.setForeground(new Color(0, 0, 0));
        lblCity.setFont(new Font("Arial", Font.PLAIN, 15));
        lblCity.setBounds(323, 171, 119, 20);
        bottomPanel.add(lblCity);

        final JLabel lblFullAddress = new JLabel("Full address : ");
        lblFullAddress.setHorizontalTextPosition(SwingConstants.CENTER);
        lblFullAddress.setHorizontalAlignment(SwingConstants.LEFT);
        lblFullAddress.setForeground(new Color(0, 0, 0));
        lblFullAddress.setFont(new Font("Arial", Font.PLAIN, 15));
        lblFullAddress.setBounds(41, 227, 109, 20);
        bottomPanel.add(lblFullAddress);

        nameField = new JTextField();
        nameField.addKeyListener(listenToKeysListener());
        nameField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        nameField.setBounds(153, 25, 149, 20);
        bottomPanel.add(nameField);
        nameField.setColumns(10);

        ownerNameField = new JTextField();
        ownerNameField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        ownerNameField.setBounds(153, 60, 149, 20);
        bottomPanel.add(ownerNameField);
        ownerNameField.setColumns(10);

        try {
            maskFormatter = new MaskFormatter("(###) ###-####");
            maskFormatter.setValidCharacters("()1234567890 ");
        } catch (ParseException e) {
            final InformationFrame dialog = new InformationFrame();
            dialog.setMessage("Couldn't set phone formatter right now,Please restart the current page.");
            dialog.setVisible(true);
        }
        phoneFrmtField = new JFormattedTextField(maskFormatter);
        phoneFrmtField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        phoneFrmtField.setBounds(153, 98, 149, 20);
        bottomPanel.add(phoneFrmtField);

        hotelTypeCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>(HOTEL_TYPES));
        hotelTypeCmbBox.setBounds(153, 171, 149, 20);
        bottomPanel.add(hotelTypeCmbBox);

        countryCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>(COUNTRY_LIST));
        countryCmbBox.setBounds(153, 133, 149, 20);
        bottomPanel.add(countryCmbBox);

        cityField = new JTextField();
        cityField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        cityField.setBounds(449, 171, 149, 20);
        bottomPanel.add(cityField);
        cityField.setColumns(10);

        final JLabel lblCapacity = new JLabel("Capacity : ");
        lblCapacity.setHorizontalTextPosition(SwingConstants.CENTER);
        lblCapacity.setHorizontalAlignment(SwingConstants.LEFT);
        lblCapacity.setForeground(Color.BLACK);
        lblCapacity.setFont(new Font("Arial", Font.PLAIN, 15));
        lblCapacity.setBounds(323, 25, 119, 20);
        bottomPanel.add(lblCapacity);

        final JLabel lblRoomTypesCount = new JLabel("Room type count : ");
        lblRoomTypesCount.setHorizontalTextPosition(SwingConstants.CENTER);
        lblRoomTypesCount.setHorizontalAlignment(SwingConstants.LEFT);
        lblRoomTypesCount.setForeground(Color.BLACK);
        lblRoomTypesCount.setFont(new Font("Arial", Font.PLAIN, 15));
        lblRoomTypesCount.setBounds(323, 60, 125, 20);
        bottomPanel.add(lblRoomTypesCount);

        roomTypeSpinner = new JSpinner();
        roomTypeSpinner.setModel(new SpinnerNumberModel(0, 0, 3, 1));
        roomTypeSpinner.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        roomTypeSpinner.setBounds(449, 60, 63, 20);
        roomTypeSpinner.addChangeListener(setTypeFields());
        typeValue = (int) roomTypeSpinner.getValue();
        bottomPanel.add(roomTypeSpinner);

        final JLabel lblStars = new JLabel("Stars : ");
        lblStars.setHorizontalTextPosition(SwingConstants.CENTER);
        lblStars.setHorizontalAlignment(SwingConstants.LEFT);
        lblStars.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblStars.setAutoscrolls(true);
        lblStars.setForeground(Color.BLACK);
        lblStars.setFont(new Font("Arial", Font.PLAIN, 15));
        lblStars.setBounds(323, 134, 119, 20);
        bottomPanel.add(lblStars);

        starsSpinner = new JSpinner();
        starsSpinner.setModel(new SpinnerNumberModel(0, 0, 5, 1));
        starsSpinner.addChangeListener(starsChangedListner());
        starsSpinner.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        starsSpinner.setBounds(449, 134, 63, 20);
        starValue = (int) starsSpinner.getValue();
        bottomPanel.add(starsSpinner);

        panel = new JPanel();
        panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel.setBounds(153, 212, 432, 43);
        bottomPanel.add(panel);
        panel.setLayout(new BorderLayout(0, 0));

        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        panel.add(scrollPane, BorderLayout.CENTER);

        fullAdressField = new JTextPane();
        fullAdressField.setFont(new Font("Dialog", Font.BOLD, 15));
        fullAdressField.setBorder(null);
        scrollPane.setViewportView(fullAdressField);

        buttonsPanel = new JPanel();
        buttonsPanel.setBounds(279, 275, 342, 49);
        buttonsPanel.setForeground(new Color(95, 158, 160));
        buttonsPanel.setBackground(Color.decode("#066d95"));
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        btnClear = new JButton("CLEAR");
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cleanAllFields();
            }
        });
        btnClear.setIcon(new ImageIcon(LoginWindow.class.getResource("/com/coder/hms/icons/login_clear.png")));
        btnClear.setForeground(new Color(220, 20, 60));
        btnClear.setOpaque(true);
        btnClear.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        btnClear.setPreferredSize(new Dimension(120, 40));
        btnClear.setFont(new Font("Verdana", Font.BOLD, 15));
        buttonsPanel.add(btnClear);

        btnLogin = new JButton("SAVE");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                saveHotelDetails();
            }
        });
        btnLogin.setIcon(new ImageIcon(HotelPropertiesWindow.class.getResource("/com/coder/hms/icons/reserv_save.png")));
        btnLogin.setForeground(new Color(0, 191, 255));
        btnLogin.setOpaque(true);
        btnLogin.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        btnLogin.setMnemonic(KeyEvent.VK_ENTER);
        btnLogin.setPreferredSize(new Dimension(120, 40));
        btnLogin.setFont(new Font("Verdana", Font.BOLD, 15));
        buttonsPanel.add(btnLogin);

        bottomPanel.add(buttonsPanel);

        capacitySpinner = new JSpinner();
        capacitySpinner.setModel(new SpinnerNumberModel(0, 0, 1000, 1));
        capacitySpinner.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        capacitySpinner.setBounds(449, 26, 63, 20);
        bottomPanel.add(capacitySpinner);

        setStars();
        setType();
        populateMainWindow();

        modalFrame = new JFrame();
        modalFrame.setAlwaysOnTop(true);

        setVisible(true);
    }

    private void populateMainWindow() {

        theHotel = hotelDaoImpl.getHotel();
        nameField.setText(theHotel.getName());
        hotelNameTitle.setText(theHotel.getName());
        starsSpinner.setValue(theHotel.getStarCount());
        if (convertStringToBufferedImage(theHotel.getPicture()) != null) {
            pictlabel.setIcon(new ImageIcon(convertStringToBufferedImage(theHotel.getPicture())));
        }
        capacitySpinner.setValue(theHotel.getRoomCapacity());
        ownerNameField.setText(theHotel.getOwner());
        roomTypeSpinner.setValue(theHotel.getRoomTypes().split("\\s").length);
        final String[] roomTypesAsString = theHotel.getRoomTypes().split("\\s");
        for (int x = 0; x < typeValue; x++) {
            roomTypeFields[x].setText(roomTypesAsString[x]);
        }
        phoneFrmtField.setValue(theHotel.getPhoneNumber());
        countryCmbBox.setSelectedItem(theHotel.getCountry());
        hotelTypeCmbBox.setSelectedItem(theHotel.getType());
        cityField.setText(theHotel.getCity());
        fullAdressField.setText(theHotel.getAddress());

        this.revalidate();
        this.repaint();
    }

    private void setType() {
        int locationPoint = 323;
        for (int i = 0; i < 3; i++) {

            roomTypes = new JTextField();
            roomTypes.setColumns(10);
            roomTypes.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
            roomTypes.setBounds(locationPoint, 97, 86, 20);
            roomTypes.setVisible(false);
            bottomPanel.add(roomTypes);

            roomTypeFields[i] = roomTypes;
            locationPoint = locationPoint + 96;
        }
    }

    private ChangeListener setTypeFields() {
        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int count = (int) roomTypeSpinner.getValue();
                if (count > typeValue) {
                    for (int i = 0; i < count; i++) {
                        roomTypeFields[i].setVisible(true);
                        roomTypeFields[i].revalidate();
                        roomTypeFields[i].repaint();
                    }
                } else if (count < typeValue) {

                    roomTypeFields[count].setVisible(false);
                    roomTypeFields[count].revalidate();
                    roomTypeFields[count].repaint();
                }
                typeValue = count;

            }
        };
        return changeListener;
    }

    private void setStars() {
        int locationPoint = 390;
        for (int i = 0; i < 5; i++) {
            stars = new JLabel("");
            stars.setHorizontalTextPosition(SwingConstants.CENTER);
            stars.setHorizontalAlignment(SwingConstants.CENTER);
            stars.setIcon(new ImageIcon(HotelPropertiesWindow.class.getResource("/com/coder/hms/icons/hotel_external_star.png")));
            stars.setBounds(locationPoint, 19, 46, 37);
            stars.setVisible(false);
            starHolder.add(stars);

            starlabels[i] = stars;
            locationPoint = locationPoint + 44;
        }
    }

    private ChangeListener starsChangedListner() {
        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int count = (int) starsSpinner.getValue();
                if (count > starValue) {
                    for (int i = 0; i < count; i++) {
                        starlabels[i].setVisible(true);
                        starlabels[i].revalidate();
                        starlabels[i].repaint();
                    }
                } else if (count < starValue) {

                    starlabels[count].setVisible(false);
                    starlabels[count].revalidate();
                    starlabels[count].repaint();
                }
                starValue = count;

            }
        };
        return changeListener;
    }

    private KeyListener listenToKeysListener() {
        final KeyAdapter adapter = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String word = nameField.getText();
                if (word.length() < 18) {
                    hotelNameTitle.setText(word);
                    hotelNameTitle.repaint();
                } else {
                    JOptionPane.showMessageDialog(modalFrame, "Hotel name maximum 18 charachter allowed!",
                            JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
                }
                super.keyTyped(e);
            }
        };
        return adapter;
    }

    private ActionListener addPictureListener() {
        final ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser sec = new JFileChooser();
                FileNameExtensionFilter JpegFilter = new FileNameExtensionFilter("JPG & GIF & PNG Images", "jpg", "gif", "png");
                sec.setFileFilter(JpegFilter);
                sec.setAcceptAllFileFilterUsed(false);
                sec.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int result = sec.showOpenDialog(sec);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {

                        newImage = ImageIO.read(sec.getSelectedFile());

                        final int pictureWidth = 586;
                        final int pictureHeight = 218;

                        BufferedImage hotelPicture = new BufferedImage(pictureWidth, pictureHeight, newImage.getType());
                        Graphics2D g = hotelPicture.createGraphics();
                        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                        g.drawImage(newImage, 0, 0, pictureHeight, pictureHeight, null);
                        g.dispose();

                        pictlabel.setIcon(new ImageIcon(hotelPicture));
                        pictlabel.revalidate();
                        pictlabel.repaint();

                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(modalFrame, "Image cannot be null !",
                                JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
                    }
                }

            }
        };
        return listener;
    }

    private String convertBufferedImageToString(BufferedImage image) {

        if (image != null) {

            try {

                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg", baos);
                byte[] imageByte = Base64.encodeBase64(baos.toByteArray());
                String base64encoded = new String(imageByte);

                return base64encoded;

            } catch (IOException e) {
                JOptionPane.showMessageDialog(modalFrame, "Image conversation error!\nPlease reopen this window.",
                        JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(modalFrame, "Image cannot be null!",
                    JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
        }

        return null;
    }

    private BufferedImage convertStringToBufferedImage(String image) {

        if (image != null) {
            final byte[] imageInByte = Base64.decodeBase64(image.getBytes());
            final InputStream in = new ByteArrayInputStream(imageInByte);
            BufferedImage bImageFromConvert = null;
            try {

                bImageFromConvert = ImageIO.read(in);

                return bImageFromConvert;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(modalFrame, "Image conversation error!\nPlease reopen this window.",
                        JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Image cannot be null!",
                    JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    private boolean checkAllFields() {
        boolean isFilled = false;
        countryCmbBox.getItemAt(countryCmbBox.getSelectedIndex());
        hotelTypeCmbBox.getItemAt(hotelTypeCmbBox.getSelectedIndex());
        capacityVal = (int) capacitySpinner.getValue();
        roomTypeVal = "";
        for (int i = 0; i < roomTypeFields.length; i++) {
            roomTypeVal += roomTypeFields[i].getText() + " ";
        }

        if (hotelNameTitle.getText().length() > 0
                && starValue != 0 && ownerNameField.getText().length() > 0
                && phoneFrmtField.getText().length() > 0
                && countryCmbBox.getItemAt(countryCmbBox.getSelectedIndex()).length() > 0
                && hotelTypeCmbBox.getItemAt(hotelTypeCmbBox.getSelectedIndex()).length() > 0
                && capacityVal != 0 && cityField.getText().length() > 0
                && roomTypeVal.length() > 0 && fullAdressField.getText().length() > 0
                && convertBufferedImageToString(newImage).length() > 0) {

            isFilled = true;
        }

        return isFilled;
    }

    private void saveHotelDetails() {

        if (checkAllFields()) {

            loggingEngine.setMessage("Hotel detail is before changing : " + theHotel.toString());

            theHotel.setName(hotelNameTitle.getText());
            theHotel.setStarCount(starValue);
            theHotel.setPicture(convertBufferedImageToString(newImage));
            theHotel.setOwner(ownerNameField.getText());
            theHotel.setPhoneNumber(phoneFrmtField.getText());
            theHotel.setCountry(countryCmbBox.getItemAt(countryCmbBox.getSelectedIndex()));
            theHotel.setType(hotelTypeCmbBox.getItemAt(hotelTypeCmbBox.getSelectedIndex()));
            theHotel.setRoomCapacity(capacityVal);
            theHotel.setRoomTypes(roomTypeVal);
            theHotel.setCity(cityField.getText());
            theHotel.setAddress(fullAdressField.getText());

            hotelDaoImpl.saveHotel(theHotel);
            JOptionPane.showMessageDialog(modalFrame, "Well done.\nYour hotel informations saved successfully!",
                    JOptionPane.MESSAGE_PROPERTY, JOptionPane.INFORMATION_MESSAGE);
            loggingEngine.setMessage("Hotel detail is after changing : " + theHotel.toString());
        } else {
            JOptionPane.showMessageDialog(modalFrame, "Please fill all the blanks as well!",
                    JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
            return;
        }

    }

    private void cleanAllFields() {

        nameField.setText(" ");
        hotelNameTitle.setText(" ");
        starsSpinner.setValue(0);
        pictlabel.setIcon(null);
        ownerNameField.setText(" ");
        phoneFrmtField.setValue(0);
        countryCmbBox.setSelectedIndex(0);
        for (int i = 0; i < roomTypeFields.length; i++) {
            roomTypeFields[i].setText(" ");
        }
        for (int j = 0; j < starlabels.length; j++) {
            starlabels[j].setVisible(false);
        }
        roomTypeSpinner.setValue(0);
        capacitySpinner.setValue(0);
        cityField.setText(" ");
        fullAdressField.setText(" ");

        this.revalidate();
        this.repaint();
    }
}
