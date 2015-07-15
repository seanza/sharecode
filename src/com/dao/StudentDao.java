package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.model.Student;

public class StudentDao {
	public List<Student> findName(Student student){
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		List<Student> list = new ArrayList<Student>();
		try {
			conn = DbManager.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement("select * from user where name=? and password=?");
			pst.setString(1, student.getName());
			pst.setString(2, student.getPassword());
			rs = pst.executeQuery();
			while (rs.next()){
				list.add(new Student(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4)));
				String ppp=rs.getString(4);
				System.out.println(ppp);
			}
			
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
		return list;		
	}
	
	public int create(Student student){
		Connection conn = null;
		PreparedStatement pst = null;
		int state = 0;
		
		try {
			conn = DbManager.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement("insert into user(name,password,usergroup) values(?,?,?)");
			pst.setString(1, student.getName());
			pst.setString(2, student.getPassword());
			pst.setString(3, student.getGroup());
			state = pst.executeUpdate();
			conn.commit();
		}catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DbManager.closeConnection(conn, pst, null);
		}
		return state;
	}
}
