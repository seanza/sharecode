package com.sql;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dao.DbManager;

public class Setinfo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
    public static void inserttemp(String com,int d,int s){
    	Connection conn = null;
 		PreparedStatement pst = null;
 		ResultSet rs = null;

 		try {
 			conn = DbManager.getConnection();
 			conn.setAutoCommit(false);
             String comname=Getinfo.getlastcom();
             pst = conn.prepareStatement("INSERT into intemp(com,dan,san) VALUES(?,?,?)");
             pst.setString(1, com);  
             pst.setInt(2, d);  
             pst.setInt(3, s);  
             pst.executeUpdate();
 			conn.commit();
 		} catch (SQLException e) {
 			try {
 				conn.rollback();
 			} catch (SQLException e1) {
 				e1.printStackTrace();
 			}
 			e.printStackTrace();
 		}finally{
 			DbManager.closeConnection(conn, pst, rs);
 		}
 	}
    public static void insertalert(String date,String time,String log){
    	Connection conn = null;
 		PreparedStatement pst = null;
 		ResultSet rs = null;
 		try {
 			conn = DbManager.getConnection();
 			conn.setAutoCommit(false);
             pst = conn.prepareStatement("INSERT into alert(date,time,log) VALUES(?,?,?)");
             pst.setString(1, date);  
             pst.setString(2, time);  
             pst.setString(3, log);
             System.out.println("insertbaojing");
             pst.executeUpdate();
 			conn.commit();
 		} catch (SQLException e) {
 			try {
 				conn.rollback();
 			} catch (SQLException e1) {
 				e1.printStackTrace();
 			}
 			e.printStackTrace();
 		}finally{
 			DbManager.closeConnection(conn, pst, rs);
 		}
 	}
    public static void deltemp(){
    	Connection conn = null;
 		PreparedStatement pst = null;
 		ResultSet rs = null;
 		try {
 			conn = DbManager.getConnection();
 			conn.setAutoCommit(false);
            pst = conn.prepareStatement("call deltemp()");
            pst.executeUpdate();
 			conn.commit();
 		} catch (SQLException e) {
 			try {
 				conn.rollback();
 			} catch (SQLException e1) {
 				e1.printStackTrace();
 			}
 			e.printStackTrace();
 		}finally{
 			DbManager.closeConnection(conn, pst, rs);
 		}
 	}
}
