package dao;

import static db.JdbcUtil.*; 
import vo.*;
import java.sql.*;
import java.util.*;

public class EventProcDao 
{
	private static EventProcDao eventProcDao;
	private Connection conn;
	private EventProcDao() { }

	public static EventProcDao getInstance() {
		if(eventProcDao==null) { eventProcDao = new EventProcDao(); }
		return eventProcDao;
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
			String sql = "select be_idx, be_title, be_img1, be_sdate, be_edate, be_status from t_bbs_event " + orderby;
			//System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				el = new EventInfo();
				el.setBe_idx(rs.getInt("be_idx"));
				el.setBe_title(rs.getString("be_title"));
				el.setBe_img1(rs.getString("be_img1"));
				el.setBe_sdate(rs.getString("be_sdate"));
				el.setBe_edate(rs.getString("be_edate"));
				el.setBe_status(rs.getString("be_status"));
				eventList.add(el);
			}

		} catch(Exception e) {
			System.out.println("EventProcDao 클래스의 getEventList() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return eventList;
		
	}
	public int getEventCount() 
	{
		// 이벤트 개수를 가지고 오는 함수
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
			System.out.println("EventProcDao 클래스의 getEventCount() 메소드 에러");
			e.printStackTrace();
		}
		finally
		{
			close(rs); close(stmt);
		}
		return rcnt;
	}
	
	public EventInfo getEventInfo(int beidx)
	{
		// 지정한 게시글의 정보들을 EventInfo형 인스턴스로 리턴하는 메소드
		Statement stmt = null;
		ResultSet rs = null;
		EventInfo el = null;
		ArrayList<EventInfo> eventList = new ArrayList<EventInfo>();
		try 
		{
			String sql = "select * from t_bbs_event where be_isview = 'y' and be_idx = " + beidx;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				el = new EventInfo();
				el.setBe_idx(beidx);				
				el.setBe_title(rs.getString("be_title"));
				el.setBe_img2(rs.getString("be_img2"));
				el.setBe_sdate(rs.getString("be_sdate"));
				el.setBe_edate(rs.getString("be_edate"));
				el.setBe_status(rs.getString("be_status"));
				eventList.add(el);
			}
		} 
		catch(Exception e) 
		{
			System.out.println("EventListDao 클래스의 getEventInfo() 메소드 오류");
			e.printStackTrace();
		} 
		finally 
		{
			close(rs); close(stmt);
		}
		
		return el;
	}

}
