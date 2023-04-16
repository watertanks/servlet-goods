package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;

public class DupNickSvc {
	public int chkDupNick(String unick) {
		int result = 0;
		Connection conn = getConnection();
		DupNickDao dupNickDao = DupNickDao.getInstance();
		dupNickDao.setConnection(conn);
		result = dupNickDao.chkDupNick(unick);
		close(conn);

		return result;
	}
}
