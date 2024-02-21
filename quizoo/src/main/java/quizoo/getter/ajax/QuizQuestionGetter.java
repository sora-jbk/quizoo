package quizoo.getter.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import db.bean.QuizQuestionBean;
import db.dao.QuizQuestionDao;
import frame.Service;
import frame.context.RequestContext;
import frame.context.ResponseContext;
import frame.exception.BadRequestException;
import frame.exception.NotFoundException;
import frame.exception.ResourceException;

public class QuizQuestionGetter extends Service {

	@Override
	public void execute(RequestContext req, ResponseContext res)
			throws IOException, ResourceException, BadRequestException, NotFoundException {
		
		int quiz_id;
		
		try {
			quiz_id = Integer.parseInt(req.getParameter("quiz_id")[0]);
		}catch (NumberFormatException e) {
			throw new BadRequestException(e.getMessage(),e);
		}
		
		
		QuizQuestionDao dao = new QuizQuestionDao();
		
		QuizQuestionBean quizAndQuestions = dao.selectQuizWithQuestion(quiz_id);
		
		Gson gson = new Gson();
		
		String jsonData = gson.toJson(quizAndQuestions);
		
		PrintWriter out = res.getWrite();
		
		out.print(jsonData);
		

	}

}
