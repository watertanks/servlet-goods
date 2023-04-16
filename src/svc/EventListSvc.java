package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class EventListSvc 
{
	public ArrayList<EventInfo> getEventList(int cpage, int psize, String orderby) {
		ArrayList<EventInfo> eventList = new ArrayList<EventInfo>();
		Connection conn = getConnection();
		EventProcDao eventListDao = EventProcDao.getInstance();
		eventListDao.setConnection(conn);

		eventList = eventListDao.getEventList(cpage, psize, orderby);
		close(conn);

		return eventList;
	}
	public int getEventCount()
	{
		int rcnt = 0;
		Connection conn = getConnection();
		EventProcDao eventListDao = EventProcDao.getInstance();
		eventListDao.setConnection(conn);
		
		rcnt = eventListDao.getEventCount();
		close(conn);
		
		return rcnt;
	}
}
