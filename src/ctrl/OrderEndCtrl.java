package ctrl;


import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;

@WebServlet("/order_end")
public class OrderEndCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public OrderEndCtrl() {super();}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String oiid = request.getParameter("oiid"); // �ֹ� ��ȣ
		HttpSession session = request.getSession();
		MemberInfo mi = (MemberInfo)session.getAttribute("loginInfo");
		String miid = mi.getMi_id(); 
		OrderEndSvc orderEndSvc = new OrderEndSvc();
		OrderInfo orderInfo = orderEndSvc.getOrderEndInfo(miid, oiid);
		if(orderInfo == null) { 
			response.setContentType("text html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 접근입니다')");
			out.println("location.replace('/goods/');");
			out.println("</script>");
			out.close();
		}else{ // �ֹ� ������ ���� ���
			request.setAttribute("orderInfo", orderInfo);
			RequestDispatcher dispartcher = request.getRequestDispatcher("order/order_end.jsp");
			dispartcher.forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
