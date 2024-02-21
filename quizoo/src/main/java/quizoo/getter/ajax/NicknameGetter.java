package quizoo.getter.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import db.dao.UserInfoDao;
import frame.Service;
import frame.context.RequestContext;
import frame.context.ResponseContext;
import frame.exception.BadRequestException;
import frame.exception.NotFoundException;
import frame.exception.ResourceException;

public class NicknameGetter extends Service {
	
	@Override
	public void execute(RequestContext req, ResponseContext res)
			throws IOException, ResourceException, BadRequestException, NotFoundException {
		// TODO 自動生成されたメソッド・スタブ
				
				int userNo = req.getUser().getUserNo();
				UserInfoDao userInfoDao = new UserInfoDao();
				
				String nickname = userInfoDao.selectNickname(userNo);
				
				PrintWriter out = res.getWrite();
				
				Gson gson = new Gson();
				String result = gson.toJson(nickname);
				
				out.println(result);
	}
}
