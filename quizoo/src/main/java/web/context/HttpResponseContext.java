package web.context;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.RequestContext;

import frame.context.ResponseContext;

public class HttpResponseContext implements ResponseContext{
	private HttpServletResponse res = null;
	
	HttpResponseContext(HttpServletResponse res){
		this.res = res;
	}

	@Override
	public void forward(String uri, RequestContext req) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public PrintWriter getWriter() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
} 
