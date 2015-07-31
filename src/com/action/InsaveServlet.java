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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DbManager;
import com.rxtx.Modbus;
import com.sql.Getinfo;
import com.sql.Setinfo;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class InsaveServlet
 */
@WebServlet("/InsaveServlet")
public class InsaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsaveServlet() {
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
        int repeated = 0;
        int id = Integer.parseInt(req.getParameter("id"));
		String[] stringcode;
		String[] stringid;
		List<String> list = new ArrayList<String>();
		JSONArray jsons=new JSONArray();
		PrintWriter out=resp.getWriter();
		int[] getrow=Getinfo.getrownum(id);
		int sumrownum=getrow[1];
		String com= Getinfo.getcomname(id);
		
		if(scode.indexOf(",")>0){
			stringcode= scode.split(","); 
			stringid= sid.split(",");
			System.out.println("入库数量大于一");
			if(!CheckIfCodeRepeated(id,stringcode))
			    repeated = 1;
		}
		else {
			stringcode=new String[1];
			stringid=new String[1];
			System.out.println("数组已经初始化：");
			stringcode[0]=scode;
			stringid[0]=sid;
			System.out.println("入库数量为一："+stringid[0]);
		}
		LogSaver ls = new LogSaver();
		String uname = (String) req.getSession().getAttribute("uname");
		String authority = (String)req.getSession().getAttribute("authority");
		if(repeated == 0)
		{
			String[] str=Getinfo.getallismeter(id,stringid);
			System.out.println(Arrays.toString(str));
			String[] ret=Modbus.read8Input(com,sumrownum);
			System.out.println("数据库操作后应返回："+Arrays.toString(str));
			int back=0;
			for(int i=0;i<sumrownum;i++){
			  if(str[i].equals(ret[i])){
				  back=back+1;
				  System.out.println(i+"每次back"+back);
			     }
			  else{
				  System.out.println(i+"不相同"+str[i]+"  "+ret[i]);
			  }
			}
			System.out.println("监测串口返回back数据"+back);
			if(back==sumrownum){
				updateChuweiList(stringcode,stringid,com);
				Modbus.write8coilofflight(com,sumrownum);   //关灯
	    		int a=Getinfo.getifnext(id);
	    		if(a==1){
	    			list.add("继续");
	    			ls.saveinlog("in", uname, authority);
	    			req.getSession().setAttribute("mes", id+1); 
	    		}
	    		else{
	    			list.add("成功");
	    			ls.saveinlog("in", uname, authority);
	    		    Setinfo.deltemp();
	    		}
	    	    jsons = JSONArray.fromObject(list);
	    		out.println(jsons);
	    		list.clear();	
			}
			else if(back==-sumrownum){
				list.add("串口通讯错误");
				Setinfo.deltemp();
				ls.saveinlog("infa", uname, authority);
				Modbus.write8coilofflight(com,sumrownum);   //关灯
				jsons = JSONArray.fromObject(list);
				out.println(jsons);
				list.clear();
			}
			else {
				list.add("失败，储位异常，存在错误，请将初为保持出库前状态");
				ls.saveinlog("infa", uname, authority);
				Modbus.write8coilofflight(com,sumrownum);   //关灯
				Setinfo.deltemp();
				jsons = JSONArray.fromObject(list);
				out.println(jsons);
				list.clear();
			}
		}
		else
		{
			list.add("条码重复");
			Setinfo.deltemp();
			ls.saveinlog("infa", uname, authority);
			Modbus.write8coilofflight(com,sumrownum);   //关灯
			jsons = JSONArray.fromObject(list);
			out.println(jsons);
			list.clear();
		}
	}
    public static boolean CheckIfCodeRepeated(int num,String[] code)
    {
    	int i, j;
    	for(i=0;i<code.length-1;i++)
    	{
    		for(j=i+1;j<code.length;j++)
    		{
    			if(code[i].equals(code[j]))
    			{
    				System.out.println("YEMIANchongfu!");
    				return false;
    			}
    				
    		}
    	}
    	if(Getinfo.checkrepeatedindatabase(num, code))
    	{
    		System.out.println("SHUJUKUchongfu!");
    		return false;
    	}
    		
    	return true;
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
			pst = conn.prepareStatement("update chuwei set meter_code = '"+mcode+"' WHERE id='"+id+"' and com='"+com+"'");
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
}
