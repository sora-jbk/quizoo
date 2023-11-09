package web.context;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import frame.context.ResponseContext;

public class HttpResponseContext implements ResponseContext{
	private HttpServletResponse res = null;
	
	public HttpResponseContext(HttpServletResponse res){
		this.res = res;
	}


	@Override
	public PrintWriter getWriter() throws IOException {
		return res.getWriter();
	}
	
} 
