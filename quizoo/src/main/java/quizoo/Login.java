package quizoo;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import db.bean.UserInfoBean;
import db.dao.UserInfoDao;
import frame.Service;
import frame.context.RequestContext;
import frame.context.ResponseContext;
import frame.exception.BadRequestException;
import frame.exception.ResourceException;
import frame.util.Hash;

public class Login extends Service{

	@Override
	public void execute(RequestContext req, ResponseContext res) 
			throws IOException, ResourceException,BadRequestException {
		String userId = req.getParameter("id")[0];
		String pass = req.getParameter("password")[0];
		
		//userIdのチェック
		if(userId == null || userId.isEmpty()) {
			throw new BadRequestException("userIdが空");
		}
		
		//passwordのチェック
		if(pass == null || pass.isEmpty()) {
			throw new BadRequestException("passwordが空");
		}
		
		UserInfoDao dao = new UserInfoDao();
		UserInfoBean bean = dao.selectSearchedUserByUserId(userId);
		
		//ハッシュ化されたパスワードを取得
		try {
			pass = Hash.getHashedString(pass);
		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw new ResourceException(e.getMessage(), e);
		} 
		
		//passwordが一致したとき
		if(bean!=null 
				&& bean.getPassword()!=null 
				&& pass.equals(bean.getPassword().replaceAll(" ", ""))
				) {
			req.setUser(bean);
			res.redirect("index");
		}else {
			res.redirect("login-page?faild");
		}
		
	}
}
