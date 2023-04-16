package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class OrderProcInSvc {
	public String orderInsert(String kind, OrderInfo oi, String temp) {
		String result = null;
		Connection conn = getConnection();
		OrderProcDao orderProcDao = OrderProcDao.getInstance();
		orderProcDao.setConnection(conn);
		
		result = orderProcDao.orderInsert(kind, oi, temp);
		//result : �ֹ���ȣ,����ȷ��ڵ��,����Ǿ��ҷ��ڵ��
		String[] arr = result.split(",");//commit
		if(arr[1].equals(arr[2])) 	commit(conn);
		// ���� ����� ���ڵ�(rcount) ������ ����Ǿ��� ���ڵ�(targer) ������ ������
		else						rollback(conn);
		close(conn);
		return result;
	}
}
