package web.context;

import java.io.IOException;
import java.io.PrintWriter;

import frame.context.RequestContext;
import frame.context.ResponseContext;
import frame.exception.BadRequestException;

public class TestResponseContext implements ResponseContext {
	public void forward(String url, RequestContext requestContext) throws  BadRequestException {
	}
	public PrintWriter getWrite() throws IOException {
		return null;
	}
	 public void redirect(String url) throws IOException {
	 }
}
