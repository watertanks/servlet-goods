package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;

public class ChkDupSvc {
	public int chkDupId(String uid) {
		int result = 0;
		Connection conn = getConnection();
		ChkDupDao dupIdDao = ChkDupDao.getInstance();
		dupIdDao.setConnection(conn);
		result = dupIdDao.chkDupId(uid);
		close(conn);

		return result;
	}
	public int chkDupNick(String unick) {
		int result = 0;
		Connection conn = getConnection();
		ChkDupDao chkDupDao = ChkDupDao.getInstance();
		chkDupDao.setConnection(conn);
		result = chkDupDao.chkDupNick(unick);
		close(conn);

		return result;
	}
	public int chkDupUemail(String uemail) {
		int result = 0;
		Connection conn = getConnection();
		ChkDupDao chkDupDao = ChkDupDao.getInstance();
		chkDupDao.setConnection(conn);
		result = chkDupDao.chkDupUemail(uemail);
		close(conn);

		return result;
	}
	public int chkDupApiLogin(String email) {
		int result = 0;
		
		Connection conn = getConnection();
		ChkDupDao chkDupDao = ChkDupDao.getInstance();
		chkDupDao.setConnection(conn);
		result = chkDupDao.chkDupApiLogin(email);
		close(conn);

		return result;
	}
	public int chkDupOldPwd(String uid, String oldPwd) {
		int result = 0;
		
		Connection conn = getConnection();
		ChkDupDao chkDupDao = ChkDupDao.getInstance();
		chkDupDao.setConnection(conn);
		result = chkDupDao.chkDupOldPwd(uid, oldPwd );
		close(conn);

		return result;
	}
	public int chkDupNewPwd(String uid, String newPwd) {
		int result = 0;
		Connection conn = getConnection();
		ChkDupDao chkDupDao = ChkDupDao.getInstance();
		chkDupDao.setConnection(conn);
		result = chkDupDao.chkDupNewPwd(uid,newPwd);
		close(conn);

		return result;
		
		
		
	}
}
