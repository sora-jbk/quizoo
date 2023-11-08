package frame;
import frame.context.RequestContext;
import frame.context.ResponseContext;

public interface ApplicationController {
	public RequestContext getRequest(Object request);
	public ResponseContext handleRequest(RequestContext request);
	public void handleResponse(RequestContext request,ResponseContext response);
} 
