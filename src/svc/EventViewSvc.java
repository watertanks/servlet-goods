package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class EventViewSvc 
{
	/*
	public int readUpdate(int beidx)
	{
		int result = 0;
		Connection conn = getConnection();
		EventListDao eventListDao = EventListDao.getInstance();
		eventListDao.setConnection(conn);
		
		result = eventListDao.readUpdate(beidx);
		if(result == 1)		commit(conn);
		else				rollback(conn);
		
		close(conn);
		
		return result;
	}
	*/
	
	public EventInfo getEventInfo(int beidx)
	{
		EventInfo el = null;
		Connection conn = getConnection();
		EventProcDao eventProcDao = EventProcDao.getInstance();
		eventProcDao.setConnection(conn);
		
		el = eventProcDao.getEventInfo(beidx);
		
		close(conn);
		
		return el;
	}
}
