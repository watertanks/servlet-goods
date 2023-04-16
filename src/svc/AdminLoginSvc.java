package svc;

import static db.JdbcUtil.*; 
import vo.*;
import dao.*;
import java.sql.*;
import java.util.*;

public class AdminLoginSvc {
	public AdminInfo getAdminInfo(String uid, String pwd) {
		Connection conn = getConnection();
		AdminLoginDao adminLoginDao = AdminLoginDao.getInstance();
		adminLoginDao.setConnection(conn);
		
		AdminInfo loginInfo = adminLoginDao.getAdminInfo(uid, pwd);
		close(conn);
		
		return loginInfo;
	}
	public int getNewMember(){
		Connection conn = getConnection();
		AdminLoginDao adminLoginDao = AdminLoginDao.getInstance();
		adminLoginDao.setConnection(conn);
		
		int cnt = adminLoginDao.getNewMember();
		close(conn);
		
		return cnt;
	}
	public int getAllMember() {
		Connection conn = getConnection();
		AdminLoginDao adminLoginDao = AdminLoginDao.getInstance();
		adminLoginDao.setConnection(conn);
		
		int cnt = adminLoginDao.getAllMember();
		close(conn);
		
		return cnt;
	}
	
}
