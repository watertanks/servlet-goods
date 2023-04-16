package dao;

import static db.JdbcUtil.*; 
import vo.*;
import java.sql.*;
import java.util.*;

public class ProductProcDao {
	//상품관련 쿼리 처리
	private static ProductProcDao productProcDao;
	private Connection conn;
	private ProductProcDao() { }

	public static ProductProcDao getInstance() {
		if(productProcDao==null) { productProcDao = new ProductProcDao(); }
		return productProcDao;
	}
	
	public void setConnection(Connection conn) {this.conn = conn;}

	//product_list.jsp용 메소드
	public int getProductCount(String where) { //검색되는 상품의 개수 리턴
		int rcnt = 0;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) from t_product_info as pi join t_artist_code as ac "+
					" on pi.ac_id = ac.ac_id join t_artist_product as ap on pi.ap_id = ap.ap_id "
					+where;
			//where 절에 alias 가 이미 사용되었으므로 추가한다
			//System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				rcnt = rs.getInt(1); 
			}
		}catch(Exception e) {
			System.out.println("productProcDao count 오류");
			e.printStackTrace();
		}
		finally {close(rs); close(stmt);}
		return rcnt;
	}
	
	public ArrayList<ProductInfo> getProductList(int cpage, int psize, String where, String orderby){
		//상품목록보기용 정보
		//기본 정보만 가지고 배열을 만든다
		ArrayList<ProductInfo> productList = new ArrayList<ProductInfo>();
		ProductInfo pi = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select pi.pi_id, pi.pi_name, pi.pi_img1, pi.pi_dc, "+
					" pi.pi_special, pi.pi_price, pi_isview, ac.ac_id, ac.ac_name_k, "+
					" ap.ap_id, ap.ap_name from t_product_info as pi join t_artist_code as ac join "+
					"t_artist_product as ap on pi.ac_id = ac.ac_id and pi.ap_id = ap.ap_id "+
					where + orderby+" limit "+((cpage-1)*psize)+","+psize;
			//System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				pi = new ProductInfo();
				pi.setPi_id(rs.getString("pi_id"));
				pi.setPi_isview(rs.getString("pi_isview"));
				pi.setPi_img1(rs.getString("pi_img1"));
				pi.setPi_name(rs.getString("pi_name"));
				pi.setPi_price(rs.getInt("pi_price"));
				pi.setPi_dc(rs.getInt("pi_dc"));
				pi.setPi_special(rs.getString("pi_special"));
				pi.setAc_id(rs.getString("ac_id"));
				pi.setAc_name(rs.getString("ac_name_k"));
				pi.setAp_id(rs.getString("ap_id"));
				pi.setAp_name(rs.getString("ap_name"));
				
				productList.add(pi);
			}
		}catch(Exception e) {
			System.out.println("productProcDao list 오류");
			e.printStackTrace();
		}
		finally { close(rs); close(stmt);}
		
		return productList;
	}
	
	public ArrayList<ArtistCode> getBigList(){ 
		//아티스트 목록(아티스트 페이지로 넘겨줄수  있음)
		ArrayList<ArtistCode> bigList = new ArrayList<ArtistCode>();
		ArtistCode ac = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from t_artist_code";
			//System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				ac = new ArtistCode();
				ac.setAc_id(rs.getString("ac_id"));
				ac.setAc_name_k(rs.getString("ac_name_k"));
				bigList.add(ac);
			}
			
		}catch(Exception e) {
			System.out.println("productProcDao bigList 오류");
			e.printStackTrace();
		}
		finally { close(rs); close(stmt);}
		
		return bigList;
	}
	
	public ArrayList<ArtistProduct> getSmallList(){ 
		//소분류 목록(소분류 메뉴 출력용)
		ArrayList<ArtistProduct> smallList = new ArrayList<ArtistProduct>();
		ArtistProduct ap = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql ="select * from t_artist_product ";
			//System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				ap = new ArtistProduct();
				ap.setAp_id(rs.getString("ap_id"));
				ap.setAp_name(rs.getString("ap_name"));
				
				smallList.add(ap);
			}
			
		}catch(Exception e) {
			System.out.println("productProcDao getSmallList 오류");
			e.printStackTrace();
		}
		finally { close(rs); close(stmt);}
		return smallList;
	}

	
	//product_detail.jsp
	public int readUpdate(String piid) { //조회수 1증가
		int result = 0;
		Statement stmt = null;
		try {
			String sql = "update t_product_info set pi_read = pi_read+1 where pi_id = '"+piid+"'";
			//System.out.println(sql);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		}catch(Exception e) {
			System.out.println("productProcDao readupdate 오류");
			e.printStackTrace();
		}
		finally { close(stmt);}
		return result;
	}
	
	public ProductInfo getProductInfo(String piid, String where) { 
		//상품상세보기 용도의 정보 가져오기
		ProductInfo pi = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select pi.*, ap.ap_name, ac.ac_name_k "+
				" from t_product_info as pi join t_artist_product as ap join t_artist_code as ac on "+
				" pi.ap_id = ap.ap_id and pi.ac_id = ac.ac_id "+where+
				" pi.pi_id = '"+piid+"' ";
			//join으로 데이터 가져오기
			//System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				pi = new ProductInfo();
				pi.setPi_id(rs.getString("pi_id")); 
				pi.setPi_name(rs.getString("pi_name"));
				pi.setPi_price(rs.getInt("pi_price"));
				pi.setPi_dc(rs.getInt("pi_dc"));
				pi.setPi_img1(rs.getString("pi_img1"));
				pi.setPi_img2(rs.getString("pi_img2"));
				pi.setPi_img3(rs.getString("pi_img3"));
				pi.setPi_desc(rs.getString("pi_desc"));
				pi.setPi_text(rs.getString("pi_text"));
				pi.setPi_sdate(rs.getString("pi_sdate"));
				pi.setPi_special(rs.getString("pi_special"));
				pi.setPi_isview(rs.getString("pi_isview"));
				pi.setAc_id(rs.getString("ac_id"));
				pi.setAp_id(rs.getString("ap_id"));
				pi.setAc_name(rs.getString("ac_name_k"));
				pi.setAp_name(rs.getString("ap_name"));
	
			}
		}catch(Exception e) {
			System.out.println("productProcDao getproductinfo 오류");
			e.printStackTrace();
		}
		finally { close(rs); close(stmt);}
		
		return pi;
	}
	
	public ArrayList<ProductStock> getStockList(String piid, String where){ //특정상품의 옵션별 재고량목록 
		ArrayList<ProductStock> psList = new ArrayList<ProductStock>();
		Statement stmt = null;
		ResultSet rs = null;
		ProductStock ps = null;
		
		try {
			String sql = "select * from t_product_stock "+where+" pi_id = '"+piid+"'";
			//System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ps = new ProductStock();
				ps.setPs_am_name(rs.getString("ps_am_name"));
				ps.setPs_stock(rs.getInt("ps_stock"));
				ps.setPs_idx(rs.getInt("ps_idx"));
				ps.setPi_id(piid);
				ps.setPs_isview(rs.getString("ps_isview"));
				
				psList.add(ps);
			}
			
		}catch(Exception e) {
			System.out.println("productProcDao getStockList 오류");
			e.printStackTrace();
		}
		finally { close(rs); close(stmt);}
		
		return psList;
	}
	
	public ArrayList<ArtistMember> getArtistMemberList(){ 
		ArrayList<ArtistMember> artistMemberList = new ArrayList<ArtistMember>();
		Statement stmt = null;
		ResultSet rs = null;
		ArtistMember am = null;
		
		try {
			String sql = "select * from t_artist_member am, t_artist_code ac "+
					"where am.ac_id = ac.ac_id ";
			//System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				am = new ArtistMember();
				am.setAc_name(rs.getString("ac_name_k"));
				am.setAm_idx(rs.getInt("am_idx"));
				am.setAc_id(rs.getString("ac_id"));
				am.setAm_name_k(rs.getString("am_name_k"));
				artistMemberList.add(am);
			}
			
		}catch(Exception e) {
			System.out.println("productProcDao getArtistMemberList 오류");
			e.printStackTrace();
		}
		finally { close(rs); close(stmt);}
		
		return artistMemberList;
	}

	//admin_pdtlist.jsp
	public int productProcIn(ProductInfo pi, int ai_idx) {
		int result = 0;
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			String sql = "select count(*) from t_product_info where ac_id = '"+pi.getAc_id()+"' ";
			//상품번호에 등록된 해당 아티스트의 총 상품 개수를 추가(삭제 안할것이므로)
			System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			
			String pi_id = pi.getPi_id()+"-"+pi.getPi_sdate()+"-";
			int pi_idx = rs.getInt(1);
			
			if(pi_idx <10) {
				pi_id += "0"+pi_idx;
			}else {
				pi_id += pi_idx;
			}
			
			sql = "insert into t_product_info (pi_name, pi_price, "+
				" pi_dc, pi_id, ai_idx, ac_id, ap_id, pi_img1, "+
				" pi_img2, pi_img3, pi_desc, pi_special, pi_sdate, pi_text, pi_isview) "+
				" values ( '"+pi.getPi_name()+"', '"+pi.getPi_price()+"', "+
				" '"+pi.getPi_dc()+"', '"+pi_id+"', '"+ai_idx+"', '"+pi.getAc_id()+"', "+
				" '"+pi.getAp_id()+"', '"+pi.getPi_img1()+"', '"+pi.getPi_img2()+"', "+
				" '"+pi.getPi_img3()+"', '"+pi.getPi_desc()+"', '"+pi.getPi_special()+"', "+
				" '"+pi.getPi_sdate()+"', '"+pi.getPi_text()+"', '"+pi.getPi_isview()+"' )";
			System.out.println(sql);
			result = stmt.executeUpdate(sql);
			
			if(result == 1) { //stocklist
				ArrayList<ProductStock> psList = pi.getStockList();
				
				int count = 0;
				for(int i = 0;i<psList.size();i++) {
					sql = "insert into t_product_stock ( pi_id, ps_am_name, ps_isview, ps_stock) values "+
							" ( '"+pi_id+"', '"+psList.get(i).getPs_am_name()+"', 'y', '"+psList.get(i).getPs_stock()+"' ) ";
					System.out.println(sql);
					count += stmt.executeUpdate(sql);
				}
				
				if(count==psList.size()) { result = 1; }
				else { result = 0; }
			}
			
			return result;
		}catch(Exception e) {
			System.out.println("productProc dao productProcIn 오류");
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(stmt);
		}
		
		return result;
	}

	public int productProcUp(ProductInfo pi, int ai_idx) {
		int result = 0;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "";
			stmt = conn.createStatement();
			
			sql = "update t_product_info set pi_name = '"+pi.getPi_name()+"', pi_price = "+
				" '"+pi.getPi_price()+"', pi_dc = '"+pi.getPi_dc()+"', pi_img1 = '"+pi.getPi_img1()+"', pi_img2 = "+
				" '"+pi.getPi_img2()+"', pi_img3 = '"+pi.getPi_img3()+"', pi_desc = '"+pi.getPi_desc()+
				"', pi_special = '"+pi.getPi_special()+"', pi_sdate = '"+pi.getPi_sdate()+"', pi_text = "+
				" '"+pi.getPi_text()+"', pi_isview = '"+pi.getPi_isview()+"', pi_ai_idx = '"+ai_idx+
				"' where pi_id = '"+pi.getPi_id()+"' ";
			System.out.println(sql);
			result = stmt.executeUpdate(sql);
			
			if(result == 1) { //stocklist
				ArrayList<ProductStock> psList = pi.getStockList();
				
				int count = 0;
				ProductStock ps = null;
				for(int i = 0;i<psList.size();i++) {
					ps = psList.get(i);
					int check = 0;
					sql = "select count(*) from t_product_stock where ps_am_name = '"+ps.getPs_am_name()+
							"' and pi_id = '"+pi.getPi_id()+"' ";
					System.out.println(sql);
					rs = stmt.executeQuery(sql);
					rs.next();
					check = rs.getInt(1);
					
					if(check ==1) { //이미 옵션이 있는것을 수정
						sql = "update t_product_stock set ps_isview = 'y', ps_stock = '"+ps.getPs_stock()+
							"' where pi_id = '"+pi.getPi_id()+"' and ps_am_name = '"
							+psList.get(i).getPs_am_name()+"' ";
						System.out.println(sql);
						count += stmt.executeUpdate(sql);
					}else { //옵션이 없음(신규)
						sql = "insert into t_product_stock (pi_id, ps_am_name, ps_stock, ps_isview) "+
							" values ('"+pi.getPi_id()+"', '"+ps.getPs_am_name()+"', '"+ps.getPs_stock()+"', 'y')";
						System.out.println(sql);
						count += stmt.executeUpdate(sql);
					}
				}
				
				if(count==psList.size()) { result = 1; }
				else { result = 0; }
			}
			
			return result;
		}catch(Exception e) {
			System.out.println("productProc dao productProcUp 오류");
			e.printStackTrace();
		}
		finally {
			if(rs!=null) { close(rs); }
			close(stmt);
		}
		
		return result;
	}

	public int productProcDel(String piid) {
		//미게시 상태로 변경(삭제 할 경우 상품아이디 생성에 문제 생길 수 있음)
		int result = 0;
		Statement stmt = null;
		try {
			String sql = "update t_product_info set pi_isview = 'n' where pi_id = '"+piid+"'";
			//System.out.println(sql);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		}catch(Exception e) {
			System.out.println("productProcDao productProcDel 오류");
			e.printStackTrace();
		}
		finally { close(stmt);}
		return result;
	}

	//optionList = artist_member + productStock 테이블 where piid
	//상품정보(상품코드) + 멤버테이블 + 재고테이블 join > 멤버별 옵션 현황 확인 가능
	//vo: ProductStock
	public ArrayList<ProductStock> getOptionList(String piid){
		ArrayList<ProductStock> optList = new ArrayList<ProductStock>();
		
		Statement stmt = null;
		ResultSet rs = null;
		ProductStock ps = null;
		
		try {
			//멤버이름 리스트 > 재고와 비교해서 
			
			
			String sql = "select pi.pi_id, am.*, ps.* " + 
					" from  t_product_info as pi join t_artist_member as am "+
					" on left(pi_id, 2) = am.ac_id " + 
					"left outer join t_product_stock as ps "+
					" on ps.pi_id = pi.pi_id and ps_am_name = am_name_k " + 
					" where pi.pi_id = '"+piid+"' ";
			//System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ps = new ProductStock();
				ps.setPi_id(piid);
				ps.setAc_id(rs.getString("ac_id"));
				ps.setPs_am_name(rs.getString("am_name_k")); 
				ps.setPs_stock(rs.getInt("ps_stock"));
				ps.setPs_isview(rs.getString("ps_isview"));
				ps.setPs_sale(rs.getInt("ps_sale"));
				ps.setPs_idx(rs.getInt("ps_idx"));
				optList.add(ps);
			}
			rs.close();
			sql = "select * from t_product_stock where pi_id = '"+piid+"' and ps_am_name = '' ";
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				ps = new ProductStock();
				ps.setPi_id(piid);
				ps.setAc_id(piid.substring(0, 2));
				ps.setPs_am_name("없음"); 
				ps.setPs_stock(rs.getInt("ps_stock"));
				ps.setPs_isview(rs.getString("ps_isview"));
				ps.setPs_sale(rs.getInt("ps_sale"));
				ps.setPs_idx(rs.getInt("ps_idx"));
				optList.add(0, ps);
			}
		}catch(Exception e) {
			System.out.println("productProcDao getOptionList 오류");
			e.printStackTrace();
		}
		finally { close(rs); close(stmt);}
		return optList;
	}
}

