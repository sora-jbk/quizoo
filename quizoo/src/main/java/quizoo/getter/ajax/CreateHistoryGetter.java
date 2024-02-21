package quizoo.getter.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;

import db.bean.CreateHistoryBean;
import db.dao.CreateHistoryDao;
import frame.Service;
import frame.context.RequestContext;
import frame.context.ResponseContext;
import frame.exception.BadRequestException;
import frame.exception.NotFoundException;
import frame.exception.ResourceException;

public class CreateHistoryGetter extends Service {

	@Override
	public void execute(RequestContext req, ResponseContext res)
			throws IOException, ResourceException, BadRequestException, NotFoundException {

		int userNo = req.getUser().getUserNo();
		
		CreateHistoryDao createhistoryDao = new CreateHistoryDao();
		
		ArrayList<CreateHistoryBean> createhistoryList = createhistoryDao.selectCreateHistory(userNo);
		System.out.println(userNo);
		PrintWriter out = res.getWrite();

		Gson gson = new Gson();
		String result = gson.toJson(createhistoryList);

		out.println(result);
		
	}

}
