package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/product_list")
public class ProductListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ProductListCtrl() { super(); }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int cpage = 1, psize = 8, bsize = 10, rcnt = 0, pcnt = 0, spage = 1;
		//현재페이지 페이지크기 블록크기 레코드(상품)개수 전체페이지수 시작페이지번호
		
		if(request.getParameter("cpage")!=null) {
			cpage = Integer.parseInt(request.getParameter("cpage"));
		}
		HttpSession session = request.getSession();
		AdminInfo adminInfo = (AdminInfo)session.getAttribute("adminInfo");
		
		/*where: db검색 조건
		* 상품 공개 여부, 대분류, 소분류, 검색어
		* db alias: pi, ac, ap
		*/
		String where = "";
		
		String ac = request.getParameter("ac"); //대분류
		String ap = request.getParameter("ap"); //소분류
		String key = request.getParameter("key"); //검색어
		String schtype = request.getParameter("schtype"); //검색조건
 		
 		if(ac != null && !ac.equals("")) {
 			where += " and ac.ac_id = '"+ac+"' ";
 		}
 		
 		if(ap != null && !ap.equals("")) {
 			where += " and ap.ap_id = '"+ap+"' ";
 		}
 		
 		if(key != null && !key.equals("")) {
 			key = key.trim();
 			if(schtype!=null && schtype.equals("a")) {
 				where += " and ac.ac_name_k like '%"+key+"%' ";
 			}else {
 				where += " and pi.pi_name like '%"+key+"%' ";	
 			}
 		}
 		
 		if(adminInfo == null) {
			where += " and pi.pi_isview = 'y' ";
		}
 		if(!where.equals("")) {
 			where = where.substring(5, where.length());
 			where = " where "+where;
 		}
 		String orderby = ""; //정렬조건
 		String o = request.getParameter("orderby"); 
 		//a: 기본값(등록역순) b: 판매량 c: 낮은가격순 d: 높은가격 
 		if(o==null||o.equals("")) { o = "a"; }
 		if(o!=null && !o.equals("")) {
 			
			switch(o) {
			case "a": 
				orderby += "order by pi.pi_date desc "; break;
			case "b": 
				orderby += "order by pi.pi_sale desc "; break;
			case "c": 
				orderby += "order by pi.pi_price "; break;
			case "d": 
				orderby += "order by pi.pi_price desc "; break;
			}
 		}
 		
		ProductListSvc productListSvc = new ProductListSvc();
		rcnt = productListSvc.getProductCount(where); //검색된 상품의 전체개수
		ArrayList<ProductInfo> productList = new ArrayList<ProductInfo>();
		productList = productListSvc.getProductList(cpage, psize, where, orderby);
				
		//검색된 상품중 현재페이지에서 보여줄 목록
		ArrayList<ArtistCode> bigList = productListSvc.getBigList();
		ArrayList<ArtistProduct> smallList = productListSvc.getSmallList();
		
		pcnt = rcnt/psize;
		if(rcnt%psize > 0) { pcnt++; } //전체 페이지수
		spage = (cpage -1)/psize * psize+1; //블록시작 번호
		
		PageInfo pageInfo = new PageInfo(); //페이지 정보 저장
		pageInfo.setBsize(bsize);
		pageInfo.setCpage(cpage);
		pageInfo.setRcnt(rcnt);
		pageInfo.setPcnt(pcnt);
		pageInfo.setPsize(psize);
		pageInfo.setSpage(spage);
		
		//전달할 정보 목록: 페이징, 상품정보, 아티스트정보, 소분류정보
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("productList", productList); 
		request.setAttribute("bigList", bigList);
		request.setAttribute("smallList", smallList);
		
		
		if(adminInfo==null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/product/product_list.jsp");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/admin_pdtlist.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
