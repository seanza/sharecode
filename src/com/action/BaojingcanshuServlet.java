package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.dao.DbManager;
import com.model.Chuwei;
import com.rxtx.Test;


public class BaojingcanshuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		System.out.println("ppppp");
        String teml= req.getParameter("teml");
        String temh= req.getParameter("temh");
        String wetl= req.getParameter("wetl");
        String weth= req.getParameter("weth");
        String che1= req.getParameter("che1");
        String che2= req.getParameter("che2");
        String chul= req.getParameter("chul");
        System.out.println(chul);
        setyuzhi(teml,temh,wetl,weth,chul);
        setbaojing(che1,che2);
	    PrintWriter out=resp.getWriter();
	    out.println("成功");
	}
   public void setyuzhi(String teml,String temh,String wetl,String weth,String chul){
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		//List<Student> list = new ArrayList<Student>();
		try {
			conn = DbManager.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement("update yuzhi set teml = '"+teml+"',temh='"+temh+"',wetl='"+wetl+"',weth='"+weth+"',chul='"+chul+"'");
			int x=pst.executeUpdate();
			System.out.println(x);
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
   public void setbaojing(String tem,String chu){
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		//List<Student> list = new ArrayList<Student>();
		try {
			conn = DbManager.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement("update baojing set tem = '"+tem+"',chu='"+chu+"'");
			int x=pst.executeUpdate();
			System.out.println(x);
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
