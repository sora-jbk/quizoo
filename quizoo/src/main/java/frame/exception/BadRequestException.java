package frame.exception;

/**
 * リクエストの情報が誤っている場合の例外
 */
public class BadRequestException extends Exception{
	public BadRequestException(String msg,Exception e) {
		super(msg,e);
	}

	public BadRequestException(String msg) {
		super(msg);
	}
} 
