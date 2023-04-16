package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class MemberDetailSvc {
	public MemberInfo getMemberDetail(String miid) {
		Connection conn = getConnection();
		MemberDetailDao memberDetailDao = MemberDetailDao.getInstance();
		memberDetailDao.setConnection(conn);

		MemberInfo memberDetail = memberDetailDao.getMemberDetail(miid);
		close(conn);
		return memberDetail;
	}
}
