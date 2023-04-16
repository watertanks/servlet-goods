package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;


/**
 * Servlet implementation class OrderProcInCtrl
 */
@WebServlet("/order_proc_in")
public class OrderProcInCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public OrderProcInCtrl() {super();}
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
		String kind = request.getParameter("kind");
		System.out.println(kind);
		int realprice = Integer.parseInt(request.getParameter("realprice"));
		String rname = request.getParameter("oi_name");
		String p1 = request.getParameter("p1");
		String p2 = request.getParameter("p2");
		String p3 = request.getParameter("p3");
		String phone = p1 + "-" + p2 + "-" + p3;
	
		String zip = request.getParameter("ma_zip");
		String addr1 = request.getParameter("ma_addr1");
		String addr2 = request.getParameter("ma_addr2");
		String oimemo = request.getParameter("oi_memo");
		String oimemoin = request.getParameter("oi_memo_in");
		
		
		
		String payment = request.getParameter("oi_payment");
		int oiupoint = Integer.parseInt(request.getParameter("oi_upoint"));
		System.out.println(addr1);
	
		OrderInfo oi = new OrderInfo();
		
		oi.setMi_id(miid); 			oi.setOi_name(rname);//������ �̸�
		oi.setOi_phone(phone); 		oi.setOi_zip(zip);
		oi.setOi_addr1(addr1); 		oi.setOi_addr2(addr2);
		oi.setOi_payment(payment); 	oi.setOi_pay(realprice);
		oi.setOi_status((payment.equals("c")? "a" : "b"));
		oi.setOi_memo(oimemo);oi.setOi_memo_in(oimemoin);
		oi.setOi_upoint(oiupoint);
		
		OrderProcInSvc orderProcInSvc = new OrderProcInSvc();
		String result = null, temp="";
		// ���� ����� ������ ������ �ֹ���ȣ, ����� ���ڵ� ����, ����Ǿ���� ����� ���� �� ����
		if(kind.equals("c")) {	// ��ٱ��ϸ� ���� ������ ���
			temp = request.getParameter("ocidxs");
			// ��ٱ��Ͽ��� ������ ��ǰ���� �ε��� ��ȣ��(��ǥ�� ����)
		}else {	// �ٷα����� ���
			String cnt = request.getParameter("cnt");
			String piid = request.getParameter("piid");
			temp=cnt+","+piid;
		}
		result = orderProcInSvc.orderInsert(kind, oi, temp);
		String[] arr = result.split(",");
		System.out.println(arr[0]);
		if(arr[1].equals(arr[2])) { // ���������� ���Ű� �̷��������
			response.sendRedirect("order_end?oiid="+arr[0]);}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
