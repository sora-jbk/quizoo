package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import frame.ApplicationController;
import frame.context.RequestContext;
import frame.context.ResponseContext;

public class FrontServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ApplicationController appctl = null;
		
		RequestContext reqc = appctl.getRequest(req);
		
		ResponseContext respc = appctl.handleRequest(reqc);
		
		
		appctl.handleResponse(reqc, respc);
		
	}
} 
