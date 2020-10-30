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
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
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
	/*public  boolean Add(String name, String pass) 
	{
		Connection conn = null;
		Statement stmt = null;
		String sql;
		boolean flag = true;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("成功加载驱动程序");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mysql_y?serverTimezone=UTC","root","123456");//Mysql_y 为数据库名可以通过命令行查看 安装数据库之后不代表有了数据库需要自己创建然后取名
			//check the account if is exeist
			sql="select*from users";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				if(name.equals(rs.getString(2)))
				{
					request.setAttribute("name", name);
					RequestDispatcher dis = request.getRequestDispatcher("loginsuccess.jsp");
					dis.forward(request, response);
					flag = true;
					return flag; 
				}
			}
			
			PreparedStatement ps = null;
			sql = "insert into users(name,pass) values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1,name);
			ps.setString(2,pass);
			ps.executeUpdate();
			ps.close();
;			/*stmt = conn.createStatement();
			sql = "insert into users(name,pass) values(Na,Pa)";//users 为自己的数据表名 需要在数据库中创建  
			int result = stmt.executeUpdate(sql);
			if(result != -1)
			{
				
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			System.out.println("操作错误！");
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}*/

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
		RequestDispatcher dispatcher;
		
		if(name.equals("")||pass.equals("")||name == null||pass==null)
		{
			error="账户名或者密码不能为空！";
			request.setAttribute("error", error);
			dispatcher = request.getRequestDispatcher("regist.jsp");
			dispatcher.forward(request, response);
		}
		else {
			Connection conn = null;
			Statement stmt = null;
			String sql;
			boolean flag = true;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("成功加载驱动程序");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mysql_y?serverTimezone=UTC","root","123456");//Mysql_y 为数据库名可以通过命令行查看 安装数据库之后不代表有了数据库需要自己创建然后取名
				//check the account if is exeist
				//判断账户名是否已经被注册
				sql="select*from users";
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					if(name.equals(rs.getString(2)))
					{
						flag = false;
						error = "账户名已经被注册，请换一个账户名。";
						request.setAttribute("error", error);
						RequestDispatcher dis = request.getRequestDispatcher("regist.jsp");
						dis.forward(request, response);
						break;
					}
				}
				//store the account and password
				if(flag)
				{
					
					PreparedStatement ps = null;
					sql = "insert into users(name,pass) values(?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1,name);
					ps.setString(2,pass);
					ps.executeUpdate();
					ps.close();
				}
				
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}catch(SQLException e) {
				System.out.println("操作错误！");
				e.printStackTrace();
			}finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			request.setAttribute("name", name);
			request.setAttribute("pass", pass);
			RequestDispatcher dis = request.getRequestDispatcher("registsuccess.jsp");
			dis.forward(request, response);
		}
	}
		
}


