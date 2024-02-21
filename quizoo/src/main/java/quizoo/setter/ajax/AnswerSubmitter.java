package quizoo.setter.ajax;

import java.io.IOException;

import com.google.gson.Gson;

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
		try {
			Gson gson = new Gson();
			
			UserInfoBean userInfo = req.getUser();
			
			AnswerResult answerResult = gson.fromJson(req.getMessageBody(), AnswerResult.class);
			
			System.out.println(userInfo);
			
			AnswerHistoryDao answerHistoryDao = new AnswerHistoryDao();
			
			//ここからanswerhistoryへのinsertやuserInfoのupdateを考える
			//answerhistoryへのisnert  userNoはsessionから持ってくる
			answerHistoryDao.insertAnswerHistory(userInfo.getUserNo(), answerResult.getQuiz_id(), answerResult.getScore());
			
			
			
			UserInfoDao userInfoDao = new UserInfoDao();
			//userinfoへのupdate
			userInfoDao.updateScore(userInfo.getUserNo(), answerResult.getQuestion_num(), answerResult.getScore());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
        
    }
	
	private class AnswerResult{
		private int quiz_id;
		private int score;
		private int question_num;
		public int getQuiz_id() {
			return quiz_id;
		}
		public void setQuiz_id(int quiz_id) {
			this.quiz_id = quiz_id;
		}
		public int getScore() {
			return score;
		}
		public void setScore(int score) {
			this.score = score;
		}
		public int getQuestion_num() {
			return question_num;
		}
		public void setQuestion_num(int questionNum) {
			this.question_num = questionNum;
		}
	}
}
