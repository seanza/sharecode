package com.action;

import java.io.*;  
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.*;  
import javax.servlet.http.*;  

import net.sf.json.JSONArray;

import javax.servlet.annotation.WebServlet;

import com.dao.*;
import com.rxtx.Modbus;
import com.sql.Getinfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpendoorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpendoorServlet() {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        resp.setContentType("text/html;charset=UTF-8");  
        resp.setHeader("Cache-Control","no-cache");  
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		int id=Integer.parseInt(req.getParameter("id"));
		int[] doorinfo=Getinfo.getcomdoor(id);
        String com= Getinfo.getcomname(id);
		LogSaver ls = new LogSaver();
		//String uname = (String) req.getSession().getAttribute("uname");
		//String authority = (String)req.getSession().getAttribute("authority");
   	    boolean[] t={true};
   	    Modbus.writeCoil(com, doorinfo[0],doorinfo[1]-1,t);
   	    ls.saveinlog("opendooruser", "admin", "管理员");
   	    System.out.println("opendoor");
   	    try {
			Thread.sleep(7000);
			boolean[] f={false};
			Modbus.writeCoil(com, doorinfo[0],doorinfo[1]-1,f);
			System.out.println("closedoor");
			ls.saveinlog("closedooruser", "admin", "管理员");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
