package svc;


import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;


public class OrderListSvc {
	public ArrayList<OrderInfo> getOrderList(String miid,int cpage,int psize){
		ArrayList<OrderInfo> orderList = new ArrayList<OrderInfo>();
		Connection conn = getConnection();
		OrderProcDao orderProcDao = OrderProcDao.getInstance();
		orderProcDao.setConnection(conn);
		orderList = orderProcDao.getOrderList(miid,cpage,psize);
		close(conn);
		return orderList;
		
	}
	public int getOrderCount(String miid) {
	
		int rcnt = 0;
		Connection conn = getConnection();
		OrderProcDao orderProcDao = OrderProcDao.getInstance();
		orderProcDao.setConnection(conn);
		rcnt =orderProcDao.getOrderCount(miid);
		close(conn);
		return rcnt;
	}
	public int getAdminOrderCount() {
		int rcnt = 0;
		Connection conn = getConnection();
		OrderProcDao orderProcDao = OrderProcDao.getInstance();
		orderProcDao.setConnection(conn);
		rcnt =orderProcDao.getAdminOrderCount();
		close(conn);
		return rcnt;
	}
	public  ArrayList<OrderInfo> getAdminOrderList(String where, int cpage,int psize) {
		ArrayList<OrderInfo> orderList = new ArrayList<OrderInfo>();
		Connection conn = getConnection();
		OrderProcDao orderProcDao = OrderProcDao.getInstance();
		orderProcDao.setConnection(conn);
		orderList = orderProcDao.getAdminOrderList(where, cpage,psize);
		close(conn);
		return orderList;
	}

}
