package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import frame.ApplicationController;
import frame.context.RequestContext;
import frame.context.ResponseContext;
import web.context.HttpRequestContext;
import web.context.HttpResponseContext;

public class WebApplicationController implements ApplicationController{
	
	HttpServletRequest req = null;
	HttpServletResponse res = null;

	@Override
	public RequestContext getRequest(Object request) {
		this.req = (HttpServletRequest)request;
		return new HttpRequestContext((HttpServletRequest)request);
	}

	@Override
	public ResponseContext handleRequest(Object response) {
		this.res = (HttpServletResponse)response;
		return new HttpResponseContext((HttpServletResponse)response);
	}

	@Override
	public void handleResponse(RequestContext request, ResponseContext response) {
		String target = request.getTarget();
		if(target != null) {
			RequestDispatcher disp = req.getRequestDispatcher("target");
			try {
				disp.forward(req, res);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}
} 
