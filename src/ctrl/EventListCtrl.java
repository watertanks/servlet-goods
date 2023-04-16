package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;


@WebServlet("/event_list")
public class EventListCtrl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
     
    public EventListCtrl() { super(); }

    protected void doProcess (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		request.setCharacterEncoding("utf-8");
		int cpage = 1, psize = 4, bsize = 10, rcnt = 0, pcnt = 0, spage = 0;
		// 현재 페이지 번호, 페이지 크기, 블록 크기, 레코드(상품) 개수
		// 전체 페이지 개수, 시작 페이지 번호 등을 저장할 변수들 선언
		if(request.getParameter("cpage")!=null) {
			cpage = Integer.parseInt(request.getParameter("cpage"));
		}
		
    	String orderby = ""; //정렬조건	a : 대기중  , b: 진행중 , c: 마감임박 , d :종료
		String o = request.getParameter("orderby"); 
		
		if(o==null||o.equals("")) { o = "a"; }
 		if(o!=null && !o.equals("")) 
 		{
 			switch(o) 
 			{
			case "a": 
				orderby += "order by be_status "; break;
			case "b": 
				orderby += " order by be_status desc "; break;
			}
		}
    	EventListSvc eventListSvc = new EventListSvc();
    	rcnt = eventListSvc.getEventCount();
    	ArrayList<EventInfo> eventList = eventListSvc.getEventList(cpage, psize, orderby);
    	
    
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
    	
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("eventList", eventList);
		// dispatcher 방식으로 view를 보여주므로 request 객체에 필요한 정보를 담아 넘겨줌
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("event/event_list.jsp");
		dispatcher.forward(request, response);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


}
