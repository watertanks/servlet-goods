package ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ChkDupSvc;

@WebServlet("/dupApiLogin")
public class DupChkApiLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DupChkApiLogin() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String email = request.getParameter("email").toLowerCase();
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if(email != null) {
			try {
				ChkDupSvc chkDupSvc = new ChkDupSvc();
				int result = chkDupSvc.chkDupApiLogin(email);
				out.println(result);
			} catch(Exception e) {
				e.printStackTrace();
				out.println("chkDupCtrl클래스의 email부분 에러발생");
			}
			
		}
		
	}
}
