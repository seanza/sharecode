package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbManager {
	public static Connection getConnection(){
		Connection conn = null;		
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/test");
			conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return conn;		
	}
	public static void closeConnection(Connection conn, PreparedStatement pst,ResultSet rs){
		try{
			if(rs !=null){
				rs.close();
			}
			if(pst !=null){
				pst.close();
			}
			if(conn !=null){
				conn.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
