package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;


@WebServlet("/artist_form_in")
public class ArtistFormIn extends HttpServlet {	private static final long serialVersionUID = 1L;
    public ArtistFormIn() {super(); }

	
    
    
   
    
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
		
		ArtistListSvc artistListSvc = new ArtistListSvc();
		ArrayList<ArtistCode> artistList = artistListSvc.getArtistList(0, 0, " ");
		
		request.setAttribute("artistList", artistList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/artist_form_in.jsp");
		dispatcher.forward(request, response);
	}

	

}
