package com.ShoutBox.Tom.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import com.ShoutBox.Tom.lib.*;
import com.ShoutBox.Tom.models.*;
import com.ShoutBox.Tom.stores.*;
import com.datastax.driver.core.*;

/**
 * Servlet implementation class follower
 */
@WebServlet({ "/follower*", "/follower/*" })
public class follower extends HttpServlet {
	private Cluster cluster;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public follower() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		cluster = CassandraHosts.getCluster();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((UserStore) request.getSession().getAttribute("user") != null)
		{
			String[] url = ParseURL.parseURL(request.getRequestURI().toString());
			if(url.length <= 4)
			{
				FollowerModel followerM = new FollowerModel();
				followerM.setCluster(cluster);
				String username;
				
				if(url.length <= 3)
				{
					UserStore uS = (UserStore) request.getSession().getAttribute("user");
					username = uS.getUsername();
				}
				else
					username = url[3];
				Set<String> followers = followerM.getFollowers(username);
				
				request.setAttribute("Followers", followers); //Set a bean with the list in it
				RequestDispatcher rd = request.getRequestDispatcher("/FollowersList.jsp");

				rd.forward(request, response);
				
			}
		}
		else
			response.sendRedirect("http://localhost:8080/ShoutBox");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((UserStore) request.getSession().getAttribute("user") != null)
		{
			String[] url = ParseURL.parseURL(request.getRequestURI().toString());
			if(url.length <= 3)
				response.sendRedirect("http://localhost8080/ShoutBox/feed");
			else
			{
				FollowerModel followerM = new FollowerModel();
				followerM.setCluster(cluster);
				
				UserStore uS = (UserStore) request.getSession().getAttribute("user");
				String username = uS.getUsername();
				
				followerM.newFollower(username, url[3]);
				
				response.sendRedirect("http://localhost:8080/ShoutBox/follower");
			}
		}
		else
			response.sendRedirect("/ShoutBox");
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((UserStore) request.getSession().getAttribute("user") != null)
		{
			String[] url = ParseURL.parseURL(request.getRequestURI().toString());
			if(url.length <= 3)
				response.sendRedirect("http://localhost8080/ShoutBox/feed");
			else
			{
				FollowerModel followerM = new FollowerModel();
				followerM.setCluster(cluster);
				
				UserStore uS = (UserStore) request.getSession().getAttribute("user");
				String username = uS.getUsername();
				
				followerM.deleteFollower(username, url[3]);
				
				response.sendRedirect("http://localhost:8080/ShoutBox/follower");
			}
		}
		else
			response.sendRedirect("/ShoutBox");
	}

}
