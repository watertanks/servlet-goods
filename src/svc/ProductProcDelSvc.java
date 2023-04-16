package svc;

import static db.JdbcUtil.*; 
import vo.*;
import dao.*;
import java.sql.*;
import java.util.*;

public class ProductProcDelSvc {
	public int productProcDel(String piid) {
		int result = 0;
		
		Connection conn = getConnection();
		ProductProcDao productProcDao = ProductProcDao.getInstance();
		productProcDao.setConnection(conn);
		
		result = productProcDao.productProcDel(piid);
		if(result == 1) { commit(conn); }
		else { rollback(conn); }
		close(conn);
		
		return result;
	}
}
