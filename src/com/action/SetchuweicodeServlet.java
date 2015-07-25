package com.action;
import java.io.*;  
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

import javax.servlet.*;  
import javax.servlet.http.*;  

import net.sf.json.JSONArray;

import javax.servlet.annotation.WebServlet;

import com.model.Student;
import com.model.Chuwei;
import com.dao.*;
import com.rxtx.Test;
import com.rxtx.SerialReader;
import com.sql.Getinfo;
/**
 * Servlet implementation class Jsondata
 */
public class SetchuweicodeServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */ 
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        doPost(req,resp);  
    }  
  
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        resp.setContentType("text/html;charset=UTF-8");  
        resp.setHeader("Cache-Control","no-cache");  
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
        String userName= req.getParameter("name");
        String userPwd = req.getParameter("password");
		System.out.println("updata1"+userName);
		System.out.println("updata2"+userPwd);
		String[] stringA;
		String[] stringB;
		stringA= userName.split(","); 
		stringB= userPwd.split(",");
		boolean repeated = CheckIfCodeRepeated(stringA);
		List<String> list = new ArrayList<String>();
		JSONArray jsons=new JSONArray();
		PrintWriter out=resp.getWriter();
		if(!repeated)
		{
			System.out.println("1");
	    	updateChuweiList(stringA,stringB);
	    	System.out.println("2");
	    	//list.add("成功");
	    	//jsons = JSONArray.fromObject(list);
	    	out.print("成功");
		}
		else
			out.print("条码重复");
    	//list.clear();
	}
private boolean CheckIfCodeRepeated(String[] stringA)
	{
		int i, j;
		for(i=0;i<stringA.length-1;i++)
		{
			for(j=i+1;j<stringA.length;j++)
			{
				if(stringA[i].equals(stringA[j]))
				{
					System.out.println("YEMIANchongfu!");
					return true;
				}
					
			}
		}
		return false;
	}

public static void updateChuweiList(String[] sql1,String[] sql2){
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DbManager.getConnection();
			conn.setAutoCommit(false);
			for(int i=0;i<sql1.length;i++){
			String mcode=sql1[i];
			System.out.println("mcode:"+mcode);
			System.out.println(sql2[i]);
			int id=Integer.parseInt(sql2[i]);
			String cc=Getinfo.getlastcom();
			System.out.println("id:"+id);
			pst = conn.prepareStatement("update chuwei set code = '"+mcode+"' WHERE com='"+cc+"' and id='"+id+"'");
			int x=pst.executeUpdate();
			System.out.println(x);
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
	}

}  