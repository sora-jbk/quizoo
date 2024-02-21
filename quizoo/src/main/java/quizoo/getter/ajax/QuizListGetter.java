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
		paramColMap.put("genre", "genre_no");//order by句に入るだけ
		paramColMap.put("ganle", "genre_no");
		paramColMap.put("popular", "total_participants");
	}
	@Override
	public void execute(RequestContext req, ResponseContext res) throws IOException, ResourceException {
		
		String genreNo = null;
		String orderColumn = null;
		String searchStr = null;
		
		if(req.getParameter("genre_no") != null) {
			genreNo = req.getParameter("genre_no")[0];
		}
		
		if(req.getParameter("order") != null) {
			orderColumn = paramColMap.get(req.getParameter("order")[0]);
		}
		
		if(req.getParameter("search") != null) {
			searchStr = req.getParameter("search")[0];
		}
		
		
		
		QuizDao quizDao = new QuizDao();
		
		ArrayList<QuizBean> quizList = quizDao.selectQuiz(orderColumn, genreNo, searchStr);
		
		//quizIdによる検索のメソッドは？

		PrintWriter out = res.getWrite();
		
		Gson gson = new Gson();
		String result = gson.toJson(quizList);
		
		
		out.println(result);

	}

}
