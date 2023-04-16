package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import java.net.*;
import svc.*;
import vo.*;
/**
 * Servlet implementation class CouponListCtrl
 */
@WebServlet("/coupon_list")
public class CouponListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CouponListCtrl() {super();}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cpage=1, psize=10, bsize=10, rcnt=0, pcnt=0, spage=0;
		HttpSession session = request.getSession();
		MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
		
		if(loginInfo ==null) {	
			response.setContentType("text/html charset=utf-8 ");
			PrintWriter out= response.getWriter();
			out.println("<script>");
			out.println("alert('�α����� ����Ͻ� �� �ֽ��ϴ�.');");
			out.println("location.replace(login_form.jsp?url=cart_view)");
			out.println("</script>");
			out.close();
		}
		String miid = loginInfo.getMi_id();
		if(request.getParameter("cpage")!=null) cpage = Integer.parseInt(request.getParameter("cpage"));
		CouponListSvc couponListSvc = new CouponListSvc();
		rcnt = couponListSvc.getCouponListCount(miid);
		
		ArrayList<Coupon> couponList = couponListSvc.getCouponList(miid,cpage, psize);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setBsize(bsize); 	pageInfo.setCpage(cpage);
		pageInfo.setPcnt(pcnt); 	pageInfo.setPsize(psize); 		
		pageInfo.setSpage(spage);	pageInfo.setRcnt(rcnt);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("couponList", couponList);
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("mypage/coupon_list.jsp");
		dispatcher.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
