package quizoo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logout(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logout(request, response);
    }
    
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // セッションを取得
        HttpSession session = request.getSession();

        if (session != null) {
            // セッションが存在する場合は無効化
            session.invalidate();
        }

        // ログアウト後のリダイレクトなどの処理を追加
        response.sendRedirect(request.getContextPath() + "/index");
    }
}
