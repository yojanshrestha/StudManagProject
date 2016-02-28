package com.javastud.studmproj.doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.javastud.studmproj.model.User;


public class UserDoa {
	private Connection conn = null;
	
	public UserDoa() throws ClassNotFoundException, SQLException {
		conn = MysqlConnection.getConnection();
	
	}
	
	public boolean validate(User user) throws SQLException{
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("select *from user where username='"+user.getUsername()+"' and password='"+user.getPassword()+"'");

//		PreparedStatement stmt = conn
//				.prepareStatement("select * from user where username = ? and password = ?");
//		stmt.setString(1, user.getUsername());
//		stmt.setString(2, user.getPassword());
//		ResultSet resultset = stmt.executeQuery();

				
		if(rs.next()){
			return true;
		}else{
			return false;
		}
		
	}
	
	public void addUser(User user) throws SQLException{
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO user(username, password) VALUES(?,?)");
		stmt.setString(1, user.getUsername());
		stmt.setString(2, user.getPassword());
		stmt.execute();
	}
	
	public boolean checkIfExists(String userName) throws SQLException{
		PreparedStatement stmt = conn.prepareStatement("SElECT *FROM user where username= ?");
		stmt.setString(1, userName);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()){
			return true;
		}else{
			return false;
		}
	}
	
}
