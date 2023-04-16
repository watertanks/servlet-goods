package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import vo.*;
import svc.*;

@WebServlet("/product_form_up")
public class ProductFormUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductFormUp() {super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
		
		if(adminInfo == null) { 
			out.println("<script>");
			out.println("alert('로그인하세요');");
			out.println("location.href = 'admin_login_form';");
			out.println("</script>");
			out.close();
		}
		String piid = request.getParameter("piid");
		//상품아이디 > 상품상세정보, 옵션정보
		
		ProductViewSvc productViewSvc = new ProductViewSvc();
		ProductInfo pi = productViewSvc.getProductInfo(piid, " where "); //상품 상세 정보
		pi.setStockList(productViewSvc.getOptionList(piid)); //옵션별 정보 
		
		request.setAttribute("pi", pi);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product_form_up.jsp");
		dispatcher.forward(request, response);
		
	}

}
