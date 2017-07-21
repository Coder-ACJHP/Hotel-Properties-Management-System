package com.coder.hms.test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class H2ConnectionTest {
	
	public static void main(String[] args) {
		
		try {
			Class.forName("org.h2.Driver");
			 Connection conn = DriverManager.
			            getConnection("jdbc:h2:~/test", "sa", "");
			
			if(conn != null) {
				System.out.println("CONNECTED!");
				String SQL = "INSERT INTO  HOTEL_MANAGMENT_SYSTEM.CUSTOMER VALUES (?,?,?,?,?,?)";
				PreparedStatement prs = conn.prepareStatement(SQL);
				prs.executeUpdate();
			
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
