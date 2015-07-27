package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Student;
import com.service.StudentService;
import com.service.impl.StudentServiceImpl;

public class StudentServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StudentService studentService;

	public StudentServlet() {
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
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		System.out.println(userName);
		System.out.println(userPwd);
		
		if(StringUtil.isBlank(userName)&&(StringUtil.isBlank(userPwd))){
			request.setAttribute("error","姓名和密碼錯誤！");
			request.getSession().setAttribute("login", "/login"); //setAttribute�������
			request.getRequestDispatcher("/login").forward(request, response); //getRequestDispatcher��ʾrequest������ɣ��·�����һ��ҳ��
			return;
		}
		
		List<Student> list = studentService.findName(new Student(userName,userPwd));
		PrintWriter out = response.getWriter();
		LogSaver ls = new LogSaver();
		if(list.size()>0){
			System.out.println(list.get(0).getGroup());
			request.getSession().setAttribute("uname", userName);
			if(list.get(0).getGroup().equals("admin")){
				System.out.println(list.get(0).getGroup());
				ls.saveinlog("login", userName, "(admin)");
				request.getSession().setAttribute("authority", "admin");
				out.print("<script>window.location.href='n3.htm'</script>");
			}
			if(list.get(0).getGroup().equals("user")){
				System.out.println(list.get(0).getGroup());
				ls.saveinlog("login", userName, "(user)");
				request.getSession().setAttribute("authority", "user");
				out.print("<script>window.location.href='out1.htm'</script>");
			}
			//request.getSession().setAttribute("list", list);
			//request.getRequestDispatcher("chapter1.jsp").forward(request, response);
		}else{
			out.print("<script>alert('用户名或密码错误');</script>");
			out.print("<script>window.location.href='n2.jsp'</script>");
			//out.print("var d = dialog({lock:true,title: '消息',content: '用户名或密码错误',okValue: '确定',ok: function(){window.location.href='n3.htm';},cancel: false,});d.showModal();");
			return;
		}
	}
}
