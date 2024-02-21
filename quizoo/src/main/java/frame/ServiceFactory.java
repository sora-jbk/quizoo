package frame;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import frame.context.RequestContext;
import frame.exception.NotFoundException;
import frame.exception.ResourceException;

/**
 * リクエスト情報からServiceの実装クラスを生成する
 */
public abstract class ServiceFactory {
	
	/**
	 * 渡されたファイルからリクエスト情報に適合したServiceの実装クラスを返す
	 * @param file properties形式で記述されたファイル
	 * @param req リクエスト情報を含むオブジェクト
	 * @return 生成されたServiceの実装クラスのオブジェクト
	 * @throws ResourceException ファイルの読み取りに失敗した場合
	 * @throws NotFoundException 指定されたリクエスト先が見つからなかった場合
	 */
	public static Service getService(InputStream file,RequestContext req) throws ResourceException, NotFoundException{
		Service service = null;
		String serviceClassName = null;
		String reqKey = req.getTargetServiceKey();
		
		Properties prop = new Properties();
		try{
			prop.load(file);
			
			
			
			serviceClassName = (String) prop.get(reqKey);
			
			if(serviceClassName == null || serviceClassName.isEmpty()) {
				throw new NotFoundException(reqKey + "に対応するクラスが見つかりません\n");
			}
			
			try {
				service = (Service)Class.forName(serviceClassName).getDeclaredConstructor().newInstance();
			} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException
					| SecurityException e) {
				throw new NotFoundException(serviceClassName + "のインスタンス化に失敗しました\n"
							+ e.getMessage(), e);
			}
			
		} catch (IOException e) {
			throw new ResourceException("command-mapping.propertiesのロードに失敗しました\n" 
						+ e.getLocalizedMessage(), e);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new NotFoundException(serviceClassName + "のインスタンス化に失敗しました\n" 
						+ e.getMessage(),e);
		}
		return service;
	}
} 
