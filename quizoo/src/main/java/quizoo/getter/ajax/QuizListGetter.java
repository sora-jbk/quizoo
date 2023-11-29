package quizoo.getter.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;

import db.dao.QuizDao;
import frame.Service;
import frame.context.RequestContext;
import frame.context.ResponseContext;
import frame.exception.ResourceException;

public class QuizListGetter extends Service {

	@Override
	public void execute(RequestContext req, ResponseContext res) throws IOException, ResourceException {
		
		PrintWriter out = res.getWrite();
		
		QuizDao quizDao = new QuizDao();
		ArrayList quizList = quizDao.sortGenreQuiz();
		Gson gson = new Gson();
		String result = gson.toJson(quizList);
		
		out.println(result);
	}

}
