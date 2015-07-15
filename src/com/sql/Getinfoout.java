package com.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dao.DbManager;

public class Getinfoout {
	public static int getalldan(){                 //取所有可取单相表数量
		int a = 100;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DbManager.getConnection();
			pst = conn.prepareStatement("select count(*) from chuwei where model='s' AND meter_code IS NOT NULL AND isuseful='0'");
			rs = pst.executeQuery();
			rs.next();
			a=Integer.parseInt(rs.getString(1));
			return a;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn, pst, rs);
		}
		System.out.println(a);
		return a;
	}
	public static int getallsan(){                  //取所有可取三相表数量
		int a = 100;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DbManager.getConnection();
			pst = conn.prepareStatement("select count(*) from chuwei where model='b' AND meter_code IS NOT NULL AND isuseful='0'");
			rs = pst.executeQuery();
			rs.next();
			a=Integer.parseInt(rs.getString(1));
			return a;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn, pst, rs);
		}
		System.out.println(a);
		return a;
	}
	public static int getcomdan(int num ){              //取指定id的com的可取单项数量
		int a = 100;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DbManager.getConnection();
			pst = conn.prepareStatement("select count(*) from chuwei where model='s' AND meter_code IS NOT NULL AND isuseful='0' AND com IN(SELECT com from com where id="+num+")");
			rs = pst.executeQuery();
			rs.next();
			a=Integer.parseInt(rs.getString(1));
			return a;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn, pst, rs);
		}
		System.out.println(a);
		return a;
	}
	public static int getcomsan(int num ){            //取指定id的com的可取三项数量
		int a = 100;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DbManager.getConnection();
			pst = conn.prepareStatement("select count(*) from chuwei where model='b' AND meter_code IS NOT NULL AND isuseful='0' AND com IN(SELECT com from com where id="+num+")");
			rs = pst.executeQuery();
			rs.next();
			a=Integer.parseInt(rs.getString(1));
			return a;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn, pst, rs);
		}
		System.out.println(a);
		return a;
	}
	public static String[] getallismeter(int num,String[] sid){            //取指定id的com的在位的有东西的数组，如0100010等
		int[] summeter=Getinfo.getadrnum(num);
		String[] ssid=new String[summeter[0]+summeter[1]];
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DbManager.getConnection();
			pst = conn.prepareStatement("select ismeter from chuwei where com IN(SELECT com from com where id="+num+")");
			rs = pst.executeQuery();
			int k=0;
			while(rs.next()){ //遍历结果集
				  ssid[k]=rs.getString(1);
				  k++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn, pst, rs);
		}

		for(int i=0;i<sid.length;i++){
			ssid[Integer.parseInt(sid[i])-1]="0";
		}
		String sztos=new String();
		for(int i = 0;i<ssid.length;i++){   sztos+=ssid[i]; }
		int[] rownum =new int[2];
		rownum=Getinfo.getrownum(num);
		String[] str=new String[rownum[1]];
		System.out.println("拼字符sztos"+sztos+"单项行数"+rownum[0]+"总行数"+rownum[1]);
		int j=0;
		for(int i=0;i<rownum[0];i++){  
			str[i]=reverse(sztos.substring(j,j+8));
			j=j+8;
		}
		for(int i=rownum[0];i<rownum[1];i++){  
			str[i]="000"+reverse(sztos.substring(j,j+5));
			j=j+5;
		}
		return str;
	}
	public static String reverse(String str) {	
		return new StringBuilder(str).reverse().toString().toUpperCase();
	}
}
