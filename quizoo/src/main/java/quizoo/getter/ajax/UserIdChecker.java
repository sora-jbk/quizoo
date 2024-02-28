package quizoo.getter.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import db.bean.UserInfoBean;
import db.dao.UserInfoDao;
import frame.Service;
import frame.context.RequestContext;
import frame.context.ResponseContext;
import frame.exception.BadRequestException;
import frame.exception.NotFoundException;
import frame.exception.ResourceException;

public class UserIdChecker extends Service {

	@Override
	public void execute(RequestContext req, ResponseContext res)
			throws IOException, ResourceException, BadRequestException, NotFoundException {
		Gson gson = new Gson();
		
		UserId id = gson.fromJson(req.getMessageBody(), UserId.class);
		
		
		UserInfoDao dao = new UserInfoDao();
		
		UserInfoBean bean = dao.selectSearchedUserByUserId(id.getUserid());
		
		PrintWriter out = res.getWrite();
		
		String result = "{\"isUsed\":";
		
		result += Boolean.toString(bean.getUserId() != null);
		
		result += "}";
		
		out.print(result);

	}
	

	private class UserId{
		String userid;
		public UserId() {
			
		}
		public String getUserid() {
			return userid;
		}
		public void setUserId(String userId) {
			this.userid = userid;
		}	
		
	}
}

