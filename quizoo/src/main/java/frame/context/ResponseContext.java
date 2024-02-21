package frame.context;

import java.io.IOException;
import java.io.PrintWriter;

import frame.exception.BadRequestException;

/**
 * レスポンス情報をラップするためのインターフェース
 */
public interface ResponseContext {
    /**
     * リクエストをurlのリソースに転送します
     * @param url 転送先
     * @param requestContext 転送するリクエスト
     * @throws BadRequestException 転送時に例外が発生した場合
     */
    public void forward(String url, RequestContext requestContext) throws BadRequestException;
    /**
     * レスポンスを直接書き込むためのPrintWriterを取得します
     * @return レスポンスを書き込むためのPrintWriter
     * @throws IOException PrintWeiterの取得時に例外が発生した場合
     */
    public PrintWriter getWrite() throws IOException;
    /**
     * クライアントを指定したurlに移動させるためのレスポンスを返します
     * @param url 転送先のURL
     * @throws IOException レスポンスを返す時に例外が発生した場合
     */
    public void redirect(String url) throws IOException;
}
