package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;



@WebServlet("/cart_view")
public class CartViewCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CartViewCtrl() {super(); }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
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
		CartViewSvc cartViewSvc = new CartViewSvc();
		ArrayList<OrderCart> cartList = cartViewSvc.getCartList(miid);
		
		request.setAttribute("cartList", cartList);
		
		RequestDispatcher dispatcher= request.getRequestDispatcher("order/cart_view.jsp");
		dispatcher.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
