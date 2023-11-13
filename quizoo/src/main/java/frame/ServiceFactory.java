package frame;

import frame.context.RequestContext;

public abstract class ServiceFactory {
	public static Service getService(RequestContext req) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String target = req.getRequestTarget();
		
		Class c = Class.forName(target);
		
		Service service = (Service)c.newInstance();
		
		return service;
	}
} 
