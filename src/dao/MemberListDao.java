package dao;

import static db.JdbcUtil.*; 
import vo.*;
import java.sql.*;
import java.util.*;

public class MemberListDao {
	private static MemberListDao memberListDao;
	private Connection conn;
	private MemberListDao() { }
	public static MemberListDao getInstance() {
		if(memberListDao==null) { memberListDao = new MemberListDao(); }
		return memberListDao;
	}
	public void setConnection(Connection conn) {this.conn = conn;}
	
	public int getMemberCount(String where) {
		int cnt = 0;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			sql = "select count(*) from t_member_info ";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			cnt = rs.getInt(1);
			
		}catch(Exception e) {
			System.out.println("MemberListDao클래스의 getMemberCount()메소드 에러발생");
			e.printStackTrace();
		}finally {
			close(rs);	close(stmt);
		}
		return cnt;
	}

	
	public ArrayList<MemberInfo> getMemberList(int cpage, int psize, String where, String orderby){
		MemberInfo mi = null;
		ArrayList<MemberInfo> memberList = new ArrayList<MemberInfo>();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			sql = "select * from t_member_info " + where + orderby +"limit "+((cpage-1)*psize)+","+psize;
			System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				mi = new MemberInfo();
				mi.setMi_id(rs.getString("mi_id"));
				mi.setMi_pw(rs.getString("mi_pw"));
				mi.setMi_name(rs.getString("mi_name"));
				mi.setMi_nickname(rs.getString("mi_nickname"));
				mi.setMi_gender(rs.getString("mi_gender"));
				mi.setMi_birth(rs.getString("mi_birth"));
				mi.setMi_phone(rs.getString("mi_phone"));
				mi.setMi_email(rs.getString("mi_email"));
				mi.setMi_point(rs.getInt("mi_point"));
				mi.setMi_bmcount(rs.getInt("mi_bmcount"));
				mi.setMi_couponcount(rs.getInt("mi_couponcount"));
				mi.setMi_lastlogin(rs.getString("mi_lastlogin"));
				mi.setMi_joindate(rs.getString("mi_joindate"));
				mi.setMi_status(rs.getString("mi_status"));
				mi.setMi_kind(rs.getString("mi_kind"));
				
				memberList.add(mi);
			}

		}catch(Exception e) {
			System.out.println("MemberListDao클래스의 getMemberList()메소드 에러발생");
			e.printStackTrace();
		}finally {
			close(rs);	close(stmt);
		}
		return memberList;
	}
	
	
}
