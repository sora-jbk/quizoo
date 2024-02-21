package frame;

import java.io.IOException;

import frame.context.RequestContext;
import frame.context.ResponseContext;
import frame.exception.BadRequestException;
import frame.exception.NotFoundException;
import frame.exception.ResourceException;

/**
 * ApplicationControllerの実装クラスから呼び出されるクラスのスーパークラス
 */
public abstract class Service {
	/**
	 * リクエストされた情報に合う処理します
	 * @param req リクエスト情報を含むオブジェクト
	 * @param res レスポンス情報を含むオブジェクト
	 * @throws IOException IOに問題が発生した場合
	 * @throws ResourceException リソースに問題が発生した場合
	 * @throws BadRequestException リクエストに含まれる情報が誤っている場合
	 * @throws NotFoundException リクエストされたリソースが存在しない場合
	 */
	public abstract void execute(RequestContext req,ResponseContext res) throws IOException,ResourceException,BadRequestException,NotFoundException;

}
