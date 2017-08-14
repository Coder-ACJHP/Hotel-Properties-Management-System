package com.coder.hms.ui.external;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.coder.hms.utils.ApplicationLogoSetter;

public class LicenseWindow extends JDialog {

	/**
	 * 
	 */
	private JTextPane editorPane;
	private static final long serialVersionUID = 1L;
	private final ApplicationLogoSetter logoSetter = new ApplicationLogoSetter();
	private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";

	/**
	 * Create the frame.
	 */
	public LicenseWindow() {
		
		setTitle("Coder HMS - [License]");
		setBounds(100, 100, 510, 550);
		setBackground(Color.decode("#066d95"));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		logoSetter.setApplicationLogoJDialog(this, LOGOPATH);
		
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		editorPane = new JTextPane();
		editorPane.setBackground(new Color(255, 255, 204));
		editorPane.setFont(new Font("Verdana", Font.PLAIN, 13));
		editorPane.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		editorPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		editorPane.setEditable(false);
		scrollPane.setViewportView(editorPane);
		
		final StyledDocument doc = editorPane.getStyledDocument();
		final SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		fillEditorPane();
		setVisible(true);
	}

	private void fillEditorPane() {
		File file = new File("src/com/coder/hms/icons/LICENSE.txt");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			editorPane.read(br, Charset.forName("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), JOptionPane.MESSAGE_PROPERTY, JOptionPane.ERROR_MESSAGE);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), JOptionPane.MESSAGE_PROPERTY, JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), JOptionPane.MESSAGE_PROPERTY, JOptionPane.ERROR_MESSAGE);
		}
	}
}
