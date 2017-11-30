/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coder.hms.ui.external;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import com.coder.hms.daoImpl.CompanyDaoImpl;
import com.coder.hms.entities.Company;
import com.coder.hms.utils.EmailValidator;

/**
 *
 * @author dell-pc
 */
public class CompanyDetails extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Creates new form CompanyDetails
     */
    private boolean isValid;
    private Color panelColor;
    private boolean isFinished;
    private JTextArea addresField;
    private JScrollPane jScrollPane1;
    private JCheckBox activateChkBox;
    private EmailValidator emailValidator;
    private JFormattedTextField phoneField;
    private JPanel buttonHolder, colorPanel, mainPanel;
    private JButton cancelBtn, pickColorBtn, saveBtn;
    private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";
    private JTextField districtField, emailField, idField, provinceField, titleField, taxAdminField, taxNumField, kindField;
    private JLabel infoLabel, idNumLbl, addressLbl, colorLbl, phoneLbl, kindLbl, titleLbl, taxAdminLbl,taxNumlbl,
    provinceLbl, districLbl, activateStsLbl, emailLbl, markerLbl, rgbColorLbl;


    
    public CompanyDetails() {
    	
        initComponents();
    }

    private void initComponents() {

        mainPanel = new JPanel();
        buttonHolder = new JPanel();
        cancelBtn = new JButton();
        saveBtn = new JButton();
        idNumLbl = new JLabel();
        kindLbl = new JLabel();
        titleLbl = new JLabel();
        taxAdminLbl = new JLabel();
        taxNumlbl = new JLabel();
        provinceLbl = new JLabel();
        districLbl = new JLabel();
        idField = new JTextField();
        kindField = new JTextField();
        titleField = new JTextField();
        taxAdminField = new JTextField();
        taxNumField = new JTextField();
        provinceField = new JTextField();
        districtField = new JTextField();
        activateStsLbl = new JLabel();
        emailLbl = new JLabel();
        emailField = new JTextField();
        addressLbl = new JLabel();
        jScrollPane1 = new JScrollPane();
        addresField = new JTextArea();
        colorLbl = new JLabel();
        colorPanel = new JPanel();
        pickColorBtn = new JButton();
        markerLbl = new JLabel();
        infoLabel = new JLabel();
        infoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        infoLabel.setLocation(new Point(50, 50));
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoLabel.setSize(new Dimension(200, 50));
        infoLabel.setMinimumSize(new Dimension(200, 50));
        infoLabel.setPreferredSize(new Dimension(200, 50));
        phoneLbl = new JLabel();
        activateChkBox = new JCheckBox();
        phoneField = new JFormattedTextField();

        setBackground(new Color(6, 109, 149));
        setTitle("Coder HPMSA - [Company Details]");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(LOGOPATH)));
        setPreferredSize(new Dimension(695, 405));
        setResizable(false);
    	setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    	setLocationRelativeTo(null);
    	final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screenSize.width / 2 - 150, screenSize.height / 2 - 75);
		
        mainPanel.setBackground(new java.awt.Color(6, 109, 149));

        buttonHolder.setBackground(new java.awt.Color(6, 109, 149));

        cancelBtn.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        cancelBtn.setForeground(new java.awt.Color(220, 20, 60));
        cancelBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/coder/hms/icons/login_clear.png"))); // NOI18N
        cancelBtn.setText("Cancel");
        cancelBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cancelBtn.setPreferredSize(new java.awt.Dimension(130, 40));
        cancelBtn.addActionListener(ActionListener->{
        	cancelBtnActionPerformed();
        });
        buttonHolder.add(cancelBtn);

        saveBtn.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        saveBtn.setForeground(new java.awt.Color(0, 191, 255));
        saveBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/coder/hms/icons/reserv_save.png"))); // NOI18N
        saveBtn.setText("Save");
        saveBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        saveBtn.setPreferredSize(new java.awt.Dimension(130, 40));
        saveBtn.addActionListener(ActionListener-> {
                saveBtnActionPerformed();
        });
        buttonHolder.add(saveBtn);

        idNumLbl.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        idNumLbl.setText("Id number : ");

        kindLbl.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        kindLbl.setText("Kind : ");

        titleLbl.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        titleLbl.setText("Title : ");

        taxAdminLbl.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        taxAdminLbl.setText("Tax administrator : ");

        taxNumlbl.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        taxNumlbl.setText("Tax number : ");

        provinceLbl.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        provinceLbl.setText("Province : ");

        districLbl.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        districLbl.setText("District : ");

        idField.setEditable(false);
        idField.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        idField.setPreferredSize(new java.awt.Dimension(150, 20));


        kindField.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        kindField.setPreferredSize(new java.awt.Dimension(150, 20));


        titleField.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        titleField.setPreferredSize(new java.awt.Dimension(150, 20));


        taxAdminField.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        taxAdminField.setPreferredSize(new java.awt.Dimension(150, 20));


        taxNumField.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        taxNumField.setPreferredSize(new java.awt.Dimension(150, 20));


        provinceField.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        provinceField.setPreferredSize(new java.awt.Dimension(150, 20));


        districtField.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        districtField.setPreferredSize(new java.awt.Dimension(150, 20));


        activateStsLbl.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        activateStsLbl.setText("Activate status : ");

        emailLbl.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        emailLbl.setText("Email : ");

        emailField.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        emailField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
			public void keyTyped(java.awt.event.KeyEvent evt) {
                emailFieldKeyTyped(evt);
            }
        });

        addressLbl.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        addressLbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        addressLbl.setText("Address : ");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        addresField.setColumns(20);
        addresField.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        addresField.setLineWrap(true);
        addresField.setRows(5);
        jScrollPane1.setViewportView(addresField);

        colorLbl.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        colorLbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        colorLbl.setText("Color : ");

        colorPanel.setBackground(new java.awt.Color(255, 255, 255));
        colorPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        GroupLayout colorPanelLayout = new GroupLayout(colorPanel);
        colorPanel.setLayout(colorPanelLayout);
        colorPanelLayout.setHorizontalGroup(
            colorPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        colorPanelLayout.setVerticalGroup(
            colorPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        pickColorBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/coder/hms/icons/rezaerv_report.png"))); // NOI18N
        pickColorBtn.setText("Pick color");
        pickColorBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pickColorBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                pickColorBtnActionPerformed(evt);
            }
        });

        markerLbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        markerLbl.setForeground(new java.awt.Color(255, 0, 0));
        markerLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        markerLbl.setText("*");

        infoLabel.setFont(new Font("Verdana", Font.BOLD, 20)); // NOI18N
        infoLabel.setForeground(new java.awt.Color(102, 255, 0));
        infoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        phoneLbl.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        phoneLbl.setText("Phone : ");

        activateChkBox.setHorizontalAlignment(SwingConstants.CENTER);
        activateChkBox.setHorizontalTextPosition(SwingConstants.CENTER);

        try {
            phoneField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("(###) ###-####")));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
        rgbColorLbl = new JLabel("");
        rgbColorLbl.setBorder(new LineBorder(new Color(0, 0, 0)));
        rgbColorLbl.setHorizontalTextPosition(SwingConstants.CENTER);
        rgbColorLbl.setPreferredSize(new Dimension(170, 20));
        rgbColorLbl.setForeground(Color.WHITE);
        rgbColorLbl.setHorizontalAlignment(SwingConstants.CENTER);

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanelLayout.setHorizontalGroup(
        	mainPanelLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(mainPanelLayout.createSequentialGroup()
        			.addGap(30)
        			.addGroup(mainPanelLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(mainPanelLayout.createSequentialGroup()
        					.addGroup(mainPanelLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(idNumLbl, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
        						.addComponent(titleLbl, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
        						.addComponent(taxAdminLbl, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
        						.addComponent(taxNumlbl, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
        						.addGroup(mainPanelLayout.createSequentialGroup()
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(districLbl, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
        						.addComponent(provinceLbl, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
        						.addComponent(kindLbl, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
        					.addGap(18)
        					.addGroup(mainPanelLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(idField, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
        						.addComponent(kindField, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
        						.addComponent(titleField, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
        						.addComponent(taxAdminField, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
        						.addComponent(taxNumField, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
        						.addComponent(districtField, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
        						.addComponent(provinceField, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
        					.addGroup(mainPanelLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(mainPanelLayout.createSequentialGroup()
        							.addGap(30)
        							.addComponent(phoneLbl, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
        							.addGap(10)
        							.addComponent(phoneField, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
        						.addGroup(mainPanelLayout.createSequentialGroup()
        							.addGap(30)
        							.addComponent(addressLbl, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
        							.addGap(10)
        							.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
        						.addGroup(mainPanelLayout.createSequentialGroup()
        							.addGroup(mainPanelLayout.createParallelGroup(Alignment.LEADING)
        								.addGroup(mainPanelLayout.createSequentialGroup()
        									.addPreferredGap(ComponentPlacement.RELATED)
        									.addComponent(buttonHolder, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE))
        								.addGroup(mainPanelLayout.createSequentialGroup()
        									.addGap(30)
        									.addGroup(mainPanelLayout.createParallelGroup(Alignment.LEADING)
        										.addComponent(infoLabel, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
        										.addGroup(mainPanelLayout.createSequentialGroup()
        											.addComponent(colorLbl, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
        											.addGroup(mainPanelLayout.createParallelGroup(Alignment.LEADING)
        												.addGroup(mainPanelLayout.createSequentialGroup()
        													.addGap(10)
        													.addComponent(colorPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        													.addGap(10)
        													.addComponent(pickColorBtn, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
        												.addGroup(mainPanelLayout.createSequentialGroup()
        													.addPreferredGap(ComponentPlacement.RELATED)
        													.addComponent(rgbColorLbl, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)))))))
        							.addGap(276))
        						.addGroup(mainPanelLayout.createSequentialGroup()
        							.addGap(30)
        							.addComponent(emailLbl, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
        							.addGap(10)
        							.addComponent(emailField, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(markerLbl, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
        					.addContainerGap())
        				.addGroup(mainPanelLayout.createSequentialGroup()
        					.addComponent(activateStsLbl, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
        					.addGap(10)
        					.addComponent(activateChkBox, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
        					.addGap(493))))
        );
        mainPanelLayout.setVerticalGroup(
        	mainPanelLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(mainPanelLayout.createSequentialGroup()
        			.addGroup(mainPanelLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(mainPanelLayout.createSequentialGroup()
        					.addGap(20)
        					.addGroup(mainPanelLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(idNumLbl, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
        						.addComponent(idField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        						.addComponent(phoneLbl, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
        						.addComponent(phoneField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        					.addGap(10)
        					.addGroup(mainPanelLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(kindLbl, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
        						.addComponent(kindField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        						.addComponent(emailLbl, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
        						.addGroup(mainPanelLayout.createParallelGroup(Alignment.TRAILING, false)
        							.addGroup(Alignment.LEADING, mainPanelLayout.createSequentialGroup()
        								.addGap(2)
        								.addComponent(markerLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        							.addComponent(emailField, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
        					.addGap(8)
        					.addGroup(mainPanelLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(mainPanelLayout.createSequentialGroup()
        							.addComponent(titleLbl, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
        							.addGap(13)
        							.addComponent(taxAdminLbl, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        						.addGroup(mainPanelLayout.createSequentialGroup()
        							.addComponent(titleField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        							.addGap(10)
        							.addComponent(taxAdminField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        						.addGroup(mainPanelLayout.createSequentialGroup()
        							.addGap(10)
        							.addComponent(addressLbl, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
        						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
        					.addGroup(mainPanelLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(colorLbl, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
        						.addGroup(mainPanelLayout.createSequentialGroup()
        							.addGap(10)
        							.addGroup(mainPanelLayout.createParallelGroup(Alignment.LEADING)
        								.addComponent(taxNumlbl, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        								.addComponent(taxNumField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
        						.addGroup(mainPanelLayout.createSequentialGroup()
        							.addGroup(mainPanelLayout.createParallelGroup(Alignment.LEADING)
        								.addComponent(colorPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(pickColorBtn, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(rgbColorLbl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(infoLabel, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
        				.addGroup(mainPanelLayout.createSequentialGroup()
        					.addGap(219)
        					.addGroup(mainPanelLayout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(districtField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        						.addComponent(districLbl, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(mainPanelLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(provinceLbl, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
        						.addComponent(provinceField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))))
        			.addGap(18)
        			.addGroup(mainPanelLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(buttonHolder, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
        				.addComponent(activateStsLbl, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
        				.addComponent(activateChkBox, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {idField, kindField, titleField, taxAdminField, taxNumField, provinceField, districtField});
        mainPanel.setLayout(mainPanelLayout);

        GroupLayout layout = new GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, 693, Short.MAX_VALUE)
        			.addGap(2))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, 380, Short.MAX_VALUE)
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);
        pack();
    }
    
    private void pickColorBtnActionPerformed(ActionEvent evt) {
       panelColor = JColorChooser.showDialog( CompanyDetails.this,
                     "Choose Company Color", colorPanel.getBackground());
        if(panelColor != null) {
            colorPanel.setBackground(panelColor);
            rgbColorLbl.setText("["+panelColor.getRed()+","+panelColor.getGreen()+","+panelColor.getBlue()+"]");
        }
    }

    private void emailFieldKeyTyped(KeyEvent evt) {

        emailValidator = new EmailValidator();
        if (emailValidator.validate(emailField.getText())) {
            markerLbl.setVisible(false);
            infoLabel.setText("");
            isValid = true;
        } else {
            markerLbl.setVisible(true);
            infoLabel.setForeground(Color.RED);
            infoLabel.setText("Invalid email format!");
        }

    }
    
    private void saveBtnActionPerformed() {
        if(isValid) {
            
        	final Company theCompany = new Company();
        	theCompany.setKind(kindField.getText());
        	theCompany.setTitle(titleField.getText());
        	theCompany.setTaxAdministration(taxAdminField.getText());
        	theCompany.setTaxNumber(taxNumField.getText());
        	theCompany.setDistrict(districtField.getText());
        	theCompany.setProvince(provinceField.getText());
        	theCompany.setEmail(emailField.getText());
        	theCompany.setAddress(addresField.getText());
        	theCompany.setPhoneNumber(phoneField.getText());
        	theCompany.setActivateStatus(activateChkBox.isSelected());
        	theCompany.setFaceColor(panelColor.getRGB());
        	
        	final CompanyDaoImpl daoImpl = new CompanyDaoImpl();
        	
        	if(daoImpl.saveCompany(theCompany)) {
        		infoLabel.setText("<html>SUCCESSFULLY ACCOMPLISHED</html>");
        		infoLabel.setForeground(Color.decode("#00FF00"));
        		isFinished = true;
        	} else {
        		infoLabel.setText("<html>OPERTION IS FAILED!</html>");
        		infoLabel.setForeground(Color.decode("#cd2626"));
        		isFinished = false;
			}
        } 
    }

    private void cancelBtnActionPerformed() {
    	this.dispose();
    }

    public void setInfoLabelText(String infotext) {
        infoLabel.setText(infotext);
    }

    public void setAddresField(String address) {
        this.addresField.setText(address);
    }
    
    public void setDistrictField(String district) {
        this.districtField.setText(district);
    }

    public void setEmailField(String email) {
        this.emailField.setText(email);
    }

    public void setIdFieldText(String Id) {
        this.idField.setText(Id);
    }

    public void setKindField(String kind) {
        this.kindField.setText(kind);
    }

    public void setPhoneNumField(String phoneNumber) {
        this.phoneField.setText(phoneNumber);
    }

    public void setTaxAdminField(String taxAdmin) {
        this.taxAdminField.setText(taxAdmin);
    }
    
    public void setTaxNumField(String taxNum) {
        this.taxNumField.setText(taxNum);
    }

    public void setProvinceField(String province) {
        this.provinceField.setText(province);
    }

    public void setTitleField(String title) {
        this.titleField.setText(title);
    }
    
    public void setActivateSts(boolean status) {
        this.activateChkBox.setSelected(status);
    }
    
    public void setFacesColor(Color color) {
    	this.panelColor = color;
    	this.colorPanel.setBackground(color);
    	this.rgbColorLbl.setText("["+color.getRed()+","+color.getGreen()+","+color.getBlue()+"]");
    }
    
    public boolean checkIsFinished() {
    	return this.isFinished;
    }
}
