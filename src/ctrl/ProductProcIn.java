package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/product_proc_in")
@MultipartConfig(
		fileSizeThreshold = 0,
		location = "E:/jhe/web/work/goods/WebContent/artist/artist_img"
	)	
public class ProductProcIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ProductProcIn() {super();}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
		int ai_idx = -1;
		if(adminInfo == null) { 
			out.println("<script>");
			out.println("alert('로그인하세요');");
			out.println("location.href = 'admin_login_form';");
			out.println("</script>");
			out.close();
		}else {
			ai_idx = adminInfo.getAi_idx();
		}
		/*
		상품 등록폼 submit 목록(request)
		ac 아티스트 코드
		ap 소분류 코드
		상품명 name
		가격 price
		할인 dc
		특별상품 special(n, h, m)
		판매기간 sdate(yymmdd)
		option 멤버명 배열
		stock 재고량 배열 
		이미지 piimg1~3(파일)
		이미지상세 pidesc (파일)
		상품정보 text
		게시여부 isview(y/n)
		*/
		
        String[] option = request.getParameterValues("option"); //hidden > length +=1;
        String[] stock = request.getParameterValues("stock");
        Part part1 = request.getPart("piimg1");
        Part part2 = request.getPart("piimg2");
        Part part3 = request.getPart("piimg3");
        Part desc = request.getPart("pidesc");

        String ac_id = request.getParameter("ac");
        String ap_id = request.getParameter("ap");
        String pi_id = ac_id+ap_id; //미완성이며 dao에서 마저 완성한다
        //piid = ac_id + ap_id +sdate+ pi_idx+1
        
        ProductInfo pi = new ProductInfo();
        pi.setPi_name(request.getParameter("name"));
        pi.setPi_price(Integer.parseInt(request.getParameter("price")));
        pi.setPi_dc(Integer.parseInt(request.getParameter("dc")));
        pi.setPi_id(pi_id);
        pi.setAc_id(ac_id);
        pi.setAp_id(ap_id);
        
        pi.setPi_img1(getUploadFileName(part1));
        pi.setPi_img2(getUploadFileName(part2));
        pi.setPi_img3(getUploadFileName(part3));
        pi.setPi_desc(getUploadFileName(desc));
 
        pi.setPi_special(request.getParameter("special"));
        pi.setPi_sdate(request.getParameter("sdate"));
        pi.setPi_text(request.getParameter("text"));
        pi.setPi_isview(request.getParameter("isview"));
        
        ArrayList<ProductStock> psList = new ArrayList<ProductStock>();
        ProductStock ps = null;

    	for(int i = 1;i<option.length;i++) {
        	ps = new ProductStock();
        	ps.setPs_am_name(option[i]);
        	ps.setPs_stock(Integer.parseInt(stock[i]));
        	psList.add(ps);
        }
        
        pi.setStockList(psList);
        
        ProductProcInSvc productProcInSvc = new ProductProcInSvc();
        int result = productProcInSvc.productProcIn(pi, ai_idx);
        
        if(result == 1) {
        	response.sendRedirect("product_list");
        }else {
			out.println("<script>");
			out.println("alert('상품등록 실패');");
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
	        }else  { return ""; } //입력한 이미지가 없을 경우
		}catch(Exception e) { //중복 오류가 날경우
			return uploadFileName;
		}
		return uploadFileName;
	}
	
}
