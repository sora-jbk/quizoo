import java.io.IOException;

import org.junit.jupiter.api.Test;

import frame.exception.BadRequestException;
import frame.exception.NotFoundException;
import frame.exception.ResourceException;
import quizoo.getter.ajax.AnswerHistoryGetter;
import web.context.TestRequestContext;
import web.context.TestResponseContext;

class test {

	@Test
	void test() {
		TestRequestContext reqc = new TestRequestContext();
		TestResponseContext resc = new TestResponseContext();
		
		AnswerHistoryGetter a = new AnswerHistoryGetter();
		
		try {
			
			a.execute(reqc, resc);
			
		} catch (IOException | ResourceException | BadRequestException | NotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		resc.closeWriter();
	}

}
