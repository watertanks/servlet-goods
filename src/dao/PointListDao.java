package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class PointListDao {
	private static PointListDao pointListDao;
	private Connection conn;
	private PointListDao() {}

	public static PointListDao getInstance() {
	
		if (pointListDao == null) pointListDao = new PointListDao(); 
		
		return pointListDao;
	}
	public void setConnection(Connection conn) {
		
		this.conn = conn;
	}
	public int getPointListCount(String miid) {
	
			Statement stmt = null;
			ResultSet rs = null;
			int rcnt  = 0;
		
			try {
				String sql = "select count(*) from t_member_point where mi_id = '"+miid+"' ";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if(rs.next()) rcnt = rs.getInt(1);
				
			}catch(Exception e) {
				System.out.println("PointListDao Ŭ������ getPointListCount() �޼ҵ� ����");
				e.printStackTrace();
			}finally {
				close(rs);
				close(stmt);
			}
			return rcnt;
		}
		public int getPointListSum(String miid) {

			Statement stmt = null;
			ResultSet rs = null;
			int ptotal  = 0;
			try {
				String sql = "select sum(mp_point) from t_member_point where if(mp_su='s',mp_point=mp_point, mp_point=mp_point*-1) and mi_id = '"+miid+"' ";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if(rs.next()) ptotal = rs.getInt(1);
				
			}catch(Exception e) {
				System.out.println("PointListDao Ŭ������ getPointListSum() �޼ҵ� ����");
				e.printStackTrace();
			}finally {
				close(rs);
				close(stmt);
			}
			return ptotal;
		}
		public  ArrayList<Point> getPointList(String miid, int cpage,int psize){
		
			Statement stmt = null;
			ResultSet rs = null;
			ArrayList<Point> pointList = new ArrayList<Point>();
		
			Point mp = null;
	
			try { // 수정
				String sql="select mp_idx, mp_point, mp_su, mp_desc, mp_detail, mi_id ,"+
						"if(curdate()=date(mp_date), right(mp_date, 8), replace(mid(mp_date,3,8), '-',' .')) wdate"+
						" from t_member_point where mi_id = '"+miid +"' order by mp_idx desc  limit " + ((cpage-1)*psize) + ", " + psize;
		
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					mp = new Point();
					mp.setMp_idx(rs.getInt("mp_idx"));
					mp.setMp_point(rs.getInt("mp_point"));
					mp.setMp_su(rs.getString("mp_su"));
					mp.setMp_desc(rs.getString("mp_desc"));
					mp.setMp_detail(rs.getString("mp_detail"));
					mp.setMp_date(rs.getString("wdate"));
					mp.setMi_id(rs.getString("mi_id"));
					pointList.add(mp);
				}
			}catch(Exception e) {
				System.out.println("PointListDao Ŭ������ getPointList() �޼ҵ� ����");
				e.printStackTrace();
			}finally {
				close(rs);
				close(stmt);
			}
			return pointList;
		}
}
