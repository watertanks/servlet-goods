package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class MemberProcUp {
	private static MemberProcUp changeInfoDao;
	private Connection conn;
	private MemberProcUp() {}

	public static MemberProcUp getInstance() {
		if (changeInfoDao == null)	changeInfoDao = new MemberProcUp();
		
		return changeInfoDao;
	}
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public int changePw(String uid, String oldPwd, String newPwd) {
		int result = 0;
		
		Statement stmt = null;
		String sql = "";
		try {
			sql="update t_member_info set mi_pw = '"+newPwd+"' where mi_id = '"+uid+"' and mi_pw = '"+oldPwd+"' ";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			result += 1;

		}catch(Exception e) {
			System.out.println("MemberProcUp클래스의 changePw()메소드 에러");
			e.printStackTrace();
		}finally {
			close(stmt);
		}
		return result;
	}
	public int memberProcUp(MemberInfo memberInfo, MemberAddr memberAddr) {
		int result = 0;
		Statement stmt = null;
		String sql = "";
		String nickname = memberInfo.getMi_nickname();
		String phone = memberInfo.getMi_phone();
		String zip = memberAddr.getMa_zip();
		String addr1 = memberAddr.getMa_addr1();
		String addr2 = memberAddr.getMa_addr2();
		
		try {
			sql = "update t_member_info set mi_nickname = '"+nickname+"', mi_phone = '"+phone+"' ";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			result += 1;
			
			sql = "update t_member_addr set ma_zip = '"+zip+"', ma_addr1 = '"+addr1+"', ma_addr2 = '"+addr2+"' ";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			result += 1;
			
		}catch(Exception e) {
			System.out.println("MemberProcUp클래스의 memberProcUp()메소드 에러");
			e.printStackTrace();
		}finally {
			close(stmt);
		}
		return result;
	}
}
