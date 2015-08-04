package com.action;
import java.io.*;  
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

import javax.servlet.*;  
import javax.servlet.http.*;  

import net.sf.json.JSONArray;

import javax.servlet.annotation.WebServlet;

import com.model.Student;
import com.model.Chuwei;
import com.dao.*;
import com.rxtx.Modbus;
import com.service.StudentService;
import com.service.impl.StudentServiceImpl;
import com.sql.Getinfo;
public class PankuchushiServlet extends HttpServlet {
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
		HttpSession session = req.getSession(true);
        int id= (int)session.getAttribute("mes");
        System.out.println("读取session："+id);
		List<String> list = new ArrayList<String>();
		JSONArray jsons=new JSONArray();
		PrintWriter out=resp.getWriter();
		int[] getrow=Getinfo.getrownum(1);
		int danrownum=getrow[0];
		int sumrownum=getrow[1];
		String com= Getinfo.getcomname(1);
		String[] ret=Modbus.read8Input(com,sumrownum);
		for(int j=getrow[0];j<getrow[1];j++){
			ret[j]=ret[j].substring(0, 5)+"000";
		}
		String des="1";
		int back=0;
		for(int i=0;i<sumrownum;i++){
		    int cnt=sumfind(ret[i],des);
		    back=back+cnt;
		}
		
		//int back=getchuId(id);      //获取DI返回的储位有表的总数量
		System.out.println("监测串口返回"+back);
		//back=10;   //test data
		if(back>0){
       		list.add(String.valueOf(back));
       	    jsons = JSONArray.fromObject(list);
       		out.println(jsons);
       		list.clear();
		}
		else{
			list.add("0");
       	    jsons = JSONArray.fromObject(list);
       		out.println(jsons);
       		list.clear();
		}
	} 
public static int sumfind(String aaa,String des){
	
	int cnt = 0;
    int offset = 0;
    if(aaa== null || aaa.equals("")){
    	return 0;
    }
    else{
    while((offset = aaa.indexOf(des, offset)) != -1){
        offset = offset + des.length();
        cnt++;
    }}
	return cnt;
	
}
}  