package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/product_proc_del")
public class ProductProcDel extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductProcDel() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
		int ai_idx = -1;
		if(adminInfo == null) { 
			out.println("<script>");
			out.println("alert('로그인하세요');");
			out.println("location.href = 'admin_login_form';");
			out.println("</script>");
			out.close();
		}else {
			ai_idx = adminInfo.getAi_idx();
		}
		
		String pi_id = request.getParameter("piid");
		ProductProcDelSvc productProcDelSvc = new ProductProcDelSvc();
		int result = productProcDelSvc.productProcDel(pi_id);
		
		if(result == 1) {
        	response.sendRedirect("product_list");
        }else {
			out.println("<script>");
			out.println("alert('상품삭제 실패');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
        }
	}

}
