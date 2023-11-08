package frame;
public abstract class ServiceFactory {
	public static Service getService(RequestContext req) {
		String target = req.getTarget();
		
		Class c = Class.forName(target);
		
		Service service = (Service)c.newInstance();
		
		return service;
	}
} 
