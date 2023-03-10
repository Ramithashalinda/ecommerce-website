package com.digitalvision.srv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.digitalvision.beans.UserBean;
import com.digitalvision.dao.UserDao;
import com.digitalvision.dao.UserDaoImpl;

/**
 * Servlet implementation class RegisterSrv
 */
@WebServlet("/RegisterSrv")
public class RegisterSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public RegisterSrv() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		String userName = request.getParameter("username");
		Long mobileNo = Long.parseLong(request.getParameter("mobile"));
		String emailId = request.getParameter("email");
		String address = request.getParameter("address");
		int pinCode = Integer.parseInt(request.getParameter("pincode"));
		String password = request.getParameter("password");
		String billaddress = request.getParameter("billaddress");
		
		UserBean user = new UserBean(userName,mobileNo,emailId,address,billaddress,pinCode,password);
		
		UserDaoImpl dao = new UserDaoImpl();
		
		String status = dao.registerUser(user);
		
		RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
		
		request.setAttribute("message", status);
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
