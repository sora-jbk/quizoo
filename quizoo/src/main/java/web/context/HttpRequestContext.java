package web.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import frame.context.RequestContext;

public class HttpRequestContext implements RequestContext{
	
	HttpServletRequest req = null;
	HttpSession session = null;
	String[] allowedMethods = null;
	String target = null;
	
	public HttpRequestContext(HttpServletRequest req) {
		this.req = req;
		this.session = req.getSession();
	}
	
	@Override
	public void setAttrubute(String key, Object value) {
		req.setAttribute(key, value);
	}

	@Override
	public String[] getParameter(String key) {
		return req.getParameterValues(key);
	}

	@Override
	public void setId(String id) {
		session.setAttribute("id", id);
	}

	@Override
	public String getId() {
		return (String)session.getAttribute("id");
	}

	@Override
	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public String getRequestTarget() {
		return req.getRequestURI();
	}
	
} 
