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

public class SelallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelallServlet() {
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
        String userName= req.getParameter("name");
        String userPwd = req.getParameter("password");
		System.out.println(userName);
		System.out.println(userPwd);
		JSONArray json = new JSONArray();  
        ArrayList<Chuwei> list = new ArrayList<Chuwei>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		//List<Student> list = new ArrayList<Student>();
		try {
			conn = DbManager.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement("select id,isUseful,ismeter from chuwei");
			rs = pst.executeQuery();
			while (rs.next()){
				int id=Integer.parseInt(rs.getString(1));
				String isuseful=rs.getString(2);
				String ismeter=rs.getString(3);
				Chuwei chuwei=new Chuwei();
				chuwei.setId(id);
				chuwei.setIsUseful(isuseful);
				chuwei.setIsmeter(ismeter);
				list.add(chuwei);
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

		json.addAll(list);  
	    PrintWriter out=resp.getWriter();
	    out.println(json);
	    json.listIterator();  
	    if(json.isEmpty()){  
	            System.out.println("dfdfdf");  
	    }  
	    ListIterator  li = json.listIterator();  
	    while(li.hasNext()){  
	            System.out.println(li.next().toString());  
	    }	
	}
}  
