package dao;

import static db.JdbcUtil.*; //jdbc Ŭ������ ��� ������� �����Ӱ� ����� �� ���� 
import java.util.*;
import java.sql.*;
import vo.*;

public class CartProcDao {
	// �ٱ��� ���� �����۾� (���,���,����,����)���� ��� ó���ϴ� Ŭ����
	private static CartProcDao cartProcDao;
	private Connection conn;
	private CartProcDao() {}

	public static CartProcDao getInstance() {
		
		if (cartProcDao == null) cartProcDao = new CartProcDao(); 
			
		return cartProcDao;
	}public void setConnection(Connection conn) {
		
		this.conn = conn;
	}public int cartInsert(OrderCart oc) {
		// ����ڰ�  ������ ��ǰ�� ��ٱ��Ͽ� ��� �޼ҵ�
		int result = 0;	
		Statement stmt = null;
		try{
			String sql="update t_order_cart set oc_cnt = oc_cnt + "+oc.getOc_cnt()+" where mi_id = '"+oc.getMi_id()+"' and pi_id = '"+oc.getPi_id()+"' and ps_idx = '"+oc.getPs_idx()+"' ";
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			System.out.println(sql);
			// ���� �α����� ȸ���� ��ٱ��Ͽ� ������ ��ǰ �� �ɼ��� ������ ������ ������Ű�� ���� ����
			if(result == 0) {
			// ���� ��ǰ(�ɼ� ����)�� ������ ��ٱ��Ͽ� ���Ӱ� �߰�	
				sql=" insert into t_order_cart(mi_id, pi_id, ps_idx, oc_cnt) "
						+ " values( '" + oc.getMi_id() + "', '" + oc.getPi_id() + "', '" + oc.getPs_idx() + "', '" + oc.getOc_cnt() + "' )";
				System.out.println(sql);
				result = stmt.executeUpdate(sql);
			}
		}catch(Exception e) {
			System.out.println("CartProcDao Ŭ������ cartInsert() �޼ҵ� ����");
			e.printStackTrace();
		}finally {

			close(stmt);
		}
		return result;
	}public int cartUpdate(OrderCart oc) {
		// ������ ��ǰ�� �ɼ��̳� ������ ��ٱ��Ͽ��� �����ϴ� �޼ҵ�		
		int result = 0;	
		Statement stmt = null;
		ResultSet rs = null;
		try{
			stmt = conn.createStatement();
			String sql = " update t_order_cart set ";
			String where = " where mi_id = '"+oc.getMi_id()+"' ";
			if(oc.getOc_cnt() == 0){// �ɼ� ������ ���
				String sql2 = " select oc_idx, oc_cnt from t_order_cart " + where +
						 		" and ps_idx = " + oc.getPs_idx();
				// �����Ϸ�,�ɼǰ� ������ �ɼ��� ��ǰ�� ��ٱ��Ͽ� �̹� �����ϴ��� ���θ� �˻��� ����
				rs =stmt.executeQuery(sql2);
				if(rs.next()) {
				// �����Ϸ��� �ɼǰ� ������ �ɼ��� ��ǰ��  ��ٱ��Ͽ� ���� ���
				// ���� ��ǰ�� ������ �߰� �����ϴ� ��ǰ�� �߰����� ����	
					int idx = rs.getInt("oc_idx");
					// stmt�� �ٸ� ������ �����ϱ� ���� ����� ���� �̸� rs���� �޾Ƴ�
					sql += " ps_idx = " + oc.getPs_idx()+
							" ,oc_cnt=oc_cnt + "+rs.getInt("oc_cnt")+
							where+" and oc_idx = " + oc.getOc_idx();
					// �ɼ� ���� �� ������ �ɼ��� ���� ��ǰ�� ������ �� ��ǰ�� �߰��ϴ� ����
					result =stmt.executeUpdate(sql);
					
					sql = " delete from t_order_cart " + 
						where +" and oc_idx = "+ idx;
					// ������ �ɼ��� ���� ��ǰ�� ��ٱ��Ͽ��� �����ϴ� ����
				}else {
				// �����Ϸ��� �ɼǰ� ������ �ɼ��� ��ǰ��  ��ٱ��Ͽ� ���� ���
				//�ش� ��ǰ�� �ɼǸ� �����Ŵ	
					sql += " ps_idx = " + oc.getPs_idx()+
							where+" and oc_idx = " +oc.getOc_idx();		
				}
				close(rs);
			}else { // ���� ������ ���
				sql += "oc_cnt = "+oc.getOc_cnt() + where 
						+ " and oc_idx = " + oc.getOc_idx();
				// sql�� �������� ����ҋ� ��� �������� ������� ���ϰ� ��� �׳��� �� �ޱ򸮳�
				
			}
			result =stmt.executeUpdate(sql);
		}catch(Exception e) {
			System.out.println("CartProcDao Ŭ������ cartUpdate() �޼ҵ� ����");
			e.printStackTrace();
		}finally {
			
			close(stmt);
		}
		return result;
	}
	public int cartDelete(String where) {
		// ������ ��ǰ�� ��ٱ��Ͽ��� �����ϴ� �޼ҵ�	
		Statement stmt = null;
		int result=0;
		try {
			String sql="delete from t_order_cart "+where;
			stmt=conn.createStatement();
			result= stmt.executeUpdate(sql);
		}catch(Exception e) {
			System.out.println("CartProcDao Ŭ������ cartDelete() �޼ҵ� ����");
			e.printStackTrace();
		}finally {
			close(stmt);
		}
		return result;
	}
	public ArrayList<OrderCart> getCartList(String miid){
		// ��ٱ��Ͽ��� ������ �������� ArrayList�� �����ϴ� �޼ҵ�	
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<OrderCart> cartList = new ArrayList<OrderCart>();
		OrderCart oc = null;
		
		try {
			ProductProcDao ppd = ProductProcDao.getInstance();
			ppd.setConnection(conn);
			//Dao�� �ٸ����� ������ ����۶� getInstance�� �����ϰ� ���ؼ����� ������� �����  ������ �߻�����// Connection is null.
			
			
			String sql=" select a.*, b.pi_name, b.pi_img1, b.pi_price, b.pi_dc, c.ps_stock, c.ps_am_name "
						+" from t_order_cart a, t_product_info b,t_product_stock c "
						+" where a.pi_id = b.pi_id and a.pi_id = c.pi_id and  a.ps_idx = c.ps_idx and b.pi_isview ='y' and mi_id ='"+miid+"' ";
			
			
			stmt=conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				oc = new OrderCart();
				oc.setOc_idx(rs.getInt("oc_idx"));
				oc.setPi_id(rs.getString("pi_id"));
				oc.setPs_idx(rs.getInt("ps_idx"));
				oc.setOc_cnt(rs.getInt("oc_cnt"));
				oc.setPi_name(rs.getString("pi_name"));
				oc.setPi_img1(rs.getString("pi_img1"));
				oc.setPi_price(rs.getInt("pi_price"));
				oc.setPi_dc(rs.getInt("pi_dc"));
				oc.setPs_stock(rs.getInt("ps_stock"));
				oc.setPs_am_name(rs.getString("ps_am_name"));
				oc.setStockList(ppd.getStockList(oc.getPi_id(),"where ps_isview ='y' and"));
				// ���� ��ǰ�� �ɼ� �� ��� ���
				System.out.println("ps_stock"+rs.getInt("ps_stock"));
				cartList.add(oc);
			}
		}catch(Exception e) {
			System.out.println("CartProcDao Ŭ������ getCartList() �޼ҵ� ����");
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return cartList;
	}
}
