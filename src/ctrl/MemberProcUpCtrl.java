package ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.*;
import vo.*;



@WebServlet("/member_proc_up")
public class MemberProcUpCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberProcUpCtrl() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		MemberInfo memberInfo = new MemberInfo();		MemberAddr memberAddr = new MemberAddr();
		int result = 0;
		
		String p1 = request.getParameter("p1");String p2 = request.getParameter("p2");String p3 = request.getParameter("p3");
		
		memberInfo.setMi_nickname(request.getParameter("mi_nickname"));
		memberInfo.setMi_phone(p1 +"-"+ p2 +"-"+ p3);
		memberAddr.setMa_zip(request.getParameter("ma_zip"));
		memberAddr.setMa_addr1(request.getParameter("ma_addr1"));
		memberAddr.setMa_addr2(request.getParameter("ma_addr2"));
		
		MemberProcUpSvc memberProcUpSvc = new MemberProcUpSvc();
		result = memberProcUpSvc.memberProcUp(memberInfo, memberAddr);
		
		if(result == 2) {
			out.println("<script>");
			out.println("alert('정상적으로 변경되었습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		} else {
			out.println("<script>");
			out.println("alert('정보수정 중 에러가 발생하였습니다\n다시 시도해주세요.');");
			out.println("location.reload();");
			out.println("</script>");
			out.close();
		}
		
		
	}

}
