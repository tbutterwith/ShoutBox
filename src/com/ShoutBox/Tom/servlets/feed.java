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
				shouts = sM.getShouts("","");
			}
			else if(url.length <= 5)
			{
				String tag = url[3];
				if(tag.length() < 12)
					shouts = sM.getShouts(tag, "");
				else
					shouts = sM.getShouts("", tag);
			}
			
			
			String uuid = UUIDs.timeBased().toString();
			
			System.out.println(uuid);
			
			request.setAttribute("Shouts", shouts);
			
			RequestDispatcher rd = request.getRequestDispatcher("/feed.jsp");
			rd.forward(request, response);
		}
		else
			response.sendRedirect("/ShoutBox/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((UserStore) request.getSession().getAttribute("user") != null)
		{
			String shoutText = request.getParameter("shoutText");
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
			
			doGet(request, response);
		}
		else
			response.sendRedirect("/ShoutBox");
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
		System.out.println("win");
		String[] url = ParseURL.parseURL(request.getRequestURI().toString());
		
		UserStore uS = (UserStore) request.getSession().getAttribute("user");
		String username = uS.getUsername();
		
		MessageModel sM = new MessageModel();
		sM.setCluster(cluster);
		
		sM.deleteShout(username, url[3]);
		doGet(request, response);
	}
	
}
