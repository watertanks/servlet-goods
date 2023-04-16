package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/product_view")
public class ProductViewCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ProductViewCtrl() { super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//svc로 데이터에서 가져올 내용 전달
		//필요한것: 상품 정보 전체& 옵션별 재고량(옵션이 있으면)
		
		String piid = request.getParameter("piid");
		
		ProductViewSvc productViewSvc = new ProductViewSvc();
		productViewSvc.readUpdate(piid); //조회수 증가
		ProductInfo pi = productViewSvc.getProductInfo(piid, " where pi_isview = 'y' and "); //상품 상세 정보
		pi.setStockList(productViewSvc.getProductStock(piid, " where ps_isview = 'y' and ")); //옵션별 정보 
		
		request.setAttribute("pi", pi);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/product/product_view.jsp");
		dispatcher.forward(request, response);
	}

}
