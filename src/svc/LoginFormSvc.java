package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class LoginFormSvc {
		public MemberInfo getLoginInfo(String uid, String pwd) {
			Connection conn = getConnection();
			LoginFormDao loginFormDao = LoginFormDao.getInstance();
			loginFormDao.setConnection(conn);

			MemberInfo loginInfo = loginFormDao.getLoginInfo(uid, pwd);
			close(conn);
			
			return loginInfo;
		}
		public MemberInfo getHiddenLoginInfo(String email) {
			
			Connection conn = getConnection();
			LoginFormDao loginDao = LoginFormDao.getInstance();
			loginDao.setConnection(conn);

			MemberInfo loginInfo = loginDao.getHiddenLoginInfo(email);
			close(conn);
			
			return loginInfo;
		}
	}
