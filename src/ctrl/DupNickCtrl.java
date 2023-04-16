package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;


@WebServlet("/dupNick")
public class DupNickCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DupNickCtrl() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String unick = request.getParameter("unick").trim().toLowerCase();
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(unick != null) {
			try {
				ChkDupSvc chkDupSvc = new ChkDupSvc();
				int result = chkDupSvc.chkDupNick(unick);
				out.println(result);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
