package com.action;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.dao.DbManager;
import com.model.Chuwei;
import com.rxtx.Modbus;
import com.sql.Getinfo;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class InselServlet
 */
@WebServlet("/InselServlet")
public class InselServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InselServlet() {
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
        String id= req.getParameter("id");
        int idnum=Integer.parseInt(id);
		System.out.println(idnum);
		int[] inarray=new int[3];
		inarray= Getinfo.gettempnum(idnum);
		inarray[2]=inarray[0]+inarray[1];
		System.out.println(inarray[2]);
		ArrayList<Chuwei> list=new ArrayList();
		JSONArray json = new JSONArray();  
		list = getChuweiList(inarray[0],inarray[2]);
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
    
public ArrayList<Chuwei> getChuweiList(int sql1,int sql2){
		ArrayList<Chuwei> list = new ArrayList<Chuwei>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DbManager.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement("select * from chuwei WHERE model='s' AND ISNULL(meter_code) AND isuseful='0' LIMIT "+sql1+" UNION select * from chuwei WHERE model='b' AND ISNULL(meter_code) AND isuseful='0' ORDER BY id LIMIT "+sql2+"");
			rs = pst.executeQuery();
			while (rs.next()){
				int id=Integer.parseInt(rs.getString(1));
				int row=Integer.parseInt(rs.getString(3));
				int col=Integer.parseInt(rs.getString(4));
				String model=rs.getString(6);
				String com=rs.getString(10);
				Chuwei chuwei=new Chuwei();
				chuwei.setId(id);
				chuwei.setRow(row);
				chuwei.setCol(col);
				chuwei.setModel(model);
				chuwei.setCom(com);
				list.add(chuwei);
				boolean[] b={true};
				Modbus.writeCoil(com, row, col-1, b);
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
		return list;		
	}
	
}
