package quizoo.setter.ajax;

import java.io.IOException;

import db.dao.QuizDao;
import frame.Service;
import frame.context.RequestContext;
import frame.context.ResponseContext;
import frame.exception.BadRequestException;
import frame.exception.NotFoundException;
import frame.exception.ResourceException;

public class QuizDeleter extends Service {

	@Override
	public void execute(RequestContext req, ResponseContext res)
			throws IOException, ResourceException, BadRequestException, NotFoundException {
		
		int quizId = Integer.parseInt(req.getParameter("quiz_id")[0]);
		int userNo = req.getUser().getUserNo();
		
		QuizDao quizDao = new QuizDao();
		quizDao.deleteQuiz(quizId,userNo);
		
		
		
		

	}

}
