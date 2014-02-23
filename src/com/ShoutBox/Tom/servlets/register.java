package com.ShoutBox.Tom.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ShoutBox.Tom.lib.*;
import com.ShoutBox.Tom.models.*;
import com.ShoutBox.Tom.stores.*;

import com.datastax.driver.core.*;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private Cluster cluster;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config) throws ServletException {
		cluster = CassandraHosts.getCluster();
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((UserStore) request.getSession().getAttribute("user") == null)
			response.sendRedirect("http://ac32007.cloudapp.net:8080/ShoutBox/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("newUsername");
		String password = request.getParameter("newPassword");
		String email = request.getParameter("email");
		
		RegisterModel reg = new RegisterModel();
		reg.setCluster(cluster);
		
		if(reg.checkUsername(username))
		{
			MessageStore message = new MessageStore();
			message.setMessage("This username is already taken");
			request.setAttribute("errorMessage", message); //Set a bean with the list in it
			RequestDispatcher rd = request.getRequestDispatcher("http://ac32007.cloudapp.net:8080/ShoutBox/index.jsp");

			rd.forward(request, response);
		}
		else
		{
			reg.newUser(username, email, password);
			UserStore uStore = new UserStore();
			uStore.setUsername(username);
			request.getSession().setAttribute("user", uStore);
			
			response.sendRedirect("feed");
		}
		
		//response.sendRedirect("http://ac32007.cloudapp.net:8080/ShoutBox");
	}

}
