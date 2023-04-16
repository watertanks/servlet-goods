package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class ProductListSvc {
	public int getProductCount(String where) {
		int rcnt = 0;
		Connection conn = getConnection();
		ProductProcDao productProcDao = ProductProcDao.getInstance();
		productProcDao.setConnection(conn);
		
		rcnt = productProcDao.getProductCount(where);
		close(conn);
		return rcnt;
	}
	
	public ArrayList<ProductInfo> getProductList(int cpage, int psize, String where, String orderby){
		ArrayList<ProductInfo> productList = new ArrayList<ProductInfo>();
		Connection conn = getConnection();
		ProductProcDao productProcDao = ProductProcDao.getInstance();
		productProcDao.setConnection(conn);
		
		productList = productProcDao.getProductList(cpage, psize, where, orderby);
		close(conn);
		return productList;
	}
	
	public ArrayList<ArtistCode> getBigList(){
		ArrayList<ArtistCode> bigList = new ArrayList<ArtistCode>();
		Connection conn = getConnection();
		ProductProcDao productProcDao = ProductProcDao.getInstance();
		productProcDao.setConnection(conn);
		
		bigList = productProcDao.getBigList();
		close(conn);
		return bigList;
	}
	
	public ArrayList<ArtistProduct> getSmallList(){ //소분류 리스트 
		ArrayList<ArtistProduct> smallList = new ArrayList<ArtistProduct>();
		Connection conn = getConnection();
		ProductProcDao productProcDao = ProductProcDao.getInstance();
		productProcDao.setConnection(conn);
		
		smallList = productProcDao.getSmallList();
		close(conn);
		
		return smallList;
	}
	
	public ArrayList<ArtistMember> getArtistMemberList(){ //소분류 리스트 
		ArrayList<ArtistMember> artistMemberList = new ArrayList<ArtistMember>();
		Connection conn = getConnection();
		ProductProcDao productProcDao = ProductProcDao.getInstance();
		productProcDao.setConnection(conn);
		
		artistMemberList = productProcDao.getArtistMemberList();
		close(conn);
		
		return artistMemberList;
	}
}
