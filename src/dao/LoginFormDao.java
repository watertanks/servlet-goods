package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class LoginFormDao {
	private static LoginFormDao loginDao;
	private Connection conn;

	private LoginFormDao() {
	}

	public static LoginFormDao getInstance() {
		if (loginDao == null)
			loginDao = new LoginFormDao();
		return loginDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public MemberInfo getLoginInfo(String uid, String pwd) {
		Statement stmt = null;
		Statement stmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		MemberInfo loginInfo = null;
		MemberAddr memberAddr = null;

		try {
			String sql = "select * from t_member_info where mi_status != 'c' and mi_id = '" + uid + "' and mi_pw = '" + pwd + "' and mi_status != 'c' ";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				loginInfo = new MemberInfo();
				loginInfo.setMi_id(rs.getString("mi_id"));
				loginInfo.setMi_name(rs.getString("mi_name"));
				loginInfo.setMi_nickname(rs.getString("mi_nickname"));;
				loginInfo.setMi_gender(rs.getString("mi_gender"));
				loginInfo.setMi_birth(rs.getString("mi_birth"));
				loginInfo.setMi_phone(rs.getString("mi_phone"));
				loginInfo.setMi_email(rs.getString("mi_email"));
				loginInfo.setMi_point(rs.getInt("mi_point"));
				loginInfo.setMi_bmcount(rs.getInt("mi_bmcount"));
				loginInfo.setMi_couponcount(rs.getInt("mi_couponcount"));
				loginInfo.setMi_lastlogin(rs.getString("mi_lastlogin"));
				loginInfo.setMi_joindate(rs.getString("mi_joindate"));
				loginInfo.setMi_status(rs.getString("mi_status"));
			}
		}catch(Exception e) {
			System.out.println("LoginFormDao 클래스의 getLoginInfo() 메소드에러");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		return loginInfo;
	}
	public MemberInfo getHiddenLoginInfo(String email) {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		MemberInfo loginInfo = new MemberInfo();
		System.out.println(email);
		try {
			
			sql = "select * from t_member_info where mi_id = '"+email+"' and mi_status !='c' ";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				loginInfo.setMi_id(rs.getString("mi_id"));
				loginInfo.setMi_name(rs.getString("mi_name"));
				loginInfo.setMi_nickname(rs.getString("mi_nickname"));;
				loginInfo.setMi_gender(rs.getString("mi_gender"));
				loginInfo.setMi_birth(rs.getString("mi_birth"));
				loginInfo.setMi_phone(rs.getString("mi_phone"));
				loginInfo.setMi_email(rs.getString("mi_email"));
				loginInfo.setMi_point(rs.getInt("mi_point"));
				loginInfo.setMi_bmcount(rs.getInt("mi_bmcount"));
				loginInfo.setMi_couponcount(rs.getInt("mi_couponcount"));
				loginInfo.setMi_lastlogin(rs.getString("mi_lastlogin"));
				loginInfo.setMi_joindate(rs.getString("mi_joindate"));
				loginInfo.setMi_status(rs.getString("mi_status"));
				
			}
			
		}catch(Exception e) {
			System.out.println("LoginFormDao 클래스의 getHiddenLoginInfo()메소드 에러발생");
			e.printStackTrace();
		}finally {
			close(rs);	close(stmt);
		}
			return  loginInfo;
	}
}
