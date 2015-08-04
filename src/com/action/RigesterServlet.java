package com.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Student;
import com.service.StudentService;
import com.service.impl.StudentServiceImpl;

public class RigesterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private StudentService studentService;

	public RigesterServlet() {
		studentService = new StudentServiceImpl();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String psd = request.getParameter("psd");
		String group = request.getParameter("group");
		System.out.println(name);
		System.out.println(psd);
		System.out.println(group);
		Student student = new Student(name,psd,group);
		int abc=studentService.create(student);
		PrintWriter out = response.getWriter();
		System.out.println(abc);
		if(abc>0){
			out.print("成功");

		}
		else{
			out.print("<script>alert('失败');</script>");
			out.print("<script>window.location.href='sconfig.jsp'</script>");
		}
	}
}