package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;


@WebServlet("/order_list")
public class OrderListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public OrderListCtrl() {super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
    	int cpage = 1, psize =10, bsize=10, rcnt=0, pcnt=0, spage=0;
    	//���� ������ ��ȣ, ������ ũ��, ��� ũ��, ���ڵ�(��ǰ����), ��ü������ ����, ���������� ��ȣ ���� ������ ������ ����
  
    	if (request.getParameter("cpage") != null) {
    		cpage = Integer.parseInt(request.getParameter("cpage"));
    	}

		//�˻� ���� where �� ����
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

		//주문내역 OrderInfo제네릭 ArrayList 형 인스턴스 생성
		OrderListSvc orderListSvc = new OrderListSvc();
		ArrayList<OrderInfo> orderList = orderListSvc.getOrderList(miid,cpage,psize);
		rcnt = orderListSvc.getOrderCount(miid);
		//페이지위지청보
		PageInfo pageInfo = new PageInfo();
		pageInfo.setBsize(bsize); 	pageInfo.setCpage(cpage);
		pageInfo.setPcnt(pcnt); 	pageInfo.setPsize(psize); 		
		pageInfo.setSpage(spage);	pageInfo.setRcnt(rcnt);

		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("orderList", orderList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/order/order_list.jsp");
		dispatcher.forward(request, response);
	}
}	
