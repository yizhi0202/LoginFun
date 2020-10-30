package com.cn;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String error = null;
		boolean flag = false;
		java.sql.Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("成功加载驱动程序");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mysql_y?serverTimezone=UTC","root","123456");//Mysql_y 为数据库名可以通过命令行查看 安装数据库之后不代表有了数据库需要自己创建然后取名
			Statement stmt = conn.createStatement();
			String sql;
			sql="select*from users";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				if(name.equals(rs.getString(2))&&pass.equals(rs.getString(3)))
				{
					flag = true;
					request.setAttribute("name", name);
					request.setAttribute("pass", pass);
					RequestDispatcher dis = request.getRequestDispatcher("loginsuccess.jsp");
					dis.forward(request, response);
					return;
				}
				else if(name.equals(rs.getString(2))&&!pass.equals(rs.getString(3)))
				{
					error = "密码输入错误！";
					flag = true;
					request.setAttribute("error", error);
					RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
					dis.forward(request, response);
					return;
				}
				else if(pass.equals(rs.getString(3)))
				{
					error = "账户名输入错误！";
					flag = true;
					request.setAttribute("error", error);
					RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
					dis.forward(request, response);
					return;
				}
			}
			if(!flag)
			{
				error = "您还没有注册，请先注册！";
				request.setAttribute("error",error);
				RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
				dis.forward(request, response);
				return;
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*for(int i = 0; i < Comm.list.size();++i) 
		{
			if(name.equals(Comm.list.get(i).getName())&&pass.equals(Comm.list.get(i).getPass()))
			{
				request.setAttribute("name", name);
				RequestDispatcher dis = request.getRequestDispatcher("loginsuccess.jsp");
				dis.forward(request, response);
				flag = true;
				return;
			}
			if(name.equals(Comm.list.get(i).getName()))
			{
				request.setAttribute("msg", "请检查您的用户名和密码。");
				request.setAttribute("error", "重新输入");
				request.setAttribute("url", "login.jsp");
				RequestDispatcher dis = request.getRequestDispatcher("loginerror.jsp");
				dis.forward(request, response);
				flag = true;
				return;
			}
				
		}*/
		
		
	}

}
