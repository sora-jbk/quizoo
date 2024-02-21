package quizoo;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import db.bean.UserInfoBean;
import db.dao.UserInfoDao;
import frame.Service;
import frame.context.RequestContext;
import frame.context.ResponseContext;
import frame.exception.BadRequestException;
import frame.exception.NotFoundException;
import frame.exception.ResourceException;
import frame.util.Hash;

public class Signup extends Service {

	@Override
	public void execute(RequestContext req, ResponseContext res)
			throws IOException, ResourceException, BadRequestException, NotFoundException {
		System.out.println("signupにきた");
		String userId = req.getParameter("id")[0];
		String nickName = req.getParameter("name")[0];
		String pass = req.getParameter("password")[0];
		String passAgain = req.getParameter("passwordAgain")[0];

		if(userId == null || userId.isEmpty()) {
			throw new BadRequestException("userIdが空");
		}
		
		//passwordのチェック
		if(pass == null || pass.isEmpty()) {
			throw new BadRequestException("passwordが空");
		}
		UserInfoDao dao = new UserInfoDao();
		UserInfoBean bean = new UserInfoBean();
		bean.setUserId(userId);
		bean.setNickname(nickName);
		if(pass.equals(passAgain)) {
			try {
				//パスワードのハッシュ化
				pass = Hash.getHashedString(pass);
				bean.setPassword(pass);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				throw new ResourceException(e.getMessage(), e);
			} 
			dao.insertUser(bean);
			
			
//			登録された情報を読み込み
			bean = dao.selectSearchedUserByUserId(userId);
//			ログイン状態にする
			req.setUser(bean);
			
			res.redirect("index");
		}else {
			res.redirect("login-page");
			throw new BadRequestException("パスワードが一致しないから諦めろ");
			
		}
		


	}

}
