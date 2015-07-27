package com.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dao.DbManager;
import com.sql.Getinfo;

public class LogSaver
{

	public LogSaver()
	{
		
	}
	
	public void saveinlog(String type, String name, String auth)
	{
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Date d = new Date();
		String authority = (auth.equals("admin"))?"管理员":"用户";
		int id = 0;
		try{
			conn = DbManager.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement("SELECT COUNT(id) FROM log");
			rs = pst.executeQuery();
			rs.next();
			id = Integer.parseInt(rs.getString(1));
			id++;
			System.out.println("id = " + id);
		}catch(SQLException e)
		{
			try {
 				conn.rollback();
 			} catch (SQLException e1) {
 				e1.printStackTrace();
 			}
 			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn, pst, rs);
		}
		
		int[] ds = new int[3];
		
		try
		{
			conn = DbManager.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement("INSERT INTO log VALUES (?,?,?,?)");
			pst.setInt(1, id);
			SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdftime = new SimpleDateFormat("HH:mm:ss");
			pst.setString(2, sdfdate.format(d));
			pst.setString(3, sdftime.format(d));
			switch(type)
			{
				case "login":
					pst.setString(4, "用户'" + name + "'(权限为" + authority + ")登录本系统！");
					break;
				case "in":
					ds = Getinfo.gettempnum(1);
					pst.setString(4, "用户'" + name + "'(" + authority + ")进行入库操作：入库" + ds[0] + "只单相电表和" + ds[1] + "只三相电表。");
					break;
				case "inventory":
					pst.setString(4, "用户'" + name + "'(" + authority + ")成功进行盘库操作。");
					break;
				case "out":
					ds = Getinfo.gettempnum(1);
					pst.setString(4, "用户'" + name + "'(" + authority + ")进行出库操作：出库" + ds[0] + "只单相电表和" + ds[1] + "只三相电表。");
			}
			pst.executeUpdate();
			conn.commit();
		}catch(SQLException e)
		{
			try 
			{
		 		conn.rollback();
		 	}catch (SQLException e1) 
			{
		 		e1.printStackTrace();
		 	}
		 	e.printStackTrace();
		}finally
		{
			DbManager.closeConnection(conn, pst, rs);
		}
	}

}
