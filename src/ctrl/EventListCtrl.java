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
		// ���� ������ ��ȣ, ������ ũ��, ��� ũ��, ���ڵ�(��ǰ) ����
		// ��ü ������ ����, ���� ������ ��ȣ ���� ������ ������ ����
		if(request.getParameter("cpage")!=null) {
			cpage = Integer.parseInt(request.getParameter("cpage"));
		}
		
    	String orderby = ""; //��������	a : �����  , b: ������ , c: �����ӹ� , d :����
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
		if(rcnt%psize > 0) { pcnt++; } //��ü ��������
		spage = (cpage -1)/psize * psize+1; //��Ͻ��� ��ȣ
		
		PageInfo pageInfo = new PageInfo(); //������ ���� ����
		pageInfo.setBsize(bsize);
		pageInfo.setCpage(cpage);
		pageInfo.setRcnt(rcnt);
		pageInfo.setPcnt(pcnt);
		pageInfo.setPsize(psize);
		pageInfo.setSpage(spage);
    	
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("eventList", eventList);
		// dispatcher ������� view�� �����ֹǷ� request ��ü�� �ʿ��� ������ ��� �Ѱ���
		
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
