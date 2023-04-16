package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class ArtistListDao {
	//��Ƽ��Ʈ ���� ���� ó��
	private static ArtistListDao artistListDao;
	private Connection conn;
	private ArtistListDao() { }

	public static ArtistListDao getInstance() {
		if(artistListDao==null) { artistListDao = new ArtistListDao(); }
		return artistListDao;
	}
	
	public void setConnection(Connection conn) {this.conn = conn;}
	
	//artist_list.jsp�� �޼ҵ�
	public ArrayList<ArtistCode> getArtistList(int cpage, int psize, String orderby){
		Statement stmt = null;
		ResultSet rs = null;
		ArtistCode ac = null;

		ArrayList<ArtistCode> artistList =  new ArrayList<ArtistCode>();
		try {
			String sql = "select ac_name_k, ac_id , ac_name_e , ac_img, ac_date from t_artist_code " + orderby + " limit "+((cpage-1)*psize)+","+psize;
			System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
				ac = new ArtistCode();
				ac.setAc_id(rs.getString("ac_id"));
				ac.setAc_name_k(rs.getString("ac_name_k"));
				ac.setAc_name_e(rs.getString("ac_name_e"));
				ac.setAc_img(rs.getString("ac_img"));
				ac.setAc_date(rs.getString("ac_date"));
				artistList.add(ac);
			}

		}catch(Exception e) {
			System.out.println("ArtistDao list ����");
			e.printStackTrace();
		}
		finally { close(rs); close(stmt);}
		
		
		return artistList;
	}

	public int getArtistCount() {
		// ��Ƽ��Ʈ ������ ������ ���� �Լ�
				Statement stmt = null;
				ResultSet rs = null;
				int rcnt = 0;
				try {
					String sql = "select count(*) from t_artist_code ";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if(rs.next()) 		rcnt = rs.getInt(1);
				}
				catch(Exception e) {
					System.out.println("ArtistListDao Ŭ������ getArtistCount() �޼ҵ� ����");
					e.printStackTrace();
				}
				finally
				{
					close(rs); close(stmt);
				}
				return rcnt;
			}
	
		
}
