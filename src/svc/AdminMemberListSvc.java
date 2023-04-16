package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class AdminMemberListSvc {
	public int getMemberCount(String where) {
		int rcnt = 0;
		Connection conn = getConnection();
		MemberListDao memberListDao = MemberListDao.getInstance();
		memberListDao.setConnection(conn);
		
		rcnt = memberListDao.getMemberCount(where);
		close(conn);
		return rcnt;
	}
	public ArrayList<MemberInfo> getMemberList(int cpage, int psize, String where, String orderby ) {
		Connection conn = getConnection();
		MemberListDao memberListDao = MemberListDao.getInstance();
		memberListDao.setConnection(conn);

		ArrayList<MemberInfo> memberList = memberListDao.getMemberList(cpage, psize, where, orderby);
		close(conn);
		
		
		
		return memberList;
	}
}
