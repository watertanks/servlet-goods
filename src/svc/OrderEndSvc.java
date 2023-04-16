package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class OrderEndSvc {
	public OrderInfo getOrderEndInfo(String miid, String oiid) {
		OrderInfo orderInfo = null;
		Connection conn = getConnection();
		OrderProcDao orderProcDao = OrderProcDao.getInstance();
		orderProcDao.setConnection(conn);
		
		orderInfo = orderProcDao.getOrderEndInfo(miid, oiid);
		close(conn);
		return orderInfo;
	}
}
