package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class MemberProcUpSvc {
	public int ChangePw(String uid, String oldPwd, String newPwd) {
		int result = 0;
		Connection conn = getConnection();
		MemberProcUp memberProcUp = MemberProcUp.getInstance();
		memberProcUp.setConnection(conn);
		
		result = memberProcUp.changePw(uid, oldPwd, newPwd);
		if (result == 1)	commit(conn);
		else				rollback(conn);
		
		close(conn);

		return result;
	}
	public int memberProcUp(MemberInfo memberInfo, MemberAddr memberAddr) {
		int result = 0;
		Connection conn = getConnection();
		MemberProcUp memberProcUp = MemberProcUp.getInstance();
		memberProcUp.setConnection(conn);
		
		result = memberProcUp.memberProcUp(memberInfo, memberAddr);
		
		if (result == 2)	commit(conn);
		else				rollback(conn);
		
		close(conn);
		return result;
	}
	
}
