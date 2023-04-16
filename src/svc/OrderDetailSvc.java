package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class OrderDetailSvc {
	public OrderInfo getOrderInfo(String miid, String oiid) {
		OrderInfo orderInfo = null;
		Connection conn = getConnection();
		OrderProcDao orderProcDao = OrderProcDao.getInstance();
		orderProcDao.setConnection(conn);
		orderInfo = orderProcDao.getOrderInfo(miid, oiid);
		
		return orderInfo;
	}
}
