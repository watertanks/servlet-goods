package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;

@WebServlet("/order_form")
public class OrderFormCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public OrderFormCtrl() {super();}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	String kind = request.getParameter("kind");

		HttpSession session = request.getSession();
		MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
		if(loginInfo ==null) {
			response.setContentType("text html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('�α����� ����Ͻ� �� �ֽ��ϴ�.')");
			out.println("location.replace('login_form.jsp?url=cart_view');");
			out.println("</script>");
		}
		String miid = loginInfo.getMi_id();

		String select = " select a.pi_id, a.pi_img1, a.pi_name, b.ps_am_name, a.pi_price, a.pi_dc,";
		String from =" from t_product_info a, t_product_stock b ";
		String where = " where a.pi_id = b.pi_id  and a.pi_isview='y' and b.ps_isview='y' ";
		
		if(kind.equals("c")) {// 장바구니구매
			select += "  c.oc_cnt cnt, c.oc_idx "; // c.oc_cnt�� cnt�� ����Ͽ� �������ſ� �����Ͽ� �����߻� ���� 
			from += ", t_order_cart c ";
			where += " and a.pi_id = c.pi_id and b.ps_idx = c.ps_idx" + 
					" and c.mi_id = '"+miid+"' and (";
			String[] arr = request.getParameterValues("chk");
			for (int i=1; i<arr.length; i++) {
				if (i == 1) where += "c.oc_idx = " + arr[i];
				else		where += " or c.oc_idx = " +arr[i];	
			}
			where +=") order by a.pi_id, c.ps_idx ";
			
		}else { // 직접구매
			String piid = request.getParameter("piid");
			String psidx = request.getParameter("psidx");
			int cnt = Integer.parseInt(request.getParameter("cnt"));
			select +=cnt + " cnt ";
			where += " and a.pi_id = '"+piid+"' and b.ps_idx = " + psidx;
		}
		OrderFormSvc orderFormSvc  =new OrderFormSvc ();
		ArrayList<OrderCart> pdtList = orderFormSvc.getBuyList(kind,select+from+where);
		ArrayList<MemberAddr> addrList = orderFormSvc.getAddrList(miid);
		
		request.setAttribute("pdtList", pdtList);
		request.setAttribute("addrList", addrList);
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/order/order_form.jsp");
		dispatcher.forward(request,response);
	}
}
