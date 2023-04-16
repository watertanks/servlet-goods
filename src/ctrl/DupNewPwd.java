package ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ChkDupSvc;


@WebServlet("/dupNewPwd")
public class DupNewPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DupNewPwd() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String uid = request.getParameter("uid");
		String newPwd = request.getParameter("newPwd");
		
		// 기존비밀번호가 맞는지 검사
		if(uid != null && newPwd != null){
			ChkDupSvc chkDupSvc = new ChkDupSvc();
			int result = chkDupSvc.chkDupNewPwd(uid, newPwd);
			out.println(result);
		}else {
			out.println("<script>");
			out.println("alert('비정상적 접근입니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
	}
}