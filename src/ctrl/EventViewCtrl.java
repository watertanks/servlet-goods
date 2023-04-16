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
		// ����ڰ� ������ �Խñ۵��� ������� EventInfo�� �ν��Ͻ��� �޾ƿ�
		EventInfo el = eventViewSvc.getEventInfo(beidx);
		
		if(el == null) 
		{	
			// ������ �Խñ��� ������
			response.setContentType("text/html; charset=utf-8"); 
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('�߸��� ��η� �����̽��ϴ�.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		else 
		{		
			// ������ �Խñ��� ������
			request.setAttribute("el", el);
			RequestDispatcher dispatcher = request.getRequestDispatcher("event/event_view.jsp");
			dispatcher.forward(request, response);
			
		}
	}

}
