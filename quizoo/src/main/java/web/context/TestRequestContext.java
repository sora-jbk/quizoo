package web.context;

import db.bean.UserInfoBean;
import frame.context.RequestContext;
import frame.exception.ResourceException;

public class TestRequestContext implements RequestContext{
	private String json;
	
	public void setAttribute(String key, Object value) {
		
	}

	public String[] getParameter(String key) {
		return null;
	}

	public void setUser(UserInfoBean user) {
		
	}

	public UserInfoBean getUser() {
		UserInfoBean bean = new UserInfoBean();
		bean.setUserNo(1);
		return bean;
	}

	public String getMessageBody() throws ResourceException {
//		json = "{\n"
//				+ "	\"userNo\":1,\n"
//				+ "	\"quizId\":1,\n"
//				+ "	\"answeredTime\":\"now()\",\n"
//				+ "	\"correctCount\":\"1\",\n"
//				+ "	\"totalAnswer\":\"1\"\n"
//				+ "}";
		return json;
	}
	
	public void setMessageBody(String json) throws ResourceException {
		this.json = json;
	}
	
	
	public String getTargetServiceKey() {
		return null;
	}
}