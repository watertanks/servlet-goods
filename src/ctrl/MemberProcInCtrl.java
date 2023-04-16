package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;


@WebServlet("/member_proc_in")
public class MemberProcInCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MemberProcInCtrl() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// 받아온 회원정보 저장
		MemberInfo memberInfo = new MemberInfo();
		MemberAddr memberAddr = new MemberAddr();
		
		memberInfo.setMi_id(request.getParameter("mi_id").trim().toLowerCase());
		memberInfo.setMi_pw(request.getParameter("mi_pw").trim().trim());
		memberInfo.setMi_name(request.getParameter("mi_name").trim());
		memberInfo.setMi_nickname(request.getParameter("mi_nickname").trim());
		memberInfo.setMi_gender(request.getParameter("mi_gender"));
		memberInfo.setMi_birth(request.getParameter("by") + "-" + 
		request.getParameter("bm") + "-" + request.getParameter("bd"));
		memberInfo.setMi_phone(request.getParameter("p1") + "-" + 
		request.getParameter("p2") + "-" + request.getParameter("p3"));
		memberInfo.setMi_email(request.getParameter("e1").trim() + 
		"@" + request.getParameter("e3").trim());
		memberInfo.setMi_point(1000);

		memberAddr.setMi_id(request.getParameter("mi_id"));
		memberAddr.setMa_name(request.getParameter("mi_name"));
		memberAddr.setMa_rname(request.getParameter("mi_name"));
		memberAddr.setMa_phone(request.getParameter("mi_phone"));
		memberAddr.setMa_zip(request.getParameter("ma_zip"));
		memberAddr.setMa_addr1(request.getParameter("ma_addr1"));
		memberAddr.setMa_addr2(request.getParameter("ma_addr2"));
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		MemberProcInSvc memberProcInSvc = new MemberProcInSvc();
		int result = memberProcInSvc.memberProcIn(memberInfo, memberAddr);
		if (result == 3) {		// 회원가입 성공
			out.println("<script>");
			out.println("alert('회원가입을 진심으로 환영합니다!');");
			out.println("location.href='/goods/login_form.jsp'");
			out.println("</script>");
			out.close();
		} else {				// 회원가입 실패
			out.println("<script>");
			out.println("alert('이미 회원인 ID입니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
	}
}


