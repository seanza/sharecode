package com.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;  
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.*;  
import javax.servlet.http.*;  

import net.sf.json.JSONArray;

import javax.servlet.annotation.WebServlet;

import com.model.Chuwei;
import com.dao.*;
import com.sql.*;
/**
 * Servlet implementation class SetcomServlet
 */
public class SetcomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetcomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
        String sel= req.getParameter("sel");
        System.out.println(sel);
        Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
        Getinfo getinfo=new Getinfo();
        PrintWriter out=resp.getWriter();
		//List<Student> list = new ArrayList<Student>();
		try {
			conn = DbManager.getConnection();
			conn.setAutoCommit(false);
            int id=getinfo.getsumcom();
            int tnum=id+1;
            String tname="chuwei"+tnum;
            System.out.println(tname);
            pst = conn.prepareStatement("INSERT into com(com,tablename) VALUES(?,?)");
            pst.setString(1, sel);  
            pst.setString(2, tname);  
            pst.executeUpdate();
			conn.commit();
			out.println("1");
		} catch (SQLException e) {
			try {
				conn.rollback();
				out.println("0");
			} catch (SQLException e1) {
				e1.printStackTrace();
				out.println("0");
			}
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn, pst, rs);
		}
        
        
        
        
        
	}

}
