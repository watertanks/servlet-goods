package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class ProductViewSvc {
	
	public ProductInfo getProductInfo(String piid, String where) {
		ProductInfo pi = null;
		
		Connection conn = getConnection();
		ProductProcDao productProcDao = ProductProcDao.getInstance();
		productProcDao.setConnection(conn);

		pi = productProcDao.getProductInfo(piid, where);
		close(conn);
		
		return pi;
	}
	
	public ArrayList<ProductStock> getProductStock(String piid, String where) {
		ArrayList<ProductStock> psList = new ArrayList<ProductStock>();
		
		Connection conn = getConnection();
		ProductProcDao productProcDao = ProductProcDao.getInstance();
		productProcDao.setConnection(conn);
		
		psList = productProcDao.getStockList(piid, where);
		
		close(conn);
		return psList;
	}
	
	public void readUpdate(String piid) {
		Connection conn = getConnection();
		ProductProcDao productProcDao = ProductProcDao.getInstance();
		productProcDao.setConnection(conn);
		productProcDao.readUpdate(piid);
		close(conn);
	}
	
	public ArrayList<ProductStock> getOptionList(String piid) {
		ArrayList<ProductStock> psList = new ArrayList<ProductStock>();
		
		Connection conn = getConnection();
		ProductProcDao productProcDao = ProductProcDao.getInstance();
		productProcDao.setConnection(conn);
		
		psList = productProcDao.getOptionList(piid);
		close(conn);
		
		return psList;
	}
}
