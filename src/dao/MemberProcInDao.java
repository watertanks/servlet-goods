package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class MemberProcInDao {
	// 회원가입 처리를 위해 DB 연결
	private static MemberProcInDao memberProcInDao;
	private Connection conn;
	private MemberProcInDao() {}

	public static MemberProcInDao getInstance() {
		if (memberProcInDao == null)	memberProcInDao = new MemberProcInDao();
		
		return memberProcInDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public int memberProcIn(MemberInfo memberInfo, MemberAddr memberAddr) {
		// 회원가입 시키는 메소드
			PreparedStatement pstmt = null;
			
			int result = 0;

			try {
				String sql = "insert into t_member_info (mi_id, mi_pw, mi_name, mi_nickname, mi_gender, mi_birth, mi_phone, mi_email, mi_point) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, memberInfo.getMi_id());
				pstmt.setString(2, memberInfo.getMi_pw());
				pstmt.setString(3, memberInfo.getMi_name());
				pstmt.setString(4, memberInfo.getMi_nickname());
				pstmt.setString(5, memberInfo.getMi_gender());
				pstmt.setString(6, memberInfo.getMi_birth());
				pstmt.setString(7, memberInfo.getMi_phone());
				pstmt.setString(8, memberInfo.getMi_email());
				pstmt.setInt(9, 1000);

				result = pstmt.executeUpdate();
				if (result == 1) {
				// t_member_info 테이블에 insert 쿼리 정상작동시 실행되는 쿼리
					sql = "insert into t_member_addr (mi_id, ma_name, ma_rname, ma_phone, ma_zip, ma_addr1, ma_addr2) " + 
					" values (?, ?, ?, ?, ?, ?, ?)";
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, memberInfo.getMi_id());
					pstmt.setString(2, memberAddr.getMa_name());
					pstmt.setString(3, memberAddr.getMa_rname());
					pstmt.setString(4, memberInfo.getMi_phone());
					pstmt.setString(5, memberAddr.getMa_zip());
					pstmt.setString(6, memberAddr.getMa_addr1());
					pstmt.setString(7, memberAddr.getMa_addr2());
					result += pstmt.executeUpdate();
					
					if (result == 2) {
						// t_member_addr 테이블에 insert 쿼리 정상작동시 실행되는 쿼리
						sql = "insert into t_member_point (mi_id, " + 
						" mp_point, mp_desc) values (?, ?, '가입축하금')";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, memberInfo.getMi_id());
						pstmt.setInt(2, memberInfo.getMi_point());
						result += pstmt.executeUpdate();
					}
				}

			} catch(Exception e) {
				System.out.println("MemberProcInDao 클래스의 memberProcIn() 메소드에러");
				e.printStackTrace();
			} finally {
				close(pstmt);
			}

			return result;
		}
	
	public int hiddenJoin(MemberInfo memberInfo) {
		int result = 0;
		Statement stmt = null;
		String sql = "";
		String email = memberInfo.getMi_email();
		String name = memberInfo.getMi_nickname();
		String gender = memberInfo.getMi_gender();
		
		String birth = memberInfo.getMi_birth();
		String year = birth.substring(0, 4);
		String month = birth.substring(4, 6);
		String day = birth.substring(6, 8);
		
		birth = year + "-" + month + "-" + day;
		//System.out.println("합친거 : " + year + "-" + month + "-" + day);
		if(gender.equals("남")) {
			gender = "남";
		}else {
			gender = "여";
		}
		try {
			sql = "insert into t_member_info (mi_id, mi_pw, mi_name, mi_gender, mi_birth, mi_email, mi_nickname, mi_phone, mi_point, mi_kind) "+
				"values('"+email+"', '" + month + day + "/qwe!@#"+"', '"+name+"', '"+gender+"', '"+birth+"', '"+email+"', '"+name+"', '010-0000-0000', 1000, 'b') ";
			stmt = conn.createStatement();
			result += stmt.executeUpdate(sql);
			if(result == 1) {
				memberInfo.setMi_id(email);
				memberInfo.setMi_name((name));
				memberInfo.setMi_gender((gender));
				memberInfo.setMi_birth((birth));
				memberInfo.setMi_email((email));
				memberInfo.setMi_nickname((name));
				memberInfo.setMi_phone(("010-0000-0000"));
				
				sql = "insert into t_member_point (mi_id, mp_point, mp_desc) values ('"+email+"', 1000, '가입축하금')";
				result += stmt.executeUpdate(sql);
			}
			if(result == 2) {
				sql = "insert into t_member_addr (mi_id, ma_name, ma_rname, ma_phone, ma_zip, ma_addr1, ma_addr2) values('"+email+"', '"+name+"', '"+name+"', '"+memberInfo.getMi_phone()+"','00000', '주소를 설정해주세요', '상세주소를 설정해주세요.')";
				result += stmt.executeUpdate(sql);
			}
			
			
			
		}catch(Exception e) {
			System.out.println("MemberProcIn클래스의 hiddenJoin 메소드 에러발생");
			e.printStackTrace();
		}finally {
			close(stmt);
		}
		return result;
	}
}
