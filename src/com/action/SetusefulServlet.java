package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DbManager;

import net.sf.json.JSONArray;


public class SetusefulServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetusefulServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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
		System.out.println("updata1"+userName);
		String[] stringA;
		String[] stringB;
		if(userName.indexOf(",")>0){
			stringA= userName.split(","); 
		}
		else {
			stringA=new String[1];
			stringA[0]=userName;
		}
		System.out.println("up123"+stringA[0]);
		List<String> list = new ArrayList<String>();
		JSONArray jsons=new JSONArray();
		PrintWriter out=resp.getWriter();
		delisuseful();
    	updateisuseful(stringA);
    	list.add("成功");
    	jsons = JSONArray.fromObject(list);
    	out.println(jsons);
    	list.clear();
    }
public static void updateisuseful(String[] sql1){
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	try {
		conn = DbManager.getConnection();
		conn.setAutoCommit(false);
		for(int i=0;i<sql1.length;i++){
		int id=Integer.parseInt(sql1[i]);
		System.out.println("id:"+id);
		pst = conn.prepareStatement("update chuwei set isUseful = '1' WHERE id='"+id+"'");
		int x=pst.executeUpdate();
		System.out.println("改变行数："+x);
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
public static void delisuseful(){
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	try {
		conn = DbManager.getConnection();
		conn.setAutoCommit(false);
		pst = conn.prepareStatement("update chuwei set isUseful = '0' ");
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
