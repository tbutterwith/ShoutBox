package com.ShoutBox.Tom.servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ShoutBox.Tom.lib.CassandraHosts;
import com.ShoutBox.Tom.models.*;
import com.ShoutBox.Tom.stores.*;
import com.datastax.driver.core.*;

/**
 * Servlet implementation class profile
 */
@WebServlet({ "/profile", "/profile*", "/profile/*" })
public class profile extends HttpServlet {
	private Cluster cluster;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public profile() {
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
		String[] url = ParseURL.parseURL(request.getRequestURI().toString());
		ProfileModel profModel = new ProfileModel();
		profModel.setCluster(cluster);
		if((UserStore) request.getSession().getAttribute("user") == null)
		{
			if(url.length == 4)
			{
				ProfileStore profileInfo = profModel.getProfileInfo(url[3]);
				request.setAttribute("profile", profileInfo); //Set a bean with the list in it
				RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");

				rd.forward(request, response);
			}
			else
				response.sendRedirect("http://ac32007.cloudapp.net:8080/ShoutBox/");
		}
		else
		{
			if(url.length <= 3)
			{
				UserStore uS = (UserStore) request.getSession().getAttribute("user");
				String username = uS.getUsername();
				ProfileStore profileInfo = profModel.getProfileDetail(username);
				request.setAttribute("profile", profileInfo); //Set a bean with the list in it
				RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
				rd.forward(request, response);
			}
			else
			{
				ProfileStore profileInfo = profModel.getProfileInfo(url[3]);
				request.setAttribute("profile", profileInfo); //Set a bean with the list in it
				RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");

				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((UserStore) request.getSession().getAttribute("user") == null)
			response.sendRedirect("http://ac32007.cloudapp.net:8080/ShoutBox/");
		else
		{
			ProfileModel profileMod = new ProfileModel();
			profileMod.setCluster(cluster);
			
			UserStore uS = (UserStore) request.getSession().getAttribute("user");
			String username = uS.getUsername();
			String password = request.getParameter("password");
			
			if(!profileMod.checkPassword(username, password))
			{
				MessageStore message = new MessageStore();
				message.setMessage("Incorrect password");
				request.setAttribute("errorMessage", message); //Set a bean with the list in it
				
				ProfileStore profileInfo = profileMod.getProfileDetail(username);
				request.setAttribute("profile", profileInfo); //Set a bean with the list in it
				RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
				rd.forward(request, response);
			}
			else
			{
				String newPassword = request.getParameter("newPassword");
				if(newPassword != "")
				{
					password = newPassword;
				}
				String primaryEmail = request.getParameter("primaryEmail");
				String secondaryEmail = request.getParameter("secondaryEmail");
				profileMod.updateProfile(username, password, primaryEmail, secondaryEmail);
				System.out.println("test");
				response.sendRedirect("profile");
			}
			
		}
	}
	
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] url = ParseURL.parseURL(request.getRequestURI().toString());
		
		ProfileModel profileMod = new ProfileModel();
		profileMod.setCluster(cluster);
		UserStore uS = (UserStore) request.getSession().getAttribute("user");
		String username = uS.getUsername();
		String password = request.getParameter("password");
		
		if(!profileMod.checkPassword(username, password))
		{
			MessageStore message = new MessageStore();
			message.setMessage("Incorrect password");
			request.setAttribute("errorMessage", message); //Set a bean with the list in it
			
			ProfileStore profileInfo = profileMod.getProfileDetail(username);
			request.setAttribute("profile", profileInfo); //Set a bean with the list in it
			RequestDispatcher rd = request.getRequestDispatcher("http://ac32007.cloudapp.net:8080/ShoutBox/profile.jsp");
			rd.forward(request, response);
		}
		else
		{
			profileMod.deleteUser(username);
			profileMod.deleteAllShouts(username);
		}
	}

}
