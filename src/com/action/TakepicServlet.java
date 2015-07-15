package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class TakepicServlet
 */
public class TakepicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakepicServlet() {
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
  
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        resp.setContentType("text/html;charset=UTF-8");  
        resp.setHeader("Cache-Control","no-cache");  
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
        String userName= req.getParameter("name");
        String userPwd = req.getParameter("password");
        Webcam webcam = Webcam.getDefault();
		webcam.open();
		Date date=new Date();
		long l=date.getTime();
		BufferedImage image = webcam.getImage();
		ImageIO.write(image, "PNG", new File("D:\\photo\\照片"+l+".png"));
		webcam.close();
		List<String> list = new ArrayList<String>();
		JSONArray jsons=new JSONArray();
		PrintWriter out=resp.getWriter();
        list.add("成功");
    	jsons = JSONArray.fromObject(list);
        out.println(jsons);
    	list.clear();

	}
}
