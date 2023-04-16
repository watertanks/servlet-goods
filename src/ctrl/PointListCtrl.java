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
 * Servlet implementation class PointListCtrl
 */
@WebServlet("/point_list")// miid가져올것
public class PointListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public PointListCtrl() {super();}

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
		
		
		PointListSvc pointListSvc = new PointListSvc();
		int ptotal = 0;
		ptotal = pointListSvc.getPointListSum(miid);
		rcnt = pointListSvc.getPointListCount(miid);
		// �˻��� �Խñ��� �� ������ �Խñ� �Ϸù�ȣ ��°� ��ü ������ �� ����� ���� �ʿ��� ��
		ArrayList<Point> pointList = pointListSvc.getPointList(miid,cpage, psize);
		Point pointtotal= new Point();
		pointtotal.setPtotal(ptotal); 
		// ��� ȭ�鿡�� ������ �Խñ� ����� ArrayList<BbsFree>������ �޾ƿ�
		// �ʿ��� ��ŭ�� �޾ƿ��� ���� cpage�� psize�� �ʿ�
		PageInfo pageInfo = new PageInfo();
		pageInfo.setBsize(bsize); 	pageInfo.setCpage(cpage);
		pageInfo.setPcnt(pcnt); 	pageInfo.setPsize(psize); 		
		pageInfo.setSpage(spage);	pageInfo.setRcnt(rcnt);
		
		// ����¡�� �˻��� �ʿ����������� PageInfo�� �ν��Ͻ������椷
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("pointList", pointList);
		request.setAttribute("pointtotal", pointtotal);
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("mypage/point_list.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
