package quizoo.setter.ajax;

import java.io.IOException;

import com.google.gson.Gson;

import db.bean.UserAnswerhistoryBean;
import db.bean.UserInfoBean;
import db.dao.AnswerHistoryDao;
import db.dao.UserInfoDao;
import frame.Service;
import frame.context.RequestContext;
import frame.context.ResponseContext;
import frame.exception.BadRequestException;
import frame.exception.NotFoundException;
import frame.exception.ResourceException;

public class AnswerSubmitter extends Service {

	@Override
	public void execute(RequestContext req, ResponseContext res) throws IOException, ResourceException, BadRequestException, NotFoundException {
		// TODO 自動生成されたメソッド・スタブ
        
        Gson gson = new Gson();

        UserInfoBean bean = req.getUser();
        
        UserAnswerhistoryBean userhistoryBean = gson.fromJson(req.getMessageBody(), UserAnswerhistoryBean.class);
        
        
        AnswerHistoryDao answerhistoryDao = new AnswerHistoryDao();
        UserInfoDao userinfoDao = new UserInfoDao();
        
        
        //ここからanswerhistoryへのinsertやuserInfoのupdateを考える
        //answerhistoryへのisnert  userNoはsessionから持ってくる
        answerhistoryDao.insertAnswerHistory(bean.getUserNo(), userhistoryBean.getQuizId(), userhistoryBean.getAnsweredTime(), userhistoryBean.getCorrectCount());
        
        
        //userinfoへのupdate
        userinfoDao.updateScore(bean.getUserNo(), userhistoryBean.getTotalAnswer(), userhistoryBean.getCorrectAnswer());
        
    }
}
