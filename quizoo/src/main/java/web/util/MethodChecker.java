package web.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import com.google.gson.Gson;

public class MethodChecker {
	private static AllowedMethods methods = null;

	public static boolean check(javax.servlet.http.HttpServletRequest req) throws IOException {
		if (methods == null) {
			try (Reader reader = new InputStreamReader(
					req.getServletContext().getResourceAsStream("/WEB-INF/methods.json"))) {
				Gson gson = new Gson();
				methods = gson.fromJson(reader, AllowedMethods.class);
				System.out.println(gson.toJson(methods));
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
		}

		String reqMethod = req.getMethod();
		
		
		String contextPath = req.getContextPath();
		
		String reqService = req.getRequestURI().toString().replaceFirst(contextPath, "");
		

		
		return (reqMethod.equals("GET") || reqMethod.equals("POST"))//GET又はPOSTでなかった場合はfalse
				&&
				((reqMethod.equals("GET") && methods.GET.contains(reqService)) //GETがリクエストされ、GET内にもサービス名がある
				|| (reqMethod.equals("POST") && methods.POST.contains(reqService))  //POSTがリクエストされ、POST内にもサービス名がある
				||(!methods.POST.contains(reqService) && !methods.GET.contains(reqService))); //どちらにも無かった場合はどちらも許可
	}

}
