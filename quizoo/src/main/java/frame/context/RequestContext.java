package frame.context;

import db.bean.UserInfoBean;
import frame.exception.ResourceException;

/**
 * リクエスト情報をラップするためのインターフェース
 */
public interface RequestContext {
	/**
	 * リクエストに属性を設定します
	 * @param key 指定された値が関連付けられるキー
	 * @param value 指定されたキーに関連付けられる値
	 */
	public void setAttribute(String key, Object value);

	/**
	 * リクエストからパラメータを取得します
	 * @param key 関連付けられた値が返されるキー
	 * @return 指定されたキーがマップされている値。このマップにそのキーのマッピングが含まれていない場合はnull
	 */
	public String[] getParameter(String key);

	/**
	 * ユーザー情報を設定します
	 * @param user 設定するユーザー
	 */
	public void setUser(UserInfoBean user);
	
	/**
	 * セッション情報を破棄してログアウト状態にします
	 */
	public void invalidatekeUser();

	/**
	 * 設定されているユーザーを取得します。
	 * @return 設定されているユーザーの情報
	 */
	public UserInfoBean getUser();

	/**
	 * リクエストのボディを取得します
	 * @return リクエストのボディを生のデータ
	 * @throws ResourceException ボディを取得する際に例外が発生した場合
	 */
	public String getMessageBody() throws ResourceException;
	
	/**
	 * Serviceの実装クラスを取得するためのキーを返します
	 * @return リクエストに関連付けられたキー
	 */
	public String getTargetServiceKey();
}