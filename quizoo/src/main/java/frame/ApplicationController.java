package frame;

import java.io.IOException;

import frame.context.RequestContext;
import frame.context.ResponseContext;
import frame.exception.BadRequestException;
import frame.exception.NotFoundException;
import frame.exception.ResourceException;

/**
 * アプリケーションコントローラーのインターフェース
 */
public interface ApplicationController {
    /**
     * resuestをラップしてRequestContextの実装クラスのオブジェクトを取得する
     * @param request wrapされるリクエストのオブジェクト
     * @return wrapされたオブジェクト
     */
    public RequestContext getRequest(Object request);
    /**
     * responseをラップしてResponseContextの実装クラスのオブジェクトを取得する
     * @param response wrapされるレスポンスのオブジェクト
     * @return wrapされたレスポンス
     */
    ResponseContext getResponse(Object response);
    /**
     * リクエスト情報をレスポンス情報を読み込んで処理します
     * @param request リクエスト情報を持つオブジェクト
     * @param response レスポンス情報を持つオブジェクト
     * @throws ResourceException リソースに問題が発生した場合
     * @throws NotFoundException リクエストされたリソースが存在しない場合
     * @throws IOException IOに問題が発生した場合
     * @throws BadRequestException リクエストに含まれる情報が誤っている場合
     */
    public void handleResponse(RequestContext request, ResponseContext response) throws ResourceException, NotFoundException, IOException, BadRequestException;
}
