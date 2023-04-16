package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.MemberAddr;
import vo.MemberInfo;

@WebServlet("/hidden_join")
public class HiddenJoinCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HiddenJoinCtrl() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		MemberInfo memberInfo = new MemberInfo();
		
		memberInfo.setMi_email(request.getParameter("email"));
		memberInfo.setMi_gender(request.getParameter("gender"));
		memberInfo.setMi_birth(request.getParameter("birth"));
		memberInfo.setMi_nickname(request.getParameter("nickname"));
		
		MemberProcInSvc memberProcInSvc = new MemberProcInSvc();
		int result = memberProcInSvc.hiddenJoin(memberInfo);
		
		if(result == 3) {
			out.println("회원가입을 진심으로 환영합니다!\n암호는 '[생월일]/qwe!@#'입니다.\n예)0515/qwe!@#");
			//out.println("location.href='/goods/login_form.jsp'");
			out.close();
		} else {							// 회원가입 실패
			out.println("alert('이미 회원인 ID입니다.');");
			out.close();
		}
				
		
	}

}
