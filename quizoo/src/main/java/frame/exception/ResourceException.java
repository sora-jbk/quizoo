package frame.exception;

/**
 * リソースに問題がある場合の例外
 */
public class ResourceException extends Exception {
	public ResourceException(String msg,Exception e) {
		super(msg, e);
	}
}
