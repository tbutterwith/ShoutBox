package com.ShoutBox.Tom.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Cluster;

import com.ShoutBox.Tom.lib.*;
import com.ShoutBox.Tom.models.*;
import com.ShoutBox.Tom.stores.*;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Cluster cluster;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException 
    {
    	// TODO Auto-generated method stub
    	cluster = CassandraHosts.getCluster();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(checkLogin(username, password))
		{
			UserStore uStore = new UserStore();
			uStore.setUsername(username);
			request.getSession().setAttribute("user", uStore);
			
			response.sendRedirect("feed");
			
		}
		else
			System.out.println("login failed");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected Boolean checkLogin(String username, String pass)
	{
		loginModel loginMod = new loginModel();
		loginMod.setCluster(cluster);
		
		if(loginMod.checkLogin(username, pass))
			return true;
		else
			return false;
	}
}
