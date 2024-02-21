package web.context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import db.bean.UserInfoBean;
import frame.context.RequestContext;
import frame.exception.ResourceException;

public class HttpRequestContext implements RequestContext{
	HttpServletRequest req = null;
	HttpSession session = null;
	public HttpRequestContext(HttpServletRequest req) {
		this.req = req;
		this.session = req.getSession();
	}
	public HttpServletRequest getRequest() {
		return req;
	}
	@Override
	public void setAttribute(String key, Object value) {
		req.setAttribute(key, value);
	}
	@Override
	public String[] getParameter(String key) {
		return req.getParameterValues(key);
	}

	@Override
	public String getMessageBody() throws ResourceException {
		StringBuilder sb = new StringBuilder();
		String line;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()))) {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        }catch(IOException e) {
        	throw new ResourceException(e.getMessage(), e);
        }
        
        return sb.toString();
	}
	@Override
	public String getTargetServiceKey() {
		return req.getRequestURI().toString().replaceFirst("/quizoo/","");
	}
	@Override
	public void setUser(UserInfoBean user) {
		session.setAttribute("user",user);
		
	}
	@Override
	public UserInfoBean getUser() {
		return (UserInfoBean)session.getAttribute("user");
	}
	@Override
	public void invalidatekeUser() {
		session.invalidate();
	}
} 
