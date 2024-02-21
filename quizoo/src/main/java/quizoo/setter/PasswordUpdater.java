package quizoo.setter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.google.gson.Gson;

import db.bean.UserInfoBean;
import db.dao.UserInfoDao;
import frame.Service;
import frame.context.RequestContext;
import frame.context.ResponseContext;
import frame.exception.BadRequestException;
import frame.exception.NotFoundException;
import frame.exception.ResourceException;
import frame.util.Hash;

public class PasswordUpdater extends Service {

    @Override
    public void execute(RequestContext req, ResponseContext res)
            throws IOException, ResourceException, BadRequestException, NotFoundException {
        
    	String password;
    	UserInfoBean user = req.getUser();
        
        UserInfoDao dao = new UserInfoDao();
        
    
        Gson gson = new Gson();
        String body = req.getMessageBody();
        UserInfoBean bean = gson.fromJson(body, UserInfoBean.class);
        try {
			//パスワードのハッシュ化
			password = Hash.getHashedString(bean.getPassword());
			//bean.setPassword(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new ResourceException(e.getMessage(), e);
			
		}
        dao.updatePassword(user.getUserId(),password);
        
    }
}