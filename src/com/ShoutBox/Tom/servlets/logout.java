package com.ShoutBox.Tom.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ShoutBox.Tom.stores.*;

/**
 * Servlet implementation class logout
 */
@WebServlet({ "/logout", "/logout/*", "/logout*" })
public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((UserStore) request.getSession().getAttribute("user") == null)
			response.sendRedirect("http://localhost:8080/ShoutBox");
		else
			doPut(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((UserStore) request.getSession().getAttribute("user") == null)
			response.sendRedirect("http://localhost:8080/ShoutBox");
		else
			doPut(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((UserStore) request.getSession().getAttribute("user") == null)
			response.sendRedirect("http://localhost:8080/ShoutBox");
		else
		{
			request.getSession().setAttribute("user", null);
			response.sendRedirect("http://localhost:8080/ShoutBox");
		}
	}

}
