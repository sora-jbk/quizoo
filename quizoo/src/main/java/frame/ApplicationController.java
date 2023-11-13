package frame;
import frame.context.RequestContext;
import frame.context.ResponseContext;

public interface ApplicationController {
	public RequestContext getRequest(Object request);
	public ResponseContext handleRequest(Object response);
	public void handleResponse(RequestContext request,ResponseContext response);
} 
