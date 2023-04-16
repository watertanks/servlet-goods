package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import svc.*;
import vo.*;

@WebServlet("/index_ctrl")
public class IndexCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public IndexCtrl() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		AllArtistListSvc allArtistListSvc = new AllArtistListSvc();
		ArrayList<ArtistCode> allArtistList = allArtistListSvc.getAllArtistList();
		
		List<String> artistNameList = new ArrayList<String>();
		String a = "";
		for(int i = 0; i < allArtistList.size(); i++) {
			a = allArtistList.get(i).getAc_name_e();
			artistNameList.add(a);
		}
		//System.out.println("컨트롤러 : " + artistNameList);
		
		if(!allArtistList.isEmpty()) {
			out.println(artistNameList);
			
			//request.setAttribute("artistNameList", artistNameList);
			
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			//dispatcher.forward(request, response);
		}else {
			out.println("<script>");
			out.println("alert('서버와의 통신중 에러가 발생했습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
	}
	
}
