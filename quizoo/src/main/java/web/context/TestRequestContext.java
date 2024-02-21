package web.context;

import db.bean.UserInfoBean;
import frame.context.RequestContext;
import frame.exception.ResourceException;

public class TestRequestContext implements RequestContext{
	private String json;
	
	public void setAttribute(String key, Object value) {
		
	}

	public String[] getParameter(String key) {
		String[] params = new String[1];
		params[0] = "1";
		return params;
	}

	public void setUser(UserInfoBean user) {
		
	}

	public UserInfoBean getUser() {
		UserInfoBean bean = new UserInfoBean();
		bean.setUserNo(1);
		return bean;
	}

	public String getMessageBody() throws ResourceException {

		return json;
	}
	
	public void setMessageBody(String json) throws ResourceException {
		this.json = json;
	}
	
	
	public String getTargetServiceKey() {
		return null;
	}

	@Override
	public void invalidatekeUser() {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}