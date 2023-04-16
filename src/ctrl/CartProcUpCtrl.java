package ctrl;


import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;


@WebServlet("/cart_proc_up")
public class CartProcUpCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CartProcUpCtrl() {super();}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int ocidx = Integer.parseInt(request.getParameter("ocidx"));
		// 변경시 where절에서 조건으로 사용될 자바구니 테이블의 pk
		int opt = Integer.parseInt(request.getParameter("opt"));
		// opt : 변경할 옵션 인덱스
		int cnt = Integer.parseInt(request.getParameter("cnt"));
		// cnt : 변경할 수량
		HttpSession session = request.getSession();
		MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
		if(loginInfo ==null) {
			response.setContentType("text html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인후 사용하실 수 있습니다.')");
			out.println("location.replace('login_form.jsp?url=cart_view');");
			out.println("</script>");
		}
		String miid = loginInfo.getMi_id();
		
		OrderCart oc = new OrderCart();
		oc.setOc_idx(ocidx);
		oc.setMi_id(miid);
		oc.setPs_idx(opt);
		oc.setOc_cnt(cnt);
		CartProcUpSvc cartProcUpsSvc = new CartProcUpSvc(); 
		int result = cartProcUpsSvc.cartUpdate(oc);
	
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
	}

}
