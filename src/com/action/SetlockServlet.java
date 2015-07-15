package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DbManager;
import com.sql.Getinfo;

/**
 * Servlet implementation class SetlockServlet
 */
@WebServlet("/SetlockServlet")
public class SetlockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetlockServlet() {
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
        String a1= req.getParameter("a1");
        String a2= req.getParameter("a2");
        String b1= req.getParameter("b1");
        String b2= req.getParameter("b2");
        String b3= req.getParameter("b3");
        String b4= req.getParameter("b4");
        System.out.println(a1);
        Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
        PrintWriter out=resp.getWriter();
		try {
			conn = DbManager.getConnection();
			conn.setAutoCommit(false);
            String comname=Getinfo.getlastcom();
            pst = conn.prepareStatement("INSERT into setlock(com,dorow,docol,di1row,di1col,di2row,di2col) VALUES(?,?,?,?,?,?,?)");
            pst.setString(1, comname);  
            pst.setString(2, a1);  
            pst.setString(3, a2);  
            pst.setString(4, b1);  
            pst.setString(5, b2);  
            pst.setString(6, b3);  
            pst.setString(7, b4);  
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
