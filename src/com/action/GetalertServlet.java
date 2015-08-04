package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.dao.DbManager;
import com.model.Alert;
import com.model.Logdata;

/**
 * Servlet implementation class GetalertServlet
 */
@WebServlet("/GetalertServlet")
public class GetalertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetalertServlet() {
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
        String date= req.getParameter("date");
        System.out.println(date);
        ArrayList<Alert> list=new ArrayList();
        JSONArray json = new JSONArray();  
		list = getList(date);
		json.addAll(list);  
	    PrintWriter out=resp.getWriter();
	    out.println(json);
	    list.listIterator();  
	    if(json.isEmpty()){  
            System.out.println("json-null");  
        }  
        ListIterator  li = json.listIterator();  
        while(li.hasNext()){  
            System.out.println(li.next().toString());  
           }
    }
    public ArrayList<Alert> getList(String date){
		ArrayList<Alert> list = new ArrayList<Alert>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DbManager.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement("select * from alert WHERE date= '"+date+"'");
			rs = pst.executeQuery();
			while (rs.next()){
				int id=Integer.parseInt(rs.getString(1));
				String date1=rs.getString(2);
				String time=rs.getString(3);
				String log=rs.getString(4);

				System.out.println("ope:"+log);
				Alert alert=new Alert();
				alert.setId(id);
				alert.setDate(date1);
				alert.setTime(time);
				alert.setLog(log);
				list.add(alert);
			}
			conn.commit();
		} catch (SQLException  e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn, pst, rs);
		}
		return list;		
	}
	
}
