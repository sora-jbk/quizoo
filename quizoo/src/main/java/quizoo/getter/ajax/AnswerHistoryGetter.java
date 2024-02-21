package quizoo.getter.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;

import db.bean.AnswerhistoryBean;
import db.dao.AnswerHistoryDao;
import frame.Service;
import frame.context.RequestContext;
import frame.context.ResponseContext;
import frame.exception.BadRequestException;
import frame.exception.NotFoundException;
import frame.exception.ResourceException;

public class AnswerHistoryGetter extends Service {

	

	@Override
	public void execute(RequestContext req, ResponseContext res)
			throws IOException, ResourceException, BadRequestException, NotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		ArrayList<AnswerhistoryBean> historyList = new ArrayList<>();
		
		int userNo = req.getUser().getUserNo();
		AnswerHistoryDao answerhistoryDao = new AnswerHistoryDao();
		
		historyList = answerhistoryDao.selectAnswerHistory(userNo);
		
		PrintWriter out = res.getWrite();
		
		Gson gson = new Gson();
		String result = gson.toJson(historyList);
		
		out.println(result);
	}

}
