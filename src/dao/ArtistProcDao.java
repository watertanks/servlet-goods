package dao;

import static db.JdbcUtil.*; 
import vo.*;
import java.sql.*;
import java.util.*;

public class ArtistProcDao {
	//아티스트 관련 쿼리 처리
	
	private static ArtistProcDao artistProcDao;
	private Connection conn;
	private ArtistProcDao() {}
	
	public static ArtistProcDao getInstance() {
		if(artistProcDao==null) { artistProcDao = new ArtistProcDao(); }
		return artistProcDao;
	}
	
	public void setConnection(Connection conn) {this.conn = conn;}
	
	//admin_artistlist.jsp
	public int artistProcIn(ArtistCode ac, int ai_idx , ArrayList<ArtistMember>amList) {
		
		int result = 0;
		Statement stmt = null;
		
		try {
		String sql = "insert into t_artist_code ( ac_id, ac_name_k, ac_name_e, ac_img)" +
		" values ( '"+ ac.getAc_id() + "', '"+ ac.getAc_name_k() + "', '"+ac.getAc_name_e() +"', "+
		" '"+ac.getAc_img() +"')";
		System.out.println(sql);
		
		stmt = conn.createStatement();
		result = stmt.executeUpdate(sql);
		
		if(result == 1) { //memberlist
			
			int count = 0;
			ArtistMember am = null;
			
			for(int i = 0;i<amList.size();i++) {
				am = amList.get(i);
				sql = "insert into t_artist_member ( ac_id, am_name_k, am_name_e)" +
				" values ( '"+ac.getAc_id()+"', '"+am.getAm_name_k() + "', '"+am.getAm_name_e()+"' ) ";
				System.out.println(sql);
				count += stmt.executeUpdate(sql);
			}
			
			if(count==amList.size()) { result = 1; }
			else { result = 0; }
		}
		return result;
		}catch(Exception e) {
			System.out.println("artistProc dao artistProcIn 오류");
			e.printStackTrace();
		}
		finally {
			close(stmt);
		}
		
		return result;
	}
}

