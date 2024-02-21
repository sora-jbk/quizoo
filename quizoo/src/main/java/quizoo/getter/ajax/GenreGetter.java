package quizoo.getter.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;

import db.bean.GenreBean;
import db.dao.GenreDao;
import frame.Service;
import frame.context.RequestContext;
import frame.context.ResponseContext;
import frame.exception.BadRequestException;
import frame.exception.NotFoundException;
import frame.exception.ResourceException;

public class GenreGetter extends Service {
	@Override
	public void execute(RequestContext req, ResponseContext res)
			throws IOException, ResourceException, BadRequestException, NotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		ArrayList<GenreBean> genreList = new ArrayList<>();
		
		GenreDao genreDao = new GenreDao();
		genreList = genreDao.selectGenre();
		
		PrintWriter out = res.getWrite();
		
		Gson gson = new Gson();
		String result = gson.toJson(genreList);
		
		out.println(result);
		
	}
}
