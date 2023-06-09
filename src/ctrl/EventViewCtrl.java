package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;

@WebServlet("/event_view")
public class EventViewCtrl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public EventViewCtrl() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int beidx = Integer.parseInt(request.getParameter("beidx"));
		EventViewSvc eventViewSvc = new EventViewSvc();
		// 사용자가 선택한 게시글들의 내용들을 EventInfo형 인스턴스로 받아옴
		EventInfo el = eventViewSvc.getEventInfo(beidx);
		
		if(el == null) 
		{	
			// 보여줄 게시글이 없으면
			response.setContentType("text/html; charset=utf-8"); 
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 경로로 들어오셨습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		else 
		{		
			// 보여줄 게시글이 있으면
			request.setAttribute("el", el);
			RequestDispatcher dispatcher = request.getRequestDispatcher("event/event_view.jsp");
			dispatcher.forward(request, response);
			
		}
	}

}
