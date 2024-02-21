package frame.exception;

/**
 * リクエストされたリソースが見つからなかった場合の例外
 */
public class NotFoundException extends Exception{
	public NotFoundException() {
		super();
	}
	public NotFoundException(String msg) {
		super(msg);
	}
	public NotFoundException(String msg,Exception e) {
		super(msg,e);
	}
} 
