package dao;

import static db.JdbcUtil.*; 
import vo.*;
import java.sql.*;
import java.util.*;

public class AdminLoginDao {
	private static AdminLoginDao adminLoginDao;
	private Connection conn;
	private AdminLoginDao() { }
	
	public static AdminLoginDao getInstance() {
		if(adminLoginDao==null) { adminLoginDao = new AdminLoginDao(); }
		return adminLoginDao;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public AdminInfo getAdminInfo(String uid, String pwd) {
		Statement stmt = null;
		ResultSet rs = null;
		AdminInfo adminInfo = null;
		
		try {
			String sql = "select * from t_admin_info where ai_id = '"+uid+
				"' and ai_pw = '"+pwd+"' and ai_use = 'y' ";
			//System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				adminInfo = new AdminInfo();
				adminInfo.setAi_id(uid);
				adminInfo.setAi_pw(pwd);
				adminInfo.setAi_idx(rs.getInt("ai_idx"));
			}
		}
		catch(Exception e) {
			System.out.println("AdminLoginDao getAdminInfo 오류");
			e.printStackTrace();
		}
		finally {
			close(rs);	close(stmt);
		}
		
		return adminInfo;
	}
	public int getNewMember(){
		int cnt = 0;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {// 최근 한달간 가입한 신규회원 수
			sql ="select count(DATE_FORMAT(mi_joindate,'%m/%d/%Y')) from t_member_info where mi_joindate between NOW() - interval 30 day and now() order by mi_joindate desc; ";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			cnt = rs.getInt(1);

		}catch(Exception e) {
			System.out.println("AdminLoginDao클래스의 getNewMember()메소드 에러 발생");
			e.printStackTrace();
		}finally {
			close(rs);	close(stmt);
		}
		return cnt;
	}
	public int getAllMember() {
		int cnt = 0;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {// 총 회원수
			sql ="select count(*) from t_member_info ";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			cnt = rs.getInt(1);

		}catch(Exception e) {
			System.out.println("AdminLoginDao클래스의 getAllMember()메소드 에러 발생");
			e.printStackTrace();
		}finally {
			close(rs);	close(stmt);
		}
		return cnt;
	}
}
