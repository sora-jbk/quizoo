package frame.exception;

public class BadRequestException extends Exception{
	public BadRequestException(String msg,Exception e) {
		super(msg,e);
	}
} 
