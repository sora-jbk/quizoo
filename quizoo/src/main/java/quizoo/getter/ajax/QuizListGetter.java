package quizoo.getter.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

import db.bean.QuizBean;
import db.dao.QuizDao;
import frame.Service;
import frame.context.RequestContext;
import frame.context.ResponseContext;
import frame.exception.ResourceException;

public class QuizListGetter extends Service {

	private static HashMap<String, String> paramColMap = new HashMap<String, String>();
	static {
		paramColMap.put("new", "create_time");
		paramColMap.put("genre", "genre_no");//order by句に入るだ�?
		paramColMap.put("ganle", "genre_no");
		paramColMap.put("popular", "total_participants");
	}
	
	@Override
	public void execute(RequestContext req, ResponseContext res) throws IOException, ResourceException {

	    ArrayList<QuizBean> quizList = new ArrayList<>();

	    String[] order = req.getParameter("order");
	    String orderStr = "create_time";

	    String[] genreNo = req.getParameter("genreNo");
	    Integer genreNoInteger = null;
		
		System.out.println(genreNo);

	    if (genreNo != null) {
	        genreNoInteger = Integer.valueOf(genreNo[0]);
	    }

	    if (order != null) {
	        orderStr = paramColMap.get(order[0]);
	    }

	    QuizDao quizDao = new QuizDao();

	    if (genreNoInteger != null && orderStr != null) {
	        quizList = quizDao.selectOrderedQuiz(orderStr, genreNoInteger);
	    } else if (genreNoInteger == null && order != null) {
	        quizList = quizDao.selectOrderedQuiz(orderStr);
	    } else if (genreNoInteger != null && orderStr == null) {
	        quizList = quizDao.searchQuiz(genreNoInteger);
	    } else {
	        quizList = quizDao.selectQuiz();
	    }

	    PrintWriter out = res.getWrite();

	    Gson gson = new Gson();
	    String result = gson.toJson(quizList);

	    out.println(result);
	}

}
