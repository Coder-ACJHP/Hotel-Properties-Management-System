/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.ui.external;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

import com.apple.eawt.Application;
import com.coder.hms.beans.LocaleBean;
import com.coder.hms.beans.SessionBean;
import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.daoImpl.HotelDaoImpl;
import com.coder.hms.daoImpl.UserDaoImpl;
import com.coder.hms.ui.extras.LanguageCmbBox;
import com.coder.hms.ui.main.MainFrame;
import com.coder.hms.utils.ChangeComponentOrientation;
import com.coder.hms.utils.CustomKeyDispatcher;
import com.coder.hms.utils.LoggingEngine;
import com.coder.hms.utils.PropertiesReader;
import com.coder.hms.utils.ResourceControl;

/**
 * @author Coder ACJHP
 *
 */
public class LoginWindow extends JDialog {

    /**
     *
     */
    private final String newDate;
    private int clicked = 0;
    private final LocalDate today;
    private final DateTimeFormatter sdf;
    private ResourceBundle bundle;
    private static LocaleBean bean;
    private final JTextField userNameField;
    private final JPasswordField passwordField;
    private static LoggingEngine logging;
    private final LanguageCmbBox languagesCmbBox;
    private static SessionBean sessionBean;
    private ChangeComponentOrientation orientationChanger;
    private final JButton btnClear, btnLogin, setPasswordVisible, capslockBtn;
    private static final long serialVersionUID = 1L;
    private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";
    private final JLabel infoLabel, userNameLabel, passwordLabel, jumbotronLabel, lblResetYourPassword;

    // Set some basic properties
    public LoginWindow() {

        //inject main dialog to custom dialog.
        orientationChanger = new ChangeComponentOrientation();
        orientationChanger.setTheDialog(this);
        
        logging = LoggingEngine.getInstance();
        logging.setReady(DataSourceFactory.class.getName());
        logging.changeLoggingLevel(Level.FINE);
        logging.setConsoleLogging(false);

        bean = LocaleBean.getInstance();
        bean.setLocale(getLocale());
        sessionBean = SessionBean.getSESSION_BEAN();
        
        //set upper icon for dialog frame
        String opSystem = System.getProperty("os.name").toLowerCase();

        if (opSystem.contains("windows") || opSystem.contains("nux")) {

            this.setIconImage(Toolkit.getDefaultToolkit().
                    getImage(LoginWindow.class.getResource(LOGOPATH)));
        } else {
            Application.getApplication().setDockIconImage(new ImageIcon(getClass().getResource(LOGOPATH)).getImage());
        }

        today = LocalDate.now();
        sdf = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(bean.getLocale());
        newDate = sdf.format(today);

        getContentPane().setForeground(new Color(255, 99, 71));
        getContentPane().setFocusCycleRoot(true);
        getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        getContentPane().setFont(new Font("Monospaced", Font.BOLD, 15));
        setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        setModal(true);
        setResizable(false);

        this.setTitle("Coder HPMSA - [Login] - (" + newDate + ")");
        SessionBean.setDate(newDate);

        /* Set default size of frame */
        this.setSize(428, 262);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.decode("#066d95"));
        getContentPane().setLayout(null);

