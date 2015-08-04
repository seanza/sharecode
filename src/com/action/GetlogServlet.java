package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.dao.DbManager;
import com.model.Chuwei;
import com.model.Logdata;

/**
 * Servlet implementation class GetlogServlet
 */
@WebServlet("/GetlogServlet")
public class GetlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetlogServlet() {
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
        ArrayList<Logdata> list=new ArrayList();
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
    public ArrayList<Logdata> getList(String date){
		ArrayList<Logdata> list = new ArrayList<Logdata>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DbManager.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement("select * from log WHERE time1= '"+date+"'");
			rs = pst.executeQuery();
			while (rs.next()){
				System.out.println("aaaaaaaaaaa");
				int id=Integer.parseInt(rs.getString(1));
				String time1=rs.getString(2);
				String time2=rs.getString(3);
				String ope=rs.getString(4);
				System.out.println("ope:"+ope);
				Logdata logdata=new Logdata();
				logdata.setId(id);
				logdata.setTime1(time1);
				logdata.setTime2(time2);
				logdata.setOpe(ope);
				list.add(logdata);
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
