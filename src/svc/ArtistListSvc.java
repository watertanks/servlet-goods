package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class ArtistListSvc {
	public int getArtistCount() {
		int rcnt = 0;
		Connection conn = getConnection();
		ArtistListDao artistListDao = ArtistListDao.getInstance();
		artistListDao.setConnection(conn);
		
		rcnt = artistListDao.getArtistCount();
		close(conn);
		return rcnt;
	}
	public ArrayList<ArtistCode> getArtistList(int cpage, int psize,  String orderby){
		ArrayList<ArtistCode> artistList = new ArrayList<ArtistCode>();
		Connection conn = getConnection();
		ArtistListDao artistListDao = ArtistListDao.getInstance();
		artistListDao.setConnection(conn);

		artistList = artistListDao.getArtistList(cpage, psize, orderby);
		close(conn);

		return artistList;
	}
	
}