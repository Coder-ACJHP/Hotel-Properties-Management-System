package com.coder.hms.connection;

import java.awt.Dialog.ModalExclusionType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.coder.hms.ui.external.InformationFrame;
import com.ibatis.common.jdbc.ScriptRunner;

public class DatabaseServerPreparingInitializer extends JFrame {

	/**
	 * 
	 */
	private static File file;
	private boolean status = false;
	private static final long serialVersionUID = 1L;


	public DatabaseServerPreparingInitializer() {
		
		setType(Type.POPUP);
		setResizable(false);
		this.setAlwaysOnTop(isAlwaysOnTopSupported());
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
				
		this.pack();
		
	}
	

	public void runScriptFile() {
		
		final InformationFrame dialog = new InformationFrame();
		final String sqlFilePath = "src/com/coder/hms/connection/hotel_management_system.sql";
		final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
		final String DB_CONNECTION = "jdbc:mysql://localhost:3306/";
		String DB_USER = JOptionPane.showInputDialog(this, "Enter your database user name :", "Coder HMS [Input]", JOptionPane.QUESTION_MESSAGE);
		String DB_PASSWORD = JOptionPane.showInputDialog(this, "Enter your database password :", "Coder HMS [Input]", JOptionPane.QUESTION_MESSAGE);
		
			try {
				
				Class.forName(DB_DRIVER);
				Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
				
				ScriptRunner runner = new ScriptRunner(connection, false, false);
				Reader br = new BufferedReader(new FileReader(sqlFilePath));

				file = new File(System.getProperty("user.dir")+ File.separator + "Logging Store/SQL_Logs.txt");
				PrintWriter writer = new PrintWriter(file);
				runner.setLogWriter(writer);
				runner.runScript(br);
				dialog.setMessage("Your database and tables created successfully.");
				
				status = true;
				
			} catch (SQLException | ClassNotFoundException | IOException ex) {
				dialog.setMessage(ex.getMessage());
				status = false;
			}
		
		dialog.setVisible(true);
	}
	
	public static File getLogFile() {
		if(file.exists()) {
			return DatabaseServerPreparingInitializer.file;
		}
		return null;
	}

	public boolean getStatus() {
		return this.status;
	}

}
