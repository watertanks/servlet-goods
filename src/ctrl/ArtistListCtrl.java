package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;


@WebServlet("/artist_list")
public class ArtistListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ArtistListCtrl() { super(); }

    protected void doProcess (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int cpage = 1, psize = 8, bsize = 10, rcnt = 0, pcnt = 0, spage = 1;
	
		if(request.getParameter("cpage")!=null) {
			cpage = Integer.parseInt(request.getParameter("cpage"));
		}
		HttpSession session = request.getSession();
		AdminInfo adminInfo = (AdminInfo)session.getAttribute("adminInfo");
		String orderby = ""; 
 		String o = request.getParameter("orderby"); 
 		
 		if(o==null||o.equals("")) { o = "a"; }
 		if(o!=null && !o.equals("")) {
 			
			switch(o) {
			case "a": 
				orderby += "order by ac_date desc "; break;
			case "b": 
				orderby += "order by ac_name_k "; break;
			}
		}
 		 
    	ArtistListSvc artistListSvc = new ArtistListSvc();
    	rcnt = artistListSvc.getArtistCount();
    	ArrayList<ArtistCode> artistList = artistListSvc.getArtistList(cpage, psize, orderby);
    	
    
    	pcnt = rcnt/psize;
		if(rcnt%psize > 0) { pcnt++; } 
		spage = (cpage -1)/psize * psize+1; 
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setBsize(bsize);
		pageInfo.setCpage(cpage);
		pageInfo.setRcnt(rcnt);
		pageInfo.setPcnt(pcnt);
		pageInfo.setPsize(psize);
		pageInfo.setSpage(spage);
    	
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("artistList", artistList);
		
		if(adminInfo==null) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("artist/artist_list.jsp");
		dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/admin_artistlist.jsp");
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
