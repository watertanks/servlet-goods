package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import vo.*;
import svc.*;

@WebServlet("/product_form_in")
public class ProductFormIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductFormIn() {super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
		
		if(adminInfo == null) { 
			out.println("<script>");
			out.println("alert('로그인하세요');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		
		ProductListSvc productListSvc = new ProductListSvc();
		ArrayList<ArtistCode> bigList = productListSvc.getBigList();
		ArrayList<ArtistProduct> smallList = productListSvc.getSmallList();
		ArrayList<ArtistMember> artistMemberList = productListSvc.getArtistMemberList();
		
		request.setAttribute("bigList", bigList);
		request.setAttribute("smallList", smallList);
		request.setAttribute("artistMemberList", artistMemberList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product_form_in.jsp");
		dispatcher.forward(request, response);
		
	}

}
