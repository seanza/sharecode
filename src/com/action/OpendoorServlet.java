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
import com.rxtx.Aboutbyte;
import com.rxtx.Test;
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
        Test d=new Test(); 
        byte[] c1=Aboutbyte.onlight(doorinfo[0], doorinfo[1]);
        byte[] c2=Aboutbyte.offsinglelight(doorinfo[0], doorinfo[1]);
        String com= Getinfo.getcomname(id);
   	    d.openSerialPortb(c1,com);
   	    
   	 try {
			Thread.sleep(7000);
			d.openSerialPortb(c2,com);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
