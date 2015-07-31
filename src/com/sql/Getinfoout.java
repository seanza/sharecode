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
	public static String[] getallismeter(int num, String[] scode,String[] sid){            //取指定id的com的在位的有东西的数组，如0100010等
		int[] summeter=Getinfo.getadrnum(num);
		boolean[] codematched = new boolean[scode.length];
		String[] meterid=new String[summeter[0]+summeter[1]];
		String[] metercode = new String[summeter[0]+summeter[1]];
		String[] istargetmeter = new String[summeter[0]+summeter[1]];
		String[] model = new String[summeter[0]+summeter[1]];
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DbManager.getConnection();
			pst = conn.prepareStatement("select id, meter_code, ismeter, model from chuwei where com IN(SELECT com from com where id="+num+")");
			rs = pst.executeQuery();
			int k=0;
			while(rs.next()){ //遍历结果集
				  meterid[k]=rs.getString(1);
				  metercode[k]=rs.getString(2);
				  istargetmeter[k]=rs.getString(3);
				  model[k]=rs.getString(4);
				  k++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn, pst, rs);
		}
		int snum=0, bnum=0;
		try {
			conn = DbManager.getConnection();
			pst = conn.prepareStatement("select dan, san from intemp where id="+num+"");
			rs = pst.executeQuery();
			while(rs.next()){ //遍历结果集
				  snum=Integer.parseInt(rs.getString(1));
				  bnum=Integer.parseInt(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn, pst, rs);
		}
		int i, j, s = 0, b = 0, flag = 0;
		for(i=0;i<scode.length;i++)
			codematched[i] = false;
		for(i=0;i<metercode.length;i++)
		{
			if(istargetmeter[i].equals("1"))
			{
				for(j=0;j<scode.length;j++)
				{
					if(!codematched[j] && scode[j].equals(metercode[i]))
					{
						if(model[i].equals("s"))
							s ++;
						else
							b ++;
						if(s > snum || b > bnum)
						{
							flag = 1;
							break;
						}
						codematched[j] = true;
						break;
					}
				}
				if(flag == 1)
					break;
			}
		}
		if(flag == 1)
		{
			String[] result=new String[1];
			result[0]="unmatched s or b meter number";
			return result;
		}
		for(i=0;i<scode.length;i++)
		{
			if(!codematched[i])
			{
				String[] result=new String[1];
				result[0]="unmatched meter remaining";
				return result;
			}	
		}
		for(int x=0;x<sid.length;x++){
			istargetmeter[Integer.parseInt(sid[x])-1]="0";
		}
		String sztos=new String();
		for(int l = 0;l<istargetmeter.length;l++){   sztos+=istargetmeter[l]; }
		int[] rownum =new int[2];
		rownum=Getinfo.getrownum(num);
		String[] str=new String[rownum[1]];
		System.out.println("拼字符sztos"+sztos+"单项行数"+rownum[0]+"总行数"+rownum[1]);
		j=0;
		for(i=0;i<rownum[0];i++){  
			str[i]=sztos.substring(j,j+8);
			j=j+8;
		}
		for(i=rownum[0];i<rownum[1];i++){  
			str[i]=sztos.substring(j,j+5)+"000";
			j=j+5;
		}
		return str;
	}
	public static String reverse(String str) {	
		return new StringBuilder(str).reverse().toString().toUpperCase();
	}
}
