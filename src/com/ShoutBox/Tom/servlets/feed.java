package com.ShoutBox.Tom.servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.utils.UUIDs;

import com.datastax.driver.core.Cluster;

import com.ShoutBox.Tom.lib.*;
import com.ShoutBox.Tom.models.*;
import com.ShoutBox.Tom.stores.*;

/**
 * Servlet implementation class message
 */
@WebServlet({ "/feed", "/feed/*" })
public class feed extends HttpServlet {
	private Cluster cluster;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public feed() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
    	cluster = CassandraHosts.getCluster();
    	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((UserStore) request.getSession().getAttribute("user") != null)
		{
			String[] url = ParseURL.parseURL(request.getRequestURI().toString());
			
			MessageModel sM = new MessageModel();
			sM.setCluster(cluster);
			
			LinkedList<ShoutStore> shouts = new LinkedList<ShoutStore>();
			if(url.length <= 3)
			{
				FollowerModel followerM = new FollowerModel();
				followerM.setCluster(cluster);
				UserStore uS = (UserStore) request.getSession().getAttribute("user");
				String username = uS.getUsername();
				
				shouts = sM.getShouts(username,"", followerM.getFollowers(username));
			}
			else if(url.length <= 5)
			{
				if(url[3].equals("all"))
				{
					shouts = sM.getAllShouts();
				}
				else
				{
					String tag = url[3];
					if(tag.length() < 12)
						shouts = sM.getShouts(tag, "", null);
					else
						shouts = sM.getShouts("", tag, null);
				}
			}
			
			request.setAttribute("Shouts", shouts);
			
			RequestDispatcher rd = request.getRequestDispatcher("http://ac32007.cloudapp.net:8080/ShoutBox/feed.jsp");
			rd.forward(request, response);
		}
		else
			response.sendRedirect("http://ac32007.cloudapp.net:8080/ShoutBox/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((UserStore) request.getSession().getAttribute("user") != null)
		{
			String shoutText = request.getParameter("shoutText");
			shoutText = shoutText.replace("\'", "&#39;");
			UserStore uS = (UserStore) request.getSession().getAttribute("user");
			String username = uS.getUsername();
			
			
			String[] url = ParseURL.parseURL(request.getRequestURI().toString());
			
			if(url.length > 3)
			{}
			else
			{
				MessageModel messModel = new MessageModel ();
				messModel.setCluster(cluster);
				
				messModel.newShout(username, shoutText);
			}
			
			response.sendRedirect("http://ac32007.cloudapp.net:8080/ShoutBox/feed");
		}
		else
			response.sendRedirect("http://ac32007.cloudapp.net:8080/ShoutBox/");
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] url = ParseURL.parseURL(request.getRequestURI().toString());
		
		UserStore uS = (UserStore) request.getSession().getAttribute("user");
		String username = uS.getUsername();
		
		MessageModel sM = new MessageModel();
		sM.setCluster(cluster);
		
		sM.deleteShout(username, url[3]);
		doGet(request, response);
	}
	
}
