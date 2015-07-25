package com.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dao.DbManager;

public class Getinfo {
	public static void main(String[] args) {

	}
	
	public static int getsumcom(){   //取共有多少个周转柜
		int a = 100;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DbManager.getConnection();
			pst = conn.prepareStatement("select count(*) from com");
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
	public static String getcomname(int num){   //取指定id的com
		String comname=new String();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DbManager.getConnection();
			pst = conn.prepareStatement("select com from com where id="+num+"");
			rs = pst.executeQuery();
			rs.next();
			comname=rs.getString(1);
			return comname;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn, pst, rs);
		}
		return comname;
	}
	public static String getlastcom(){       //取这次设置的周转柜com
		String a=new String();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DbManager.getConnection();
			pst = conn.prepareStatement("select com from com order by id desc limit 1");
			rs = pst.executeQuery();
			rs.next();
			a=rs.getString(1);
			return a;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn, pst, rs);
		}
		System.out.println(a);
		return a;
	}
	public static int getalldan(){                 //取所有可取单相表数量
		int a = 100;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DbManager.getConnection();
			pst = conn.prepareStatement("select count(*) from chuwei where model='s' AND ISNULL(meter_code) AND isuseful='0'");
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
			pst = conn.prepareStatement("select count(*) from chuwei where model='b' AND ISNULL(meter_code) AND isuseful='0'");
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
			pst = conn.prepareStatement("select count(*) from chuwei where model='s' AND ISNULL(meter_code) AND isuseful='0' AND com IN(SELECT com from com where id="+num+")");
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
			pst = conn.prepareStatement("select count(*) from chuwei where model='b' AND ISNULL(meter_code) AND isuseful='0' AND com IN(SELECT com from com where id="+num+")");
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
	public static int[] gettempnum(int num){            //取这次将入库的数量
		int[] a =new int[3];
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DbManager.getConnection();
			pst = conn.prepareStatement("select dan,san from intemp where id="+num+"");
			rs = pst.executeQuery();
			rs.next();
			a[0]=Integer.parseInt(rs.getString(1));
			a[1]=Integer.parseInt(rs.getString(2));
			return a;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn, pst, rs);
		}
		System.out.println(a[0] + " " + a[1]);
		return a;
	}
	public static int[] getadrnum(int num ){            //取指定id的com的储位数量
		int[] a =new int[2];
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DbManager.getConnection();
			pst = conn.prepareStatement("SELECT COUNT(model) FROM chuwei where model='s' and com IN(SELECT com from com where id="+num+") UNION SELECT COUNT(model) FROM chuwei where model='b' and com IN(SELECT com from com where id="+num+")");
			rs = pst.executeQuery();
			int k=0;
			while(rs.next()){ //遍历结果集
				  a[k]=Integer.parseInt(rs.getString(1));
				  k++;
			}
			return a;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn, pst, rs);
		}
		System.out.println(a);
		return a;
	}
	public static int[] getrownum(int num ){            //取指定id的com的单项表多少行，三相表多少行
		int[] a =new int[2];
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DbManager.getConnection();
			pst = conn.prepareStatement("select MAX(row) from chuwei where  model='s' and com IN(SELECT com from com where id='"+num+"') UNION select MAX(row) from chuwei where  model='b' and com IN(SELECT com from com where id="+num+")");
			rs = pst.executeQuery();
			rs.next(); //遍历结果集
			a[0]=Integer.parseInt(rs.getString(1));
			rs.next();
			a[1]=Integer.parseInt(rs.getString(1));
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
		int[] summeter=getadrnum(num);
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
			ssid[Integer.parseInt(sid[i])-1]="1";
			System.out.println(sid[i] + " ");
		}
		String sztos=new String();
		for(int i = 0;i<ssid.length;i++){   sztos+=ssid[i]; }
		int[] rownum =new int[2];
		rownum=getrownum(num);
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
	public static int  getifnext(int num ){            //取temp的id,判断是否有下一条记录
        int a=0;
		num=num+1;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DbManager.getConnection();
			pst = conn.prepareStatement("select * from intemp where id="+num+"");
			rs = pst.executeQuery();
			if(rs.next()){
				a=1;
			}
			else{
			a=0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn, pst, rs);
		}
		return a;
	}
	public static int[] getcomdoor(int num ){            //取指定id的com的门锁位置数组
		int[] a =new int[6];
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DbManager.getConnection();
			pst = conn.prepareStatement("select * from setlock where id="+num+"");
			rs = pst.executeQuery();
			int k=0;
			rs.next(); //遍历结果集
			a[0]=Integer.parseInt(rs.getString(3));
			a[1]=Integer.parseInt(rs.getString(4));
			a[2]=Integer.parseInt(rs.getString(5));
			a[3]=Integer.parseInt(rs.getString(6));
			a[4]=Integer.parseInt(rs.getString(7));
			a[5]=Integer.parseInt(rs.getString(8));
			return a;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn, pst, rs);
		}
		System.out.println(a);
		return a;
	}

	public static boolean checkrepeatedindatabase(int num, String[] code)
	{
		int[] summeter=getadrnum(num);
		String[] metercode=new String[summeter[0]+summeter[1]];
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int k=0;
		try {
			conn = DbManager.getConnection();
			pst = conn.prepareStatement("select meter_code from chuwei where com IN(SELECT com from com where id="+num+") and ismeter = 1");
			rs = pst.executeQuery();
			
			while(rs.next()){ //遍历结果集
				  metercode[k]=rs.getString(1);
				  k++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn, pst, rs);
		}
		int i,j;
		for(i=0;i<code.length;i++)
		{
			for(j=0;j<k;j++)
			{
				if(code[i].equals(metercode[j]))
					return true;
			}
		}
		return false;
	}
}
