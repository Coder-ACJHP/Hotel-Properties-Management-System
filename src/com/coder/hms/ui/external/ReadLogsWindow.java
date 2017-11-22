package com.coder.hms.ui.external;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.coder.hms.ui.extras.LogRecordsListRenderer;

public class ReadLogsWindow extends JDialog {

	/**
	 * 
	 */
	private File file;
	private JTextPane editorPane;
	private String selectedItem = "";
	private static final long serialVersionUID = 1L;
	private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";

	/**
	 * Create the frame.
	 */
	public ReadLogsWindow() {
		
		setTitle("Coder HPMSA - [Read Logs]");
		setBounds(100, 100, 660, 550);
		setBackground(Color.decode("#066d95"));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		this.setIconImage(Toolkit.getDefaultToolkit().
				getImage(getClass().getResource(LOGOPATH)));
		
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		editorPane = new JTextPane();
		editorPane.setBackground(new Color(255, 255, 240));
		editorPane.setFont(new Font("Verdana", Font.PLAIN, 13));
		editorPane.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		editorPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		editorPane.setEditable(false);
		scrollPane.setViewportView(editorPane);
		
		final JPanel filesPanel = new JPanel();
		filesPanel.setPreferredSize(new Dimension(200, 10));
		getContentPane().add(filesPanel, BorderLayout.EAST);
		filesPanel.setLayout(new BorderLayout(0, 0));
		
		final JScrollPane listScrollPane = new JScrollPane();
		listScrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listScrollPane.setViewportView(logFilesList());
		filesPanel.add(listScrollPane, BorderLayout.CENTER);
		
		final JPanel titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(10, 40));
		titlePanel.setBackground(Color.decode("#066d95"));
		titlePanel.setAutoscrolls(true);
		getContentPane().add(titlePanel, BorderLayout.NORTH);
		titlePanel.setLayout(new BorderLayout(0, 0));
		
		final JLabel lblTitle = new JLabel("SYSTEM LOG RECORDS");
		lblTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setAutoscrolls(true);
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 25));
		lblTitle.setForeground(UIManager.getColor("Button.highlight"));
		titlePanel.add(lblTitle, BorderLayout.CENTER);
		
		final StyledDocument doc = editorPane.getStyledDocument();
		final SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		setVisible(true);
	}

	private JList<String> logFilesList() {
		
		
		file = new File(System.getProperty("user.dir") + File.separator + "Logging Store/");
		
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(file);
		fileChooser.setMultiSelectionEnabled(true);

		final DefaultListModel<String> model = new DefaultListModel<String>();
		
		final JList<String> list = new JList<String>(model);
		list.setPreferredSize(new Dimension(85, 480));
		list.setFont(new Font("Dialog", Font.PLAIN, 12));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.setFixedCellHeight(18);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					selectedItem = list.getSelectedValue();
					getSelectedFileName();
				}
			}
		});
		
		list.setCellRenderer(new LogRecordsListRenderer());

		File[] selectedFiles = fileChooser.getCurrentDirectory().listFiles();

		for (File f : selectedFiles) {
			if (f.getName().indexOf(".log") != -1) {
				model.addElement(f.getName());
			}else {
				continue;
			}
		}
		if(model.isEmpty()) {
			model.addElement("List is empty!");
		}
		
		return list;
	}
	
	public void getSelectedFileName() {

		BufferedReader br;
		try {
			String getFileFrom = file.getAbsolutePath() + File.separator + selectedItem;
			br = new BufferedReader(new FileReader(getFileFrom));
			editorPane.read(br, Charset.defaultCharset());
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(new Frame(), "Log files not found!\n"
					+ "Please try again later.", JOptionPane.MESSAGE_PROPERTY ,JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new Frame(), e.getMessage(), JOptionPane.MESSAGE_PROPERTY ,JOptionPane.ERROR_MESSAGE);

		}

	}
}
