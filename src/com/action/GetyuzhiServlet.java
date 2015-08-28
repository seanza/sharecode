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
import com.model.Alert;
import com.sql.Getinfo;

/**
 * Servlet implementation class GetyuzhiServlet
 */
@WebServlet("/GetyuzhiServlet")
public class GetyuzhiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetyuzhiServlet() {
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
        int[] yuzhi=Getinfo.getyuzhi();
        String teml=Integer.toString(yuzhi[1]);
        String temh=Integer.toString(yuzhi[2]);
        String wetl=Integer.toString(yuzhi[3]);
        String weth=Integer.toString(yuzhi[4]);
        String chul=Integer.toString(yuzhi[5]);
        List<String> list = new ArrayList<String>();
		list.add(teml);
		list.add(temh);
		list.add(wetl);
		list.add(weth);
		list.add(chul);
        JSONArray json = new JSONArray();  
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
	
}
