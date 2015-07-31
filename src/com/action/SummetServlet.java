package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.dao.DbManager;
import com.rxtx.Modbus;
import com.sql.Getinfo;

/**
 * Servlet implementation class SummetServlet
 */
public class SummetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SummetServlet() {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        resp.setContentType("text/html;charset=UTF-8");  
        resp.setHeader("Cache-Control","no-cache");  
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String userName= req.getParameter("name");
		System.out.println(userName);
		String a=danxiang();
		System.out.println(a);
		String b=sanxiang();
		System.out.println(b);
		String c=Integer.toString(gettemwet()[0]);
		System.out.println(c);
		String d=Integer.toString(gettemwet()[1]);
		System.out.println(d);
		List<String> list = new ArrayList<String>();
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);
		JSONArray jsons=new JSONArray();
		PrintWriter out=resp.getWriter();
		jsons = JSONArray.fromObject(list);
		out.println(jsons);
		ListIterator  li = jsons.listIterator();  
	    while(li.hasNext()){  
	            System.out.println(li.next().toString());  
	    }
		list.clear();
	}
   public String danxiang(){
       	Connection conn = null;
   		PreparedStatement pst = null;
   		ResultSet rs = null;
   		String model=new String();
   		try {
   			conn = DbManager.getConnection();
   			conn.setAutoCommit(false);
   			pst = conn.prepareStatement("SELECT SUM(ismeter) FROM chuwei WHERE model='s'");
   			rs = pst.executeQuery();
   			while (rs.next()){
   				model=rs.getString(1);
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
   		
   			 return model;
       }
          
   public String sanxiang(){
      	Connection conn = null;
  		PreparedStatement pst = null;
  		ResultSet rs = null;
  		String model=new String();
  		try {
  			conn = DbManager.getConnection();
  			conn.setAutoCommit(false);
  			pst = conn.prepareStatement("SELECT SUM(ismeter) FROM chuwei WHERE model='b'");
  			rs = pst.executeQuery();
  			while (rs.next()){
  				model=rs.getString(1);
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
  		
  			 return model;
      }
       public int[] gettemwet(){
    	String ret = ""; 
    	String com=Getinfo.getcomname(1);
    	short[] tw=Modbus.readHold(com,10,0,2);
   	    int[] num=new int[2];
      if(tw!=null){
	    int tem=(tw[1]-27315)/100;
	    int wet=tw[0]/100;
	    num[0]=tem;
	    num[1]=wet;
	    return num;
      }
      else{
    	  num[0]=20;
    	  num[1]=60;
      	 return num;
         }
    }
}  