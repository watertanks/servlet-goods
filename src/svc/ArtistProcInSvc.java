package svc;

import static db.JdbcUtil.*; 
import vo.*;
import dao.*;
import java.sql.*;
import java.util.*;

public class ArtistProcInSvc {
	public int artistProcIn(ArtistCode ac, int ai_idx, ArrayList<ArtistMember>amList) {
		int result = 0;
		
		Connection conn = getConnection();
		ArtistProcDao artistProcDao = ArtistProcDao.getInstance();
		artistProcDao.setConnection(conn);
		
		result = artistProcDao.artistProcIn(ac, ai_idx , amList);
		if(result == 1) { commit(conn); }
		else { rollback(conn); }
		close(conn);
		
		return result;
}
}
