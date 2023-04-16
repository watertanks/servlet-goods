package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/artist_proc_in")
@MultipartConfig(
		fileSizeThreshold = 0,
		location = "E:/jhe/web/work/goods/WebContent/artist/artist_img"
	)
public class ArtistProcIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ArtistProcIn() {super();}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
		int ai_idx = -1;
		if(adminInfo == null) { 
			out.println("<script>");
			out.println("alert('�α����ϼ���');");
			out.println("location.href = 'admin_login_form';");
			out.println("</script>");
			out.close();
		}else {
			ai_idx = adminInfo.getAi_idx();
		}
		
		/*
		��ǰ ����� submit ���(request)
		ac ��Ƽ��Ʈ �ڵ�
		��Ƽ��Ʈ��(�ѱ�) namek
		��Ƽ��Ʈ��(����) namee
		��� �̸� (����) memnamek
		��� �̸� (����) memnamee
		�̹��� piimg(����)
		*/
		String[] memnamek = request.getParameterValues("memnamek");
		String[] memnamee = request.getParameterValues("memnamee");
		
		Part part1 = request.getPart("piimg");
		
		
		ArtistCode ac = new ArtistCode();
		ac.setAc_id(request.getParameter("accode"));
		ac.setAc_name_k(request.getParameter("namek"));
		ac.setAc_name_e(request.getParameter("namee"));
		ac.setAc_img(getUploadFileName(part1));
		
		 ArrayList<ArtistMember> amList = new ArrayList<ArtistMember>();
		 
		ArtistMember am = null;
		
		System.out.println(memnamek.length);
		
		for(int i = 1; i < memnamek.length; i++ ) {
			am = new ArtistMember();
			am.setAm_name_e(memnamee[i]);
			am.setAm_name_k(memnamek[i]);
			amList.add(am);
			System.out.println(am);
		}
		
		
		
		ArtistProcInSvc artistProcInSvc = new ArtistProcInSvc();
		
		
		int result = artistProcInSvc.artistProcIn(ac, ai_idx, amList);
		
		 if(result == 1) {
	        	response.sendRedirect("artist_list");
	        }else {
				out.println("<script>");
				out.println("alert('��Ƽ��Ʈ ��� ����');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
	        }
		}
		
	
	
	private String getUploadFileName(Part part) {
		String uploadFileName = null;
		String contentDisposition = part.getHeader("content-disposition");
		String[] contentSplitStr = contentDisposition.split(";");
		
		int fIdx = contentSplitStr[2].indexOf("\"");
		int sIdx = contentSplitStr[2].lastIndexOf("\"");
		uploadFileName = contentSplitStr[2].substring(fIdx+1, sIdx);

		try {
			if(uploadFileName!=null && !uploadFileName.equals("")) {
	        	part.write(uploadFileName);
	        }else  { return ""; } //�Է��� �̹����� ���� ���
		}catch(Exception e) { //�ߺ� ������ �����
			e.printStackTrace();
		}
		return uploadFileName;
	}

}
