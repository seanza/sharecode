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
 * Servlet implementation class SetrowServlet
 */
@WebServlet("/SetrowServlet")
public class SetrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetrowServlet() {
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
        String dan= req.getParameter("dan");
        String san= req.getParameter("san");
        System.out.println(dan);
        Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
        Getinfo getinfo=new Getinfo();
        PrintWriter out=resp.getWriter();
		//List<Student> list = new ArrayList<Student>();
		try {
			conn = DbManager.getConnection();
			conn.setAutoCommit(false);
            String comname=Getinfo.getlastcom();
            pst = conn.prepareStatement("call setchu(?,?,?)");
            pst.setString(1, dan);  
            pst.setString(2, san);  
            pst.setString(3, comname); 
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
