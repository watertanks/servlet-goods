package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class MemberDetailDao {
	private static MemberDetailDao memberDetailDao;
	private Connection conn;

	private MemberDetailDao() {
	}
	public static MemberDetailDao getInstance() {
		if (memberDetailDao == null)
			memberDetailDao = new MemberDetailDao();
		return memberDetailDao;
	}
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public MemberInfo getMemberDetail(String miid){
		MemberInfo mi = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from t_member_info where mi_id = '"+miid+"' ";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			//System.out.println(sql);
			if(rs.next()) {
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
			}
			
		}catch(Exception e) {
			System.out.println("MemberDetailDao클래스의 getMemberDetail메소드 에러발생");
			e.printStackTrace();
		}finally {
			close(rs); close(stmt);
		}
		//System.out.println(mi);
		return mi;
		
	}
	
}
