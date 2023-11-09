package frame.context;
import java.io.IOException;
import java.io.PrintWriter;

public interface ResponseContext {
	public PrintWriter getWriter() throws IOException;
} 
