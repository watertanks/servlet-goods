package dao;

import static db.JdbcUtil.*; 
import vo.*;
import java.sql.*;
import java.util.*;

public class EventListDao 
{
	private static EventListDao eventListDao;
	private Connection conn;
	private EventListDao() { }

	public static EventListDao getInstance() {
		if(eventListDao==null) { eventListDao = new EventListDao(); }
		return eventListDao;
	}
	
	public void setConnection(Connection conn) {this.conn = conn;}
	
	public ArrayList<EventInfo> getEventList(int cpage, int psize, String orderby) 
	{
		Statement stmt = null;
		ResultSet rs = null;
		EventInfo el = null;
		
		ArrayList<EventInfo> eventList = new ArrayList<EventInfo>();
		try 
		{
			String sql = "select be_title, be_img1, be_sdate, be_edate, be_status from t_bbs_event " + orderby;
			//System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				el = new EventInfo();
				el.setBe_title(rs.getString("be_title"));
				el.setBe_img1(rs.getString("be_img1"));
				el.setBe_sdate(rs.getString("be_sdate"));
				el.setBe_edate(rs.getString("be_edate"));
				el.setBe_status(rs.getString("be_status"));
				eventList.add(el);
			}

		} catch(Exception e) {
			System.out.println("EventListDao Ŭ������ getEventList() �޼ҵ� ����");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return eventList;
		
	}
	public int getEventCount() 
	{
		Statement stmt = null;
		ResultSet rs = null;
		int rcnt = 0;
		try
		{
			String sql = "select count(*) from t_bbs_event ";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) 		rcnt = rs.getInt(1);
		}
		catch(Exception e)
		{
			System.out.println("EventListDao Ŭ������ getEventCount() �޼ҵ� ����");
			e.printStackTrace();
		}
		finally
		{
			close(rs); close(stmt);
		}
		return rcnt;
	}

}
