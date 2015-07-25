package com.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sql.Getinfo;
import com.sql.Getinfoout;
import com.sql.Setinfo;

/**
 * Servlet implementation class OutboundServlet
 */
@WebServlet("/OutboundServlet")
public class OutboundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OutboundServlet() {
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
        String dan= req.getParameter("dan");
        String san= req.getParameter("san");
        System.out.println(dan);
        int dcount=Integer.parseInt(dan);
	    int scount=Integer.parseInt(san);
        System.out.println(dcount);
        Setinfo.deltemp();
        PrintWriter out=resp.getWriter();
        if(Getinfoout.getalldan()>=dcount&&Getinfoout.getallsan()>=scount){
         	for(int i=1;i<(Getinfo.getsumcom()+1);i++){
         		int tdan=Getinfoout.getcomdan(i);
         		int tsan=Getinfoout.getcomsan(i);
                String comna=Getinfo.getcomname(i);
        	    if(tdan>=dcount&&tsan>=scount){
        	    	Setinfo.inserttemp(comna,dcount,scount);
        	    	 System.out.println("插入");
        		break;
        	    }
        	    else{
        	    	tdan=tdan>dcount?dcount:tdan;
        	    	tsan=tsan>scount?scount:tsan;
        	    	Setinfo.inserttemp(comna,tdan,tsan);
        	    	dcount=((dcount-tdan)>0)?dcount-tdan:0;
        	    	scount=((scount-tsan)>0)?scount-tsan:0;
        	    }
        	}
         	System.out.println("跳转");
         	req.getSession().setAttribute("mes", "1");
         	out.print("成功");
        }
            else{out.print("失败");}
      
	}

}