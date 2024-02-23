package com.store.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CatalogServlet
 */
@WebServlet("/CatalogServlet")
public class CatalogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CatalogServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			String itemSku = request.getParameter("sku");
			CatalogItem catalogItem = Catalog.getItem(itemSku);
			
			response.addCookie(new Cookie("cookie" , "value"));
			response.setHeader("header" , "value");
			response.getWriter().append(catalogItem.getName()); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			String name = request.getParameter("name");
			String manufacturer = request.getParameter("manufacturer");
			String sku = request.getParameter("sku");

			Catalog.addItem(new CatalogItem(name, manufacturer, sku));
			
			response.getWriter().append("product added");

		}

}
