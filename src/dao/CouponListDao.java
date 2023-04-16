package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class CouponListDao {
	private static CouponListDao couponListDao;
	private Connection conn;
	private CouponListDao() {}

	public static CouponListDao getInstance() {
	
		if (couponListDao == null) couponListDao = new CouponListDao(); 
		
		return couponListDao;
	}
	public void setConnection(Connection conn) {
		
		this.conn = conn;
	}public int getCouponListCount(String miid) {
		
		Statement stmt = null;
		ResultSet rs = null;
		int rcnt  = 0;
	
		try {
			String sql = "select count(*) from t_member_coupon where mi_id = '"+miid+"' ";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) rcnt = rs.getInt(1);
			
		}catch(Exception e) {
			System.out.println("CouponListDao Ŭ������ getCouponListCount() �޼ҵ� ����");
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return rcnt;
	}
	public  ArrayList<Coupon> getCouponList(String miid, int cpage,int psize){
		
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Coupon> couponList = new ArrayList<Coupon>();
	
		Coupon mc = null;

		try { // 수정
			String sql="select mc_idx, mc_name, mc_kind, mc_status, if(curdate()=date(mc_sdate), right(mc_sdate, 8), replace(mid(mc_sdate,3,8), '-',' .')) sdate, "+
					"if(curdate()=date(mc_edate), right(mc_edate, 8), replace(mid(mc_edate,3,8), '-',' .')) edate"+
				
					" from t_member_coupon where mi_id = '"+miid +"' order by mc_idx desc  limit " + ((cpage-1)*psize) + ", " + psize;
	
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				mc = new Coupon();
				mc.setMc_idx(rs.getInt("mc_idx"));
				mc.setMc_name(rs.getString("mc_name"));
				mc.setMc_kind(rs.getString("mc_kind"));
				mc.setMc_status(rs.getString("mc_status"));
				mc.setMc_sdate(rs.getString("sdate"));
				mc.setMc_edate(rs.getString("edate"));
				couponList.add(mc);
			}
		}catch(Exception e) {
			System.out.println("CouponListDao Ŭ������ getCouponList() �޼ҵ� ����");
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return couponList;
	}
}
