package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;


public class PointListSvc {
	public int getPointListCount(String miid ) {
		int rcnt = 0;
		Connection conn = getConnection();
		PointListDao pointListDao = PointListDao.getInstance();
		pointListDao.setConnection(conn);
		
		rcnt = pointListDao.getPointListCount(miid);
		close(conn);
		return rcnt;
		
	}
	public int getPointListSum(String miid) {
		int ptotal =0;
		Connection conn = getConnection();
		PointListDao pointListDao = PointListDao.getInstance();
		pointListDao.setConnection(conn);
		
		ptotal = pointListDao.getPointListSum(miid);
		close(conn);
		return ptotal;
	}
	public ArrayList<Point> getPointList(String miid, int cpage,int psize){
		ArrayList<Point> pointList = new ArrayList<Point>(); 
		Connection conn = getConnection();
		PointListDao pointListDao = PointListDao.getInstance();
		pointListDao.setConnection(conn);
		pointList = pointListDao.getPointList(miid,cpage,psize);
		close(conn);
		return pointList;
	}
}
