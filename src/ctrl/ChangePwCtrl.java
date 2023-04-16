package ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.*;



@WebServlet("/changeInfoPw")
public class ChangePwCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ChangePwCtrl() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String newPwd = request.getParameter("newPwd");
		String uid = request.getParameter("uid");
		String oldPwd = request.getParameter("oldPwd");

		if(newPwd != null && uid != null) {
			MemberProcUpSvc changeInfoSvc = new MemberProcUpSvc();
			int result = changeInfoSvc.ChangePw(uid, oldPwd, newPwd);
			
			if(result == 1) {
				out.println("<script>");
				out.println("alert('비밀번호가 정상적으로 변경되었습니다.');");
				out.println("location.href='/goods/mypage/myinfo_detail.jsp';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('비밀번호 변경시 오류가 발생하였습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		
		
	}

}
