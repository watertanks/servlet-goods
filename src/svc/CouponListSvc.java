package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;



public class CouponListSvc {
	public int getCouponListCount(String miid ) {
		int rcnt = 0;
		Connection conn = getConnection();
		CouponListDao couponListDao = CouponListDao.getInstance();
		couponListDao.setConnection(conn);
		
		rcnt = couponListDao.getCouponListCount(miid);
		close(conn);
		return rcnt;
		
	}
	public ArrayList<Coupon> getCouponList(String miid, int cpage,int psize){
		ArrayList<Coupon> couponList = new ArrayList<Coupon>(); 
		Connection conn = getConnection();
		CouponListDao couponListDao = CouponListDao.getInstance();
		couponListDao.setConnection(conn);
		couponList = couponListDao.getCouponList(miid,cpage,psize);
		close(conn);
		return couponList;
	}
}
