package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class AllArtistListSvc {
	public ArrayList<ArtistCode> getAllArtistList() {
		Connection conn = getConnection();
		ArtistListDao artistListDao = ArtistListDao.getInstance();
		artistListDao.setConnection(conn);

		ArrayList<ArtistCode> allArtistList = artistListDao.getAllArtistList();
		close(conn);
		
		
		
		return allArtistList;
	}
}
