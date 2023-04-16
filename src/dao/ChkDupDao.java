package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class ChkDupDao {
// 아이디 중복체크 관련 쿼리작업
	private static ChkDupDao dupIdDao;
	private Connection conn;
	private ChkDupDao() {}

	public static ChkDupDao getInstance() {
		if (dupIdDao == null)	dupIdDao = new ChkDupDao();
		
		return dupIdDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public int chkDupId(String uid) {
	// 회원가입할때 사용자가 사용할 아이디의 중복 여부를 리턴 하는 메소드
		Statement stmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			stmt = conn.createStatement();
			String sql = "select count(*) from t_member_info " + 
				" where mi_id = '" + uid + "' ";
			rs = stmt.executeQuery(sql);
			rs.next();	// count() 함수를 사용 했으므로 데이터가 없을순 없음
			result = rs.getInt(1);

		} catch(Exception e) {
			System.out.println("DupIdDao 클래스의 chkDupId() 메소드 에러");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return result;
	}
	
	public int chkDupNick(String unick) {
		// 회원가입할때 사용자가 사용할 아이디의 중복 여부를 리턴 하는 메소드
			Statement stmt = null;
			ResultSet rs = null;
			int result = 0;

			try {
				stmt = conn.createStatement();
				String sql = "select count(*) from t_member_info " + 
					" where mi_nickname = '" + unick + "' ";
				rs = stmt.executeQuery(sql);
				rs.next();	// count() 함수를 사용 했으므로 데이터가 없을순 없음
				result = rs.getInt(1);

			} catch(Exception e) {
				System.out.println("DupIdDao 클래스의 chkDupNick() 메소드 에러");
				e.printStackTrace();
			} finally {
				close(rs);	close(stmt);
			}
			
			return result;
		}
	
	public int chkDupUemail(String uemail) {
		// 회원가입할때 사용자가 사용할 아이디의 중복 여부를 리턴 하는 메소드
			Statement stmt = null;
			ResultSet rs = null;
			int result = 0;

			try {
				stmt = conn.createStatement();
				String sql = "select count(*) from t_member_info " + 
					" where mi_email = '" + uemail + "' ";
				rs = stmt.executeQuery(sql);
				rs.next();	// count() 함수를 사용 했으므로 데이터가 없을순 없음
				result = rs.getInt(1);

			} catch(Exception e) {
				System.out.println("DupIdDao 클래스의 chkDupUemail() 메소드 에러");
				e.printStackTrace();
			} finally {
				close(rs);	close(stmt);
			}
			return result;
	}
	
	public int chkDupApiLogin(String email) {
		Statement stmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			stmt = conn.createStatement();
			String sql = "select count(*) from t_member_info " + 
				" where mi_id = '" + email + "' ";
			rs = stmt.executeQuery(sql);
			rs.next();	// count() 함수를 사용 했으므로 데이터가 없을순 없음
			result = rs.getInt(1);

		} catch(Exception e) {
			System.out.println("DupIdDao 클래스의 chkDupApiLogin() 메소드 에러");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		return result;
	}
	public int chkDupOldPwd(String uid, String oldPwd) {
		int result = 0;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try{
			sql = "select count(*) from t_member_info where mi_id = '"+uid+"' and mi_pw = '"+oldPwd+"' ";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			result = rs.getInt(1);
			
		}catch(Exception e) {
			System.out.println("DupIdDao 클래스의 chkDupOldPwd() 메소드 에러");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		return result;
	}
	public int chkDupNewPwd(String uid, String newPwd) {
		int result = 0;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			sql = "select count(*) from t_member_info where mi_id = '"+uid+"' and mi_pw != '"+newPwd+"' ";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			result = rs.getInt(1);
			
		}catch(Exception e) {
			System.out.println("DupIdDao 클래스의 chkDupNewPwd() 메소드 에러");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		return result;
	}
	
}
