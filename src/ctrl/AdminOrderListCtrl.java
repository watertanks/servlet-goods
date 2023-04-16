package ctrl;

import java.io.*;
import java.net.URLEncoder;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;


@WebServlet("/admin_order_list")
public class AdminOrderListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminOrderListCtrl() {super();}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
    	int cpage = 1, psize =10, bsize=10, rcnt=0, pcnt=0, spage=0;
    	String schtype = request.getParameter("schtype"); 
		String keyword = request.getParameter("keyword"); 
    	if (request.getParameter("cpage") != null) {
    		cpage = Integer.parseInt(request.getParameter("cpage"));
    	}
    	String where  = "";
		if(schtype == null || keyword == null) {
			schtype = "";
			keyword = "";
		}else if(!schtype.equals("") && !keyword.equals("") ){	
	
			URLEncoder.encode(keyword, "UTF-8");
			
			if(schtype.equals("miid")) { // �˻������� '����+����'�� ���
				where += " and mi_id='"+schtype+ "' like '%"+keyword+"%'  ";
			}else if(schtype.equals("oiid") ){ 
				where += " and oi_id='"+schtype+ "' like '%"+keyword+"%'  ";
				
			}else if(schtype.equals("piid")) {
				where += " and pi_id='"+schtype+ "' like '%"+keyword+"%'  ";
			}
		}
    	PrintWriter out= response.getWriter();
    	
			



		//주문내역 OrderInfo제네릭 ArrayList 형 인스턴스 생성
		OrderListSvc orderListSvc = new OrderListSvc();
		ArrayList<OrderInfo> orderList = orderListSvc.getAdminOrderList(where, cpage,psize);
		rcnt = orderListSvc.getAdminOrderCount();
		//페이지위지청보
		PageInfo pageInfo = new PageInfo();
		pageInfo.setBsize(bsize); 	pageInfo.setCpage(cpage);
		pageInfo.setPcnt(pcnt); 	pageInfo.setPsize(psize); 		
		pageInfo.setSpage(spage);	pageInfo.setRcnt(rcnt);
		pageInfo.setSchtype(schtype); pageInfo.setKeyword(keyword);
		request.setAttribute("pageInfo", pageInfo);	
		request.setAttribute("orderList", orderList);
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("admin/admin_orderlist.jsp");
			dispatcher.forward(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
