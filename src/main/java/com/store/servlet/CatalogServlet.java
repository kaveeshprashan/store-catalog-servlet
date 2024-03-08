package com.store.servlet;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CatalogServlet
 */
@WebServlet(urlPatterns = "/CatalogServlet" , asyncSupported = true)
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
			
			request.setAttribute("message", catalogItem.getName());
			RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
			dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			AsyncContext asyncContext = request.startAsync();
					
			asyncContext.start(new Runnable() {
	
				@Override
				public void run() {
					try {
						Thread.sleep(5000);
						System.out.println("Print the response");
						System.out.println("Reponse returned by: " + Thread.currentThread().getName());
						
						String name = request.getParameter("name");
						String manufacturer = request.getParameter("manufacturer");
						String sku = request.getParameter("sku");
						Catalog.addItem(new CatalogItem(name, manufacturer, sku));
						
						response.getWriter().append("product added");
						asyncContext.complete();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			System.out.println("Initial Request: " + Thread.currentThread().getName());
	}

}
