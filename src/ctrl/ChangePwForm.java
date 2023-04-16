package ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.MemberInfo;

/**
 * Servlet implementation class ChangePwForm
 */
@WebServlet("/change_pw_form")
public class ChangePwForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ChangePwForm() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
		if(loginInfo != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/mypage/change_pw_form.jsp");
			dispatcher.forward(request, response);
		}
	}


}
