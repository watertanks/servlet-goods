package ctrl;


import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;


@WebServlet("/order_detail")
public class OrderDetailCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public OrderDetailCtrl() {super(); }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	HttpSession session = request.getSession();
		MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
		if(loginInfo ==null) {
			response.setContentType("text html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('�߸��� ��η� �����̽��ϴ�')");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}// �α��ο� ���� ó��
		String miid = loginInfo.getMi_id();
		String oiid = request.getParameter("oiid");
		
		OrderDetailSvc orderDetailSvc =new OrderDetailSvc();
		OrderInfo orderInfo = orderDetailSvc.getOrderInfo(miid,oiid);
		
		request.setAttribute("orderInfo", orderInfo);
		RequestDispatcher dispartcher = request.getRequestDispatcher("order/order_detail.jsp");
		dispartcher.forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
