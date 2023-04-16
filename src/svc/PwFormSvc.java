package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class PwFormSvc {
	public MemberAddr checkPw(String chkPw) {
		Connection conn = getConnection();
		MyPageDao  myPageDao  = MyPageDao .getInstance();
		myPageDao.setConnection(conn);

		MemberAddr memberAddr = myPageDao.checkPw(chkPw);
		if (memberAddr != null)		commit(conn);
		else						rollback(conn);
		// 사용 쿼리가 insert, update, delete일때 트랙잭션 완료
		close(conn);
		
		return memberAddr;
	}
}
