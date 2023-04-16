package svc;

import static db.JdbcUtil.*; 
import vo.*;
import dao.*;
import java.sql.*;
import java.util.*;

public class ProductProcInSvc {
	public int productProcIn(ProductInfo pi, int ai_idx) {
		int result = 0;
		
		Connection conn = getConnection();
		ProductProcDao productProcDao = ProductProcDao.getInstance();
		productProcDao.setConnection(conn);
		
		result = productProcDao.productProcIn(pi, ai_idx);
		if(result == 1) { commit(conn); }
		else { rollback(conn); }
		close(conn);
		
		return result;
	}
}
