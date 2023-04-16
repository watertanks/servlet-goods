package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import vo.*;
import svc.*;


@WebServlet("/member_detail")
public class MemberDetailCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MemberDetailCtrl() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String miid = request.getParameter("miid");
		MemberDetailSvc memberDetailSvc = new MemberDetailSvc();
		MemberInfo mi = new MemberInfo();
		mi = memberDetailSvc.getMemberDetail(miid);
		String[] arr = new String[15];
		if(mi != null) {
			arr[0] = mi.getMi_id();
			arr[1] = mi.getMi_pw();
			arr[2] = mi.getMi_gender();
			arr[3] = mi.getMi_birth();
			arr[4] = mi.getMi_name();
			arr[5] = mi.getMi_nickname();
			arr[6] = mi.getMi_email();
			arr[7] = mi.getMi_phone();
			arr[8] = Integer.toString(mi.getMi_point());
			arr[9] = Integer.toString(mi.getMi_bmcount());
			arr[10] = Integer.toString(mi.getMi_couponcount());
			arr[11] = mi.getMi_lastlogin();
			arr[12] = mi.getMi_joindate();
			
			if(mi.getMi_status().equals("a")) arr[13] = "정상";
			else if (mi.getMi_status().equals("b")) arr[13] = "휴면";
			else if (mi.getMi_status().equals("c")) arr[13] = "탈퇴";
			else if (mi.getMi_status().equals("d")) arr[13] = "정지";
			
			if(mi.getMi_kind().equals("a")) arr[14]= "내부가입";
			else if(mi.getMi_kind().equals("b")) arr[14]= "외부가입";

			for(int i = 0; i < arr.length; i++) {
				out.println(","+arr[i]);
			}		
			
			
		/*	out.println();	out.println();	out.println();	out.println();	out.println();	out.println();
			out.println();	out.println();	out.println();	out.println();	out.println();	out.println();
			out.println();	out.println();
			request.setAttribute("arr", arr);*/
		} else {
			out.println("<script>");
			out.println("alert('비정상적 에러발생');");
			out.println("history.back();");
			out.println("</script>");
		}
		
	}

}
    
