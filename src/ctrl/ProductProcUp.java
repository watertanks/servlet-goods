package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/product_proc_up")
@MultipartConfig(
		fileSizeThreshold = 0,
		location = "E:/jhe/web/work/goods/WebContent/"
	)	

public class ProductProcUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductProcUp() {super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
		int pi_ai_idx = -1;
		if(adminInfo == null) { 
			out.println("<script>");
			out.println("alert('로그인하세요');");
			out.println("location.href = 'admin_login_form';");
			out.println("</script>");
			out.close();
		}else {
			pi_ai_idx = adminInfo.getAi_idx();
		}
		/*
		상품 등록폼 submit 목록(request)
		상품명 name
		가격 price
		할인 dc
		특별상품 special(n, h, m)
		판매기간 sdate(yymmdd)
		옵션 배열 option[], stock[] (멤버명 재고량)
		이미지 piimg1~3(파일), pidesc(파일)
		상품정보 text 
		게시여부 isview(y/n)
		*/
        
		//이미지 파일 경로 설정
		//이전 파일들 경로
		String old[] = new String[4];
		old[0]= request.getParameter("oldpiimg1");
		old[1] = request.getParameter("oldpiimg2");
		old[2] = request.getParameter("oldpiimg3");
		old[3] = request.getParameter("oldpidesc");
		
		//파일받기
        Part part1 = request.getPart("piimg1");
        Part part2 = request.getPart("piimg2");
        Part part3 = request.getPart("piimg3");
        Part desc = request.getPart("pidesc");
        
        String pi_id = request.getParameter("piid"); 
        
        ProductInfo pi = new ProductInfo();
        pi.setPi_name(request.getParameter("name"));
        pi.setPi_price(Integer.parseInt(request.getParameter("price")));
        pi.setPi_dc(Integer.parseInt(request.getParameter("dc")));
        pi.setPi_id(pi_id);
        
        pi.setPi_img1(getUploadFileName(part1, old[0]));
        pi.setPi_img2(getUploadFileName(part2, old[1]));
        pi.setPi_img3(getUploadFileName(part3, old[2]));
        pi.setPi_desc(getUploadFileName(desc, old[3]));
        
        pi.setPi_special(request.getParameter("special"));
        pi.setPi_sdate(request.getParameter("sdate"));
        pi.setPi_text(request.getParameter("text"));
        pi.setPi_isview(request.getParameter("isview"));
        
        String[] option = request.getParameterValues("option");
		String[] stock = request.getParameterValues("stock");
        
        ArrayList<ProductStock> psList = new ArrayList<ProductStock>();
        ProductStock ps = null;
        
        int[] cnt = new int[stock.length];
        for(int i = 0;i<cnt.length;i++) {
        	try {
        		cnt[i] = Integer.parseInt(stock[i]);
        	}catch(Exception e) {
        		cnt[i] = -1;
        	}
        }
        
        
        for(int i = 0;i<option.length;i++) {
        	ps = new ProductStock();
        	if(option[i].equals("없음")) { option[i] = ""; }
        	if(cnt[i]!=-1) {
        		ps.setPs_am_name(option[i]);
            	ps.setPs_stock(cnt[i]);
            	psList.add(ps);
        	}
        }
        pi.setStockList(psList);
        
        ProductProcUpSvc productProcUpSvc = new ProductProcUpSvc();
        int result = productProcUpSvc.productProcUp(pi, pi_ai_idx);
        
        if(result == 1) {
        	response.sendRedirect("product_list");
        }else {
			out.println("<script>");
			out.println("alert('상품수정 실패');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
        }
	}
	
	private String getUploadFileName(Part part, String oldFileName) {
		String uploadFileName = null;
		String contentDisposition = part.getHeader("content-disposition");
		String[] contentSplitStr = contentDisposition.split(";");
		
		int fIdx = contentSplitStr[2].indexOf("\"");
		int sIdx = contentSplitStr[2].lastIndexOf("\"");
		uploadFileName = contentSplitStr[2].substring(fIdx+1, sIdx);

		try {
			if(uploadFileName!=null && !uploadFileName.equals("")) {
	        	part.write(uploadFileName);
	        }else  { return oldFileName; } //입력한 이미지가 없을 경우
		}catch(Exception e) { //중복 오류가 날경우
			return uploadFileName;
		}
		return uploadFileName;
	}
}
