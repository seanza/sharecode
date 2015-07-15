package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.dao.DbManager;
import com.rxtx.Aboutbyte;
import com.rxtx.Test;
import com.sql.Getinfo;
import com.sql.Getinfoout;
import com.sql.Setinfo;

/**
 * Servlet implementation class OutsaveServlet
 */
@WebServlet("/OutsaveServlet")
public class OutsaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OutsaveServlet() {
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
        String scode= req.getParameter("scode");
        String sid = req.getParameter("sid");
        int id = Integer.parseInt(req.getParameter("id"));
		String[] stringcode;
		String[] stringid;
		if(scode.indexOf(",")>0){
			stringcode= scode.split(","); 
			stringid= sid.split(",");
			System.out.println("出库数量大于一");
		}
		else {
			stringcode=new String[1];
			stringid=new String[1];
			System.out.println("数组已经初始化：");
			stringcode[0]=scode;
			stringid[0]=sid;
			System.out.println("出库数量为一："+stringid[0]);
		}
		List<String> list = new ArrayList<String>();
		JSONArray jsons=new JSONArray();
		PrintWriter out=resp.getWriter();
		int[] getrow=Getinfo.getrownum(id);
		int sumrownum=getrow[1];
		int[] getre=new int[sumrownum];
		String[] str=Getinfoout.getallismeter(id,stringid);
		for(int i=0;i<sumrownum;i++){
		  byte[] di= Aboutbyte.getdi(i+1);
		  getre[i]=By(di,Aboutbyte.binaryString2hexString(str[i]),Getinfo.getcomname(id));
		}
		int back=0;
		for(int i=0;i<getre.length;i++){
			back=back+getre[i];
		}
		//back=7;
		String com= Getinfo.getcomname(id);
		System.out.println("监测串口返回back数据"+back);
		if(back==sumrownum){
			updateChuweiList(stringcode,stringid,com);
			Test b=new Test(); 
			for(int i=1;i<sumrownum+1;i++){
				b.openSerialPortb(Aboutbyte.offlight(i),com);
			}
    		int a=Getinfo.getifnext(id);
    		if(a==1){
    			list.add("继续");
    			req.getSession().setAttribute("mes", id+1); 
    		}
    		else{
    			list.add("成功");
    		    Setinfo.deltemp();
    		}
    	    jsons = JSONArray.fromObject(list);
    		out.println(jsons);
    		list.clear();	
		}
		else if(back==-sumrownum){
			list.add("串口通讯错误");
			Test b=new Test();
			for(int i=1;i<sumrownum+1;i++){
				b.openSerialPortb(Aboutbyte.offlight(i),com);
			}
			jsons = JSONArray.fromObject(list);
			out.println(jsons);
			list.clear();
		}
		else {
			list.add("失败，储位异常，存在错误，请将初为保持出库前状体");
			Test b=new Test();
			for(int i=1;i<sumrownum+1;i++){
				b.openSerialPortb(Aboutbyte.offlight(i),com);
			}
			jsons = JSONArray.fromObject(list);
			out.println(jsons);
			list.clear();
		}
	}
    public static void updateChuweiList(String[] sql1,String[] sql2,String com){
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DbManager.getConnection();
			conn.setAutoCommit(false);
			for(int i=0;i<sql1.length;i++){
			String mcode=sql1[i];
			int id=Integer.parseInt(sql2[i]);
			pst = conn.prepareStatement("update chuwei set meter_code =Null WHERE id='"+id+"' and com='"+com+"'");
			int x=pst.executeUpdate();
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
	}
    public static int By(byte[] cc,String ss,String com) { 
 	   String ret = ""; 
 	   Test a=new Test(); 
        a.openSerialPortb(cc,com);
        String m=new String();
        int i;
     try {
 			Thread.sleep(360);
 			ret=a.mt;
 			System.out.println("串口返回数据："+ret+"--与现有对比"+ss);
 		} catch (InterruptedException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
      if(ret!=""&&ret!=null){
 	    m=ret.substring(6,8);
 	    System.out.println("串口返回数据切分："+m+"--与现有对比"+ss);
      if(m.equals(ss)){
 	        return 1;
      }
      else{
  	   return 0;
      }
      }
      else{
      	 return -1;
      }
   }
}
