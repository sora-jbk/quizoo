import java.io.PrintWriter;

import org.apache.tomcat.util.http.fileupload.RequestContext;

public interface ResponseContext {
	public void forward(String uri,RequestContext req);
	public PrintWriter getWriter();
} 
