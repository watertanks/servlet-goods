package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class MyPageDao {
	private static MyPageDao myPageDao;
	private Connection conn;
	private MyPageDao() {}

	public static MyPageDao getInstance() {
		if (myPageDao == null)	myPageDao = new MyPageDao();
		
		return myPageDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	
	public MemberAddr checkPw(String chkPw) {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = chkPw;
		MemberAddr memberAddr = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				memberAddr = new MemberAddr();
				memberAddr.setMa_zip(rs.getString("ma_zip"));
				memberAddr.setMa_addr1(rs.getString("ma_addr1"));
				memberAddr.setMa_addr2(rs.getString("ma_addr2"));
				memberAddr.setMa_idx(rs.getInt("ma_idx"));
				memberAddr.setMa_name(rs.getString("ma_name"));
				memberAddr.setMa_phone(rs.getString("ma_phone"));
				memberAddr.setMa_rname(rs.getString("ma_rname"));
			}

		}catch(Exception e) {
			System.out.println("MyPageDao클래스의 getCheckPw()메소드 에러발생");
			e.printStackTrace();
		}finally {
			close(rs); 	close(stmt);
		}
		return memberAddr;
	}
	
	
}
