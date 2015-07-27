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
import com.rxtx.Test;
import com.rxtx.SerialReader;
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
		int back=getchuId(id);      //获取DI返回的储位有表的总数量
		System.out.println("监测串口返回"+back);
		//back=3;   //test data
		switch (back)
    	{
    	   case -9:
       		list.add(String.valueOf(back));
       	    jsons = JSONArray.fromObject(list);
       		out.println(jsons);
       		list.clear();
       		break;
    	   default:
	    	list.add(String.valueOf(back));
			jsons = JSONArray.fromObject(list);
			out.println(jsons);
			list.clear();
			break;
		}
	}

public static int getchuId(int id){
	byte[] b1={0x01,0x02,0x00,0x00,0x00,0x0C,(byte) 0x78,0x0F};//01 02 00 00 00 0C 78 0F 
	byte[] b2={0x02,0x02,0x00,0x00,0x00,0x0C,(byte) 0x78,0x3C};//02 02 00 00 00 0C 78 3C
	byte[] b3={0x03,0x02,0x00,0x00,0x00,0x0C,(byte) 0x79,(byte) 0xED};
	byte[] b4={0x04,0x02,0x00,0x00,0x00,0x0C,(byte) 0x78,(byte) 0x5A};
	byte[] b5={0x05,0x02,0x00,0x00,0x00,0x0C,(byte) 0x79,(byte) 0x8B};
	byte[] b6={0x06,0x02,0x00,0x00,0x00,0x0C,(byte) 0x79,(byte) 0xB8};
	byte[] b7={0x07,0x02,0x00,0x00,0x00,0x0C,(byte) 0x78,(byte) 0x69};
	byte[] b8={0x08,0x02,0x00,0x00,0x00,0x0C,(byte) 0x78,(byte) 0x96};
	byte[] b9={0x09,0x02,0x00,0x00,0x00,0x0C,(byte) 0x79,(byte) 0x47};
	int i1=By(b1,id);
	int i2=By(b2,id);
	int i3=By(b3,id);
	int i4=By(b4,id);
	int i5=By(b5,id);
	int i6=By(b6,id);
	int i7=By(b7,id);
	int i8=By(b8,id);
	int i9=By(b9,id);
	int j=i1+i2+i3+i4+i5+i6+i7+i8+i9;
	return j;
}
public static int By(byte[] cc,int id) { 
	   String ret = ""; 
	   Test a=new Test(); 
    a.openSerialPortb(cc,Getinfo.getcomname(id));
    String m=new String();
    int i;
    try {
			Thread.sleep(360);
			ret=a.mt;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     if(ret!=""&&ret!=null){
	    m=ret.substring(6,8);
	    String aaa=hexString2binaryString(m);
		String des="1";
		int cnt=sumfind(aaa,des);
        System.out.println(cnt);
     	return cnt;
     }
     else{
	return -1;
     }
  }
public static String binaryString2hexString(String bString)
{
	if (bString == null || bString.equals("") || bString.length() % 8 != 0)
		return null;
	StringBuffer tmp = new StringBuffer();
	int iTmp = 0;
	for (int i = 0; i < bString.length(); i += 4)
	{
		iTmp = 0;
		for (int j = 0; j < 4; j++)
		{
			iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);
		}
		tmp.append(Integer.toHexString(iTmp));
	}
	return tmp.toString().toUpperCase();
}
public static String reverse(String str) {	
	return new StringBuilder(str).reverse().toString().toUpperCase();
}

public static String hexString2binaryString(String hexString)  
{  
    if (hexString == null || hexString.length() % 2 != 0)  
        return null;  
    String bString = "", tmp;  
    for (int i = 0; i < hexString.length(); i++)  
    {  
        tmp = "0000"  
                + Integer.toBinaryString(Integer.parseInt(hexString  
                        .substring(i, i + 1), 16));  
        bString += tmp.substring(tmp.length() - 4);  
    }  
    return bString;  
}  
public static int sumfind(String aaa,String des){
	
	int cnt = 0;
    int offset = 0;
    while((offset = aaa.indexOf(des, offset)) != -1){
        offset = offset + des.length();
        cnt++;
    }
	return cnt;
	
}
}  