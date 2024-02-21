package quizoo.setter;

import java.io.IOException;

import com.google.gson.Gson;

import db.bean.UserInfoBean;
import db.dao.UserInfoDao;
import frame.Service;
import frame.context.RequestContext;
import frame.context.ResponseContext;
import frame.exception.BadRequestException;
import frame.exception.NotFoundException;
import frame.exception.ResourceException;

public class NickNameUpdater extends Service {

    @Override
    public void execute(RequestContext req, ResponseContext res)
            throws IOException, ResourceException, BadRequestException, NotFoundException {
        
    		
    		UserInfoDao dao = new UserInfoDao();
    		
    		int userNo = req.getUser().getUserNo();
    		
    		
    		
    		
    		Gson gson = new Gson();
    		String body = req.getMessageBody();
    		
    		UserInfoBean bean = gson.fromJson(body, UserInfoBean.class);
    		
    		
    		dao.updateNickName(userNo,bean.getNickname());
    		
    		

    }
    
    
    	
    }

