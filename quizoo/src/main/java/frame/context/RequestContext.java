package frame.context;
public interface RequestContext {
	public void setAttrubute(String key,Object value);
	public String[] getParameter(String key);
	public void setId(String id);
	public String getId();
	public String getTarget();
} 