        userNameLabel = new JLabel("User name : ");
        userNameLabel.setForeground(new Color(255, 255, 255));
        userNameLabel.setBounds(29, 79, 113, 18);
        userNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        userNameLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        userNameLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 16));
        getContentPane().add(userNameLabel);

        userNameField = new JTextField();
        userNameField.setToolTipText("Enter your user name");
        userNameField.setLocation(143, 74);
        userNameField.setBackground(new Color(255, 255, 255));
        userNameField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        userNameField.setMinimumSize(new Dimension(10, 40));
        userNameField.setSize(new Dimension(218, 31));
        userNameField.setPreferredSize(new Dimension(10, 44));
        userNameField.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
        userNameField.setEditable(true);
        userNameLabel.setLabelFor(userNameField);
        getContentPane().add(userNameField);
        userNameField.setColumns(10);

        passwordLabel = new JLabel("Password : ");
        passwordLabel.setForeground(new Color(255, 255, 255));
        passwordLabel.setBounds(30, 122, 113, 18);
        passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        passwordLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        passwordLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 16));
        getContentPane().add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setToolTipText("Enter your password");
        passwordField.setLocation(143, 118);
        passwordField.setBackground(new Color(255, 255, 255));
        passwordField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        passwordField.setMinimumSize(new Dimension(10, 40));
        passwordField.setSize(new Dimension(218, 29));
        passwordField.setPreferredSize(new Dimension(10, 44));
        passwordField.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
        passwordLabel.setLabelFor(passwordField);
        getContentPane().add(passwordField);

        setPasswordVisible = new JButton("");
        setPasswordVisible.setHorizontalTextPosition(SwingConstants.CENTER);
        setPasswordVisible.setToolTipText("Make password visible");
        setPasswordVisible.setOpaque(false);
        setPasswordVisible.setIcon(new ImageIcon(ChangePasswordWindow.class
                .getResource("/com/coder/hms/icons/login_show_pwd.png")));
        setPasswordVisible.setPreferredSize(new Dimension(16, 16));
        setPasswordVisible.setMaximumSize(new Dimension(16, 16));
        setPasswordVisible.setMinimumSize(new Dimension(16, 16));
        setPasswordVisible.setBounds(373, 118, 16, 16);
        setPasswordVisible.addMouseListener(setVisible());
        getContentPane().add(setPasswordVisible);

        //add listener for both fields to clear info label when key typed
        keyListenerForFields(userNameField, passwordField);

        /* 
		*	need to new panel for adding our buttons
         */
        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBounds(112, 161, 277, 49);
        buttonsPanel.setForeground(new Color(95, 158, 160));
        buttonsPanel.setBackground(Color.decode("#066d95"));
        getContentPane().add(buttonsPanel);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        btnClear = new JButton("CLEAR");
        btnClear.setIcon(new ImageIcon(LoginWindow.class.getResource("/com/coder/hms/icons/login_clear.png")));
        btnClear.setForeground(new Color(220, 20, 60));
        btnClear.setOpaque(true);
        btnClear.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        btnClear.setPreferredSize(new Dimension(125, 40));
        btnClear.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
        mouseListenerForButtons(btnClear);
        actionListenerForButtons(btnClear);
        buttonsPanel.add(btnClear);

        btnLogin = new JButton("LOGIN");
        btnLogin.setToolTipText("ENTER for shortcut");
        btnLogin.setSelectedIcon(null);
        btnLogin.setIcon(new ImageIcon(LoginWindow.class.getResource("/com/coder/hms/icons/login_key.png")));
        btnLogin.setForeground(new Color(0, 191, 255));
        btnLogin.setOpaque(true);
        btnLogin.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        btnLogin.setMnemonic(KeyEvent.VK_ENTER);
        btnLogin.setPreferredSize(new Dimension(125, 40));
        btnLogin.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
        mouseListenerForButtons(btnLogin);
        actionListenerForButtons(btnLogin);
        buttonsPanel.add(btnLogin);

        //set default button
        getRootPane().setDefaultButton(btnLogin);
        btnLogin.requestFocus();

        //header label
        jumbotronLabel = new JLabel("CODER HOTEL P. M. S. A.");
        jumbotronLabel.setForeground(new Color(255, 255, 255));
        jumbotronLabel.setBorder(new MatteBorder(1, 1, 1, 1, new Color(70, 130, 180)));
        jumbotronLabel.setBackground(new Color(135, 206, 235));
        jumbotronLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        jumbotronLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jumbotronLabel.setOpaque(true);
        jumbotronLabel.setBounds(10, 13, 351, 47);
        getContentPane().add(jumbotronLabel);

        //information label
        infoLabel = new JLabel("");
        infoLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        infoLabel.setForeground(new Color(220, 20, 60));
        infoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        infoLabel.setAutoscrolls(true);
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoLabel.setFont(new Font("Consolas", infoLabel.getFont().getStyle() | Font.BOLD, 15));
        infoLabel.setBounds(1, 213, 428, 24);
        getContentPane().add(infoLabel);

        lblResetYourPassword = new JLabel("reset your password");
        lblResetYourPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lblResetYourPassword.setForeground(Color.decode("#20f90c"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblResetYourPassword.setForeground(Color.BLACK);
                super.mouseExited(e);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        new SendEmailWindow();

                    }
                });
            }
        });

        lblResetYourPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblResetYourPassword.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
        lblResetYourPassword.setBounds(241, 151, 134, 9);
        getContentPane().add(lblResetYourPassword);

        capslockBtn = new JButton("");
        capslockBtn.setIcon(new ImageIcon(LoginWindow.class.getResource("/com/coder/hms/icons/login_capslock.png")));
        capslockBtn.setToolTipText("CAPS_LOCK status.");
        capslockBtn.setPreferredSize(new Dimension(16, 16));
        capslockBtn.setOpaque(false);
        capslockBtn.setMinimumSize(new Dimension(16, 16));
        capslockBtn.setMaximumSize(new Dimension(16, 16));
        capslockBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        capslockBtn.setBounds(373, 136, 16, 16);
        getContentPane().add(capslockBtn);

        languagesCmbBox = new LanguageCmbBox();
        languagesCmbBox.setBounds(370, 38, 40, 20);
        languagesCmbBox.addActionToLanguageBox(getActionOfLangBox());
        getContentPane().add(languagesCmbBox);

        JLabel iconLabel = new JLabel("");
        iconLabel.setIcon(new ImageIcon(LoginWindow.class.getResource("/com/coder/hms/icons/languages.png")));
        iconLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setBounds(370, 13, 39, 24);
        getContentPane().add(iconLabel);

        final KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        final CustomKeyDispatcher dispatcher = new CustomKeyDispatcher(this.capslockBtn);
        manager.addKeyEventDispatcher(dispatcher);

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                new DataSourceFactory().shutDown();
                System.exit(0);
                super.windowClosing(e);
            }
        });

        changeLanguage(getLocale());

        setAlwaysOnTop(false);
        setVisible(true);

    }

    private void changeLanguage(Locale locale) {

        try {

            bundle = ResourceBundle.getBundle("com/coder/hms/languageFiles/LocalizationBundle", locale, new ResourceControl());
            this.setTitle(bundle.getString("MainTitle") + " (" + newDate + ")");
            this.btnClear.setText(bundle.getString("Clear"));
            this.btnLogin.setText(bundle.getString("Login"));
            this.lblResetYourPassword.setText(bundle.getString("ResetPwd"));
            this.userNameLabel.setText(bundle.getString("UserName"));
            this.passwordLabel.setText(bundle.getString("Password"));
            this.revalidate();
            this.repaint();

        } catch (MissingResourceException ex) {

            final InformationFrame dialog = new InformationFrame();
            dialog.setMessage("Cannot find translation files, application will continue with default language.");
            dialog.setVisible(true);

            logging.setMessage(ex.getMessage());
        }
    }

    private ActionListener getActionOfLangBox() {
        final ActionListener listener = (ActionEvent e) -> {
            Integer selectedInt = languagesCmbBox.comboBox.getSelectedIndex();
            
            Locale currentLocale;
            switch (selectedInt) {
                case 0:
                    currentLocale = new Locale("en", "US");
                    bean.setLocale(currentLocale);
                    setLocale(currentLocale);
                    orientationChanger.changeOrientationOfJDialogToLeft();
                    break;
                case 1:
                    currentLocale = new Locale("ar", "IQ");
                    bean.setLocale(currentLocale);
                    setLocale(currentLocale);
                    orientationChanger.changeOrientationOfJDialogToRight();
                    break;
                case 2:
                    currentLocale = new Locale("es", "ES");
                    bean.setLocale(currentLocale);
                    setLocale(currentLocale);
                    orientationChanger.changeOrientationOfJDialogToLeft();
                    break;
                case 3:
                    currentLocale = new Locale("tr", "TR");
                    bean.setLocale(currentLocale);
                    setLocale(currentLocale);
                    orientationChanger.changeOrientationOfJDialogToLeft();
                    break;
                    
                default:
                    break;
            }
            
            try {
                
                changeLanguage(bean.getLocale());
                
            } catch (MissingResourceException ex) {
                
                final InformationFrame dialog = new InformationFrame();
                dialog.setMessage("Cannot find translation files, application will continue with default language.");
                dialog.setVisible(true);
                
                logging.setMessage(ex.getMessage());
            }
            
            logging.setMessage("Language changed.");
        };
        return listener;
    }

    public void mouseListenerForButtons(final JButton jButton) {

        //change opaque property for making button color changeable
        jButton.setOpaque(true);
        //add listener for buttons when mouse hover, active.
        final MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jButton.setBackground(Color.decode("#8be9fd"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jButton.setBackground(getBackground());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                jButton.setBackground(Color.decode("#0fd0f9"));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                jButton.setBackground(Color.decode("#0fd0f9"));
            }
        };
        //set the button mouse listener this listener
        jButton.addMouseListener(ma);
    }

    public void keyListenerForFields(JTextField textField, JPasswordField passwordField) {

        KeyAdapter myAdapter = new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {

                infoLabel.setText(null);
                super.keyTyped(e);
            }
        };

        textField.addKeyListener(myAdapter);
        passwordField.addKeyListener(myAdapter);

    }

    public void actionListenerForButtons(final JButton jButton) {

        final ActionListener myListner = (ActionEvent e) -> {
            final String userName = userNameField.getText().toLowerCase();
            final String userPswrd = String.valueOf(passwordField.getPassword());
            
            if (e.getSource() == btnLogin) {
                
                if (userName.trim().length() > 0 && userPswrd.trim().length() > 0) {
                    
                    boolean check = false;
                    
                    if (userName.equalsIgnoreCase("System")) {
                        final PropertiesReader checker = new PropertiesReader();
                        check = checker.checkIsAdministrator(userName, userPswrd);
                        sessionBean.setHotelName(checker.getHotelName());
                        
                    } else {
                        
                        final UserDaoImpl userDaoImpl = new UserDaoImpl();
                        check = userDaoImpl.authentication(userName, userPswrd);
                        final HotelDaoImpl daoImpl = new HotelDaoImpl();
                        sessionBean.setHotelName(daoImpl.getHotel().getName());
                        
                    }
                    
                    doLogin(userName, userPswrd, check);
                    
                } else {
                    //change label font color and warn the user.
                    infoLabel.setForeground(new Color(220, 20, 60));
                    infoLabel.setText("INFO : All fields required !");
                }

            } else if (e.getSource() == btnClear) {
                // clear all fields
                userNameField.setText(null);
                passwordField.setText(null);
                infoLabel.setForeground(new Color(135, 206, 235));
                infoLabel.setText("INFO : All fields cleared.");
                
            }
        };

        jButton.addActionListener(myListner);
    }

	private void doLogin(final String userName, final String userPswrd, boolean check) {
		if (check == true) {
		    //store informations in bean to use it in another frames.
		    sessionBean.setNickName(userName);
		    sessionBean.setPassword(userPswrd);
		    
		    logging.setMessage("User is : " + sessionBean.getNickName());
		    //close this frame
		    dispose();
		    //open main application frame
		    new MainFrame();
		    
		} else {
		    infoLabel.setForeground(new Color(220, 20, 60));
		    infoLabel.setText("INFO :Username and password doesn't match !!");
		}
	}

    private MouseListener setVisible() {
        final MouseAdapter listener = new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                if (clicked != 0) {
                    passwordField.setEchoChar('\u25CF');
                    clicked = 0;
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (clicked == 0) {
                    passwordField.setEchoChar((char) 0);
                    clicked++;
                }
            }
        };
        return listener;
    }
}
