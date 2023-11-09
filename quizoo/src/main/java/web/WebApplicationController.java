package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import frame.ApplicationController;
import frame.context.RequestContext;
import frame.context.ResponseContext;
import web.context.HttpRequestContext;
import web.context.HttpResponseContext;

public class WebApplicationController implements ApplicationController{

	@Override
	public RequestContext getRequest(Object request) {
		return new HttpRequestContext((HttpServletRequest)request);
	}

	@Override
	public ResponseContext handleRequest(Object response) {
		return new HttpResponseContext((HttpServletResponse)response);
	}

	@Override
	public void handleResponse(RequestContext request, ResponseContext response) {
		
	}
} 
