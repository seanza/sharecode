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
import com.rxtx.Modbus;
import com.sql.Getinfo;

/**
 * Servlet implementation class CancelServlet
 */
@WebServlet("/CancelServlet")
public class CancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelServlet() {
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
        String set= req.getParameter("set");
        System.out.println(set);
        Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
        PrintWriter out=resp.getWriter();
        int[] getrow=Getinfo.getrownum(1);
		int sumrownum=getrow[1];
		int[] getre=new int[sumrownum];
		String com= Getinfo.getcomname(1);
		//List<Student> list = new ArrayList<Student>();
		try {
			conn = DbManager.getConnection();
			conn.setAutoCommit(false);
            pst = conn.prepareStatement("call deltemp()");
            pst.executeUpdate();
			conn.commit();
			Modbus.write8coilofflight(com,sumrownum);   //关灯
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