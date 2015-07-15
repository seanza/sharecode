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
import com.service.StudentService;
import com.service.impl.StudentServiceImpl;
/**
 * Servlet implementation class Jsondata
 */
public class PankuquerenServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		if(userName.indexOf(",")>0){
			stringA= userName.split(","); 
			stringB= userPwd.split(",");
			System.out.println("盘库数量大于一");
		}
		else {
			stringA=new String[1];
			stringB=new String[1];
			System.out.println("数组已经初始化：");
			stringA[0]=userName;
			stringB[0]=userPwd;
			System.out.println("盘库数量为一："+stringB[0]);
		}
		System.out.println("up123"+stringA[0]);
		System.out.println("up124"+stringB[0]);
		List<String> list = new ArrayList<String>();
		JSONArray jsons=new JSONArray();
		PrintWriter out=resp.getWriter();
    	updateChuweiList(stringA,stringB);
    	list.add("成功");
    	jsons = JSONArray.fromObject(list);
        out.println(jsons);
    	list.clear();
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
			System.out.println("mcode"+mcode);
			String id=sql2[i];
			System.out.println("id"+id);
			pst = conn.prepareStatement("update chuwei set meter_code = '"+mcode+"' WHERE code='"+id+"'");
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