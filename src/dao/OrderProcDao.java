package dao;

import static db.JdbcUtil.*; //jdbc Ŭ������ ��� ������� �����Ӱ� ����� �� ���� 
import java.util.*;
import java.sql.*;
import java.time.LocalDate;

import vo.*;

public class OrderProcDao {
	
	// �ٱ��� ���� �����۾� (���,���,����,����)���� ��� ó���ϴ� Ŭ����
	private static OrderProcDao orderProcDao;
	private Connection conn;
	private OrderProcDao() {}

	public static OrderProcDao getInstance() {
		
		if (orderProcDao == null) orderProcDao = new OrderProcDao(); 
			
		return orderProcDao;
	}public void setConnection(Connection conn) {
		
		this.conn = conn;
	}
	public ArrayList<OrderInfo> getOrderList(String miid,int cpage,int psize){
		// order_list
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<OrderInfo>  orderList=  new ArrayList<OrderInfo>();
		OrderInfo ol = null;
		try {
			
			String sql="select a.oi_id, b.od_idx, a.oi_date , c.pi_name, b.od_am_name, b.od_img, b.od_cnt, b.od_price, a.oi_pay"
					   +" from t_order_info a, t_order_detail b ,t_product_info c "
					   +" where a.mi_id ='"+miid+"' and  a.oi_id = b.oi_id and b.pi_id =c.pi_id "
					   		+ " order by a.oi_date desc"+ " limit " + ((cpage-1)*psize) + ", " + psize;
				System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ol = new OrderInfo();
				ol.setOi_id(rs.getString("oi_id"));
				ol.setOd_idx(rs.getInt("od_idx"));
				ol.setOi_date(rs.getString("oi_date"));
				ol.setPi_name(rs.getString("pi_name"));
				ol.setOd_am_name(rs.getString("od_am_name"));
				ol.setOd_img(rs.getString("od_img"));
				ol.setOd_cnt(rs.getInt("od_cnt"));
				ol.setOd_price(rs.getInt("od_price"));
				ol.setOi_pay(rs.getInt("oi_pay"));
				
				orderList.add(ol);
			}
		
		}catch(Exception e) {
			System.out.println("CartProcDao Ŭ������ getOrderList() �޼ҵ� ����");
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		
		}
		return orderList;
		
	}
	public int getOrderCount(String miid) {
		// order_list
		Statement stmt = null;
		ResultSet rs = null;
		int rcnt = 0;
		try {
			String sql="select count(*) from t_order_info where mi_id='"+miid+"' "+
					"";
			System.out.println(sql);
			stmt = conn.createStatement();
		
			rs = stmt.executeQuery(sql);
			if(rs.next()) rcnt = rs.getInt(1);
		}catch(Exception e) {
			System.out.println("ProductProcDao Ŭ������ getProductCount() �޼ҵ� ����");
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		
		return rcnt;
	}
	public ArrayList<OrderCart> getBuyList(String kind, String sql){
		// �ֹ� ������ ������ ������ ��ǰ����� ArrayList�� �����ϴ� �޼ҵ�
			Statement stmt = null;
			ResultSet rs = null;
			ArrayList<OrderCart> pdtList = new ArrayList<OrderCart>();
			OrderCart oc = null;
			try {
				System.out.println(sql);
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					oc = new OrderCart();
					if (kind.equals("c")) oc.setOc_idx(rs.getInt("oc_idx"));
					// ��ٱ��ϸ� ���� ������ ��쿡�� ��ٱ��� �ε����� �߰���
					oc.setPi_id(rs.getString("pi_id"));
					oc.setPi_img1(rs.getString("pi_img1"));
					oc.setPi_name(rs.getString("pi_name"));
					oc.setPi_price(rs.getInt("pi_price"));
					oc.setPi_dc(rs.getInt("pi_dc"));
					oc.setOc_cnt(rs.getInt("cnt"));
					oc.setPs_am_name(rs.getString("ps_am_name"));
					pdtList.add(oc);
				}
			}catch(Exception e) {
				System.out.println("OrderProcDao Ŭ������ getBuyList() �޼ҵ� ����");
				e.printStackTrace();
			}finally {
				close(rs);
				close(stmt);
			}
			return pdtList;
		}
		public ArrayList<MemberAddr> getAddrList(String miid){
		// �ֹ� ������ ������ ���� �α����� ȸ���� �ּ� ����� ArrayList�� �����ϴ� �޼ҵ�
			Statement stmt = null;
			ResultSet rs = null;
			MemberAddr ma = null;
			ArrayList<MemberAddr> addrList = new 	ArrayList<MemberAddr>();
			
			try {
				String sql ="select * from t_member_addr where mi_id='"+miid+"' "; 
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					ma = new MemberAddr();
					ma.setMa_idx(rs.getInt("ma_idx"));
					ma.setMi_id(rs.getString("mi_id"));
					ma.setMa_name(rs.getString("ma_name"));
					ma.setMa_phone(rs.getString("ma_phone"));
					ma.setMa_zip(rs.getString("ma_zip"));
					ma.setMa_basic(rs.getString("ma_basic"));
					ma.setMa_date(rs.getString("ma_date"));
					ma.setMa_addr1(rs.getString("ma_addr1"));
					ma.setMa_addr2(rs.getString("ma_addr2"));
					ma.setMa_rname(rs.getString("ma_rname"));
					addrList.add(ma);
				}
			}catch(Exception e) {
				System.out.println("OrderProcDao Ŭ������ getAddrList() �޼ҵ� ����");
				e.printStackTrace();
			}finally {
				close(rs);
				close(stmt);
			}
			
			return addrList;
		}

		public String getOrderId() {
			// ���ο� �ֹ���ȣ(yymmdd(6�ڸ�) +���� ���� 2�ڸ� +�Ϸù�ȣ 4�ڸ�(1001���� ����))�� �����Ͽ� �����ϴ� �޼ҵ�	
				Statement stmt =null;
				ResultSet rs = null;
				String oi_id =null;
				
				
				try {
					stmt = conn.createStatement();
					LocalDate today = LocalDate.now(); // yyyy-mm-dd
					String td = (today+"").substring(2).replace("-","");//yymmdd 
					
					String alpha ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
					Random rnd = new Random();
		 			String rn = alpha.charAt(rnd.nextInt(26))+"";
					rn += alpha.charAt(rnd.nextInt(26))+"";// ���� ������ 2�ڸ�
					
					String sql =" select right(oi_id,4) seq "+// seq ���� ������ ������ �ַλ��
							" from t_order_info where left(oi_id,6) = '"+td+"' "+
							"order by oi_date desc limit 0,1";
					// ���� �� �Էµ� �ֹ���ȣ�� �� ���� �ֱٰ��� �������� ����
					rs = stmt.executeQuery(sql);
					if(rs.next()) {  // ���� ������ �ֹ���ȣ�� ������
						int num = Integer.parseInt(rs.getString("seq"))+1 ;				oi_id = td+rn+num;
					}else {				// ���� ù������ ���
						oi_id = td+rn+"1001";
					}
					
				}catch(Exception e) {
					System.out.println("OrderProcDao Ŭ������ getOrderId() �޼ҵ� ����");
					e.printStackTrace();
				}finally {
					close(rs);
					close(stmt);
				}
				return oi_id;
			}
		public String orderInsert(String kind, OrderInfo oi, String temp) {
			Statement stmt = null;
			ResultSet rs =null;
			String oi_id = getOrderId();
			String result =oi_id + ","; // oi_id�� �������� ������ �ֹ������� �����ֱ�����
			int rcount=0, target=0;
			int pnt = oi.getOi_pay()*2/100;
			try {
				stmt = conn.createStatement();
			// t_order_info ���̺� ����� insert��
				String sql = "insert into t_order_info (" + 
						"oi_id, mi_id, oi_name, oi_phone, oi_zip, oi_addr1, " + 
						"oi_addr2, oi_payment, oi_pay, oi_status, oi_spoint, oi_upoint) values ('" + 
						oi_id			+ "', '" + oi.getMi_id()	+ "', '" + 
						oi.getOi_name()	+ "', '" + oi.getOi_phone() + "', '" + 
						oi.getOi_zip()	+ "', '" + oi.getOi_addr1()	+ "', '" + 
						oi.getOi_addr2()+ "', '" + oi.getOi_payment()+"', '" + 
						oi.getOi_pay()	+ "', '" + oi.getOi_status() + "', '" +
						pnt +"', '"+ oi.getOi_upoint() + "') ";
				 		target++; rcount = stmt.executeUpdate(sql);
						System.out.println(sql);
						if(kind.equals("c")) { // ��ٱ��ϸ� ���� ������ ���
						// ��ٱ��Ͽ��� t_order_detail ���̺� insert�� ��ǰ ������ ������	
							sql="select  a.pi_id, a.ps_idx, a.oc_cnt , b.pi_name, b.pi_img1, c.ps_am_name,"+
								" if(b.pi_dc>0, (100-b.pi_dc)/100 * b.pi_price, b.pi_price) price "
								+" from t_order_cart a, t_product_info b, t_product_stock c "
								+" where a.pi_id = b.pi_id and a.ps_idx = c.ps_idx "
								+" and a.mi_id ='"+oi.getMi_id()+"' and ( ";
								String delWhere = " where mi_id = '"+oi.getMi_id()+"' and (";
								String[] arr = temp.split(",");
								
								// ��ٱ��� ���̺��� ���ؽ� ��ȣ��� �迭 ����
								for(int i= 1; i<arr.length;i++) {
									if( i == 1 ) {
										sql +="a.oc_idx =" + arr[i];
										delWhere += " oc_idx= " + arr[i];
									}else{
										sql += " or a.oc_idx = "	+ arr[i];	
										delWhere += " or oc_idx= " + arr[i];
									}
								}
								sql +=")";
								
								delWhere +=")";
								System.out.println(sql);
								rs=stmt.executeQuery(sql);
								
								if(rs.next()) { // ��ٱ��Ͽ� ������ ��ǰ������ ������
									do {
										Statement stmt2 = conn.createStatement();
										// t_order_detail ���̺� ����� insert ��
										sql="insert t_order_detail(oi_id, pi_id, ps_idx, od_cnt, od_price, od_name, od_img, od_am_name)values('"
											+ oi_id +"', '" 
											+rs.getString("pi_id")+"', '"
											+rs.getInt("ps_idx")+"', '"
											+rs.getInt("oc_cnt")+"', '"
											+rs.getInt("price")+"', '"
											+rs.getString("pi_name")+"', '"
											+rs.getString("pi_img1")+"', '"
											+rs.getString("ps_am_name")+"' )";
										 
											target++; rcount += stmt2.executeUpdate(sql);
											
											// t_product_info ���̺��� �Ǹż� ���� update��
											sql = "update t_product_info set pi_sale = pi_sale + "+rs.getInt("oc_cnt")+
													" where pi_id = '"+rs.getString("pi_id")+"' ";
											System.out.println(sql);
											target++; rcount += stmt2.executeUpdate(sql);
											
											// t_product_stock ���̺��� �Ǹ� �� ��� ���� update��
											sql = "update t_product_stock set ps_stock = ps_stock - "+rs.getInt("oc_cnt")+" , ps_sale = ps_sale + "
												  + rs.getInt("oc_cnt") + " where ps_idx ="+ rs.getInt("ps_idx");
											
											target++; rcount += stmt2.executeUpdate(sql);		
									}while(rs.next());
									// t_order_cart ���̺��� ���� �� ���� delect ��
									sql = "delete from t_order_cart " + delWhere;
									stmt.executeUpdate(sql);
									// ����� ������ �߻��ص� ���ſʹ� ��������Ƿ� rcount�� �������� ����
								}else {     // ��ٱ��Ͽ� ������ ��ǰ������ ������
									return result + "1,4";
								}
						}else { //직접구매일 경우
							String[] arr = temp.split(",");
							sql="select  a.pi_id, b.ps_idx, "+arr[0]+" cnt , a.pi_name, a.pi_img1, b.ps_am_name,"+
									" if(a.pi_dc>0, (100-a.pi_dc)/100 * a.pi_price, a.pi_price) price "
									+" from  t_product_info a, t_product_stock b "
									+" where a.pi_id = b.pi_id and a.pi_id='"+arr[1]+"' ";
							
									
						
									System.out.println(sql);
									
									rs=stmt.executeQuery(sql);
									
									if(rs.next()) { // ��ٱ��Ͽ� ������ ��ǰ������ ������
										do {
											Statement stmt2 = conn.createStatement();
											// t_order_detail ���̺� ����� insert ��
											sql="insert t_order_detail(oi_id, pi_id, ps_idx, od_cnt, od_price, od_name, od_img, od_am_name)values('"
												+ oi_id +"', '" 
												+rs.getString("pi_id")+"', '"
												+rs.getInt("ps_idx")+"', '"
												+rs.getInt("cnt")+"', '"
												+rs.getInt("price")+"', '"
												+rs.getString("pi_name")+"', '"
												+rs.getString("pi_img1")+"', '"
												+rs.getString("ps_am_name")+"' )";
											 
												target++; rcount += stmt2.executeUpdate(sql);
												
												// t_product_info ���̺��� �Ǹż� ���� update��
												sql = "update t_product_info set pi_sale = pi_sale + "+rs.getInt("cnt")+
														" where pi_id = '"+rs.getString("pi_id")+"' ";
												System.out.println(sql);
												target++; rcount += stmt2.executeUpdate(sql);
												
												// t_product_stock ���̺��� �Ǹ� �� ��� ���� update��
												sql = "update t_product_stock set ps_stock = ps_stock - "+rs.getInt("cnt")+" , ps_sale = ps_sale + "
													  + rs.getInt("cnt") + " where ps_idx ="+ rs.getInt("ps_idx");
												
												target++; rcount += stmt2.executeUpdate(sql);		
										}while(rs.next());
										// t_order_cart ���̺��� ���� �� ���� delect ��
										
										// ����� ������ �߻��ص� ���ſʹ� ��������Ƿ� rcount�� �������� ����
									}else {     // ��ٱ��Ͽ� ������ ��ǰ������ ������
										return result + "1,4";
									}
						}
						if(oi.getOi_upoint()>0) { // 포인트를 사용할 경우
							sql="call sp_member_point_insert('"+oi.getMi_id()+"','u',"+oi.getOi_upoint()+",'상품구매','"+oi_id+"')";
							target++; rcount += stmt.executeUpdate(sql);
						}else { // 포인트를 적립할 경우
							pnt = oi.getOi_pay()*2/100; // ������ ����Ʈ
							sql="call sp_member_point_insert('"+oi.getMi_id()+"','s',"+pnt+",'상품구매','"+oi_id+"')";
							target++; rcount += stmt.executeUpdate(sql);
						}
			}catch(Exception e) {
				System.out.println("OrderProcDao 클래스 orderInsert() 메소드 오류");
				e.printStackTrace();
			}finally {
				close(rs);
				close(stmt);
			}
			return result + rcount + "," + target;
			// �ֺй�ȣ�� ���� ����� ���ڵ��, ����Ǿ���� ���ڵ� ���� ����
		}
		public OrderInfo getOrderEndInfo(String miid, String oiid) {
			// �޾ƿ� ȸ�� ���̵�� �ֹ���ȣ�� �ش��ϴ� ��������  OrderInfo�� �ν��Ͻ��� ��� �����ϴ� �޼ҵ�	
				Statement stmt = null;
				ResultSet rs = null;
				OrderInfo oi = null;
			
				try {
					String sql="select a.oi_name, a.oi_phone, a.oi_zip, a.oi_addr1, a.oi_addr2, a.oi_payment, a.oi_pay, b.od_img, b.od_name, b.od_am_name, b.od_cnt,b.od_price, b.pi_id , c.pi_isview " 
							+ " from t_order_info a, t_order_detail b, t_product_info c "
							+ " where a.oi_id = b.oi_id and b.pi_id=c.pi_id and a.mi_id = '"+miid+"' and a.oi_id = '"+oiid+"' order by c.pi_id, b.od_am_name ";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					
					if(rs.next()) {
						oi= new OrderInfo();
						oi.setOi_id(oiid);
						oi.setOi_name(rs.getString("oi_name"));
						oi.setOi_phone(rs.getString("oi_phone"));
						oi.setOi_zip(rs.getString("oi_zip"));
						oi.setOi_addr1(rs.getString("oi_addr1"));
						oi.setOi_addr2(rs.getString("oi_addr2"));
						oi.setOi_payment(rs.getString("oi_payment"));
						oi.setOi_pay(rs.getInt("oi_pay"));
					}
				}catch(Exception e) {
					System.out.println("OrderProcDao Ŭ������ getOrderInfo() �޼ҵ� ����");
					e.printStackTrace();
				}finally {
					close(rs);
					close(stmt);
				}
				return oi;
			}
		public OrderInfo getOrderInfo(String miid, String oiid) {
			Statement stmt = null;
			ResultSet rs = null;
			OrderInfo oi = null;
			ArrayList<OrderDetail> detailList = new ArrayList<OrderDetail>(); // �ֹ� ��ǰ���� ����
			try {
				String sql="select a.oi_name, a.oi_phone, a.oi_zip, a.oi_addr1, a.oi_addr2, a.oi_payment, a.oi_pay, b.od_img, b.od_name, b.od_am_name, "
						+ "b.od_cnt,b.od_price, b.pi_id , c.pi_isview, a.oi_upoint, a.oi_spoint " 
						+ " from t_order_info a, t_order_detail b, t_product_info c "
						+ " where a.oi_id = b.oi_id and b.pi_id=c.pi_id and a.mi_id = '"+miid+"' and a.oi_id = '"+oiid+"'order by c.pi_id, b.od_am_name ";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				if(rs.next()) {
					oi= new OrderInfo();
					oi.setOi_id(oiid);
					oi.setOi_name(rs.getString("oi_name"));
					oi.setOi_phone(rs.getString("oi_phone"));
					oi.setOi_zip(rs.getString("oi_zip"));
					oi.setOi_addr1(rs.getString("oi_addr1"));
					oi.setOi_addr2(rs.getString("oi_addr2"));
					oi.setOi_payment(rs.getString("oi_payment"));
					oi.setOi_pay(rs.getInt("oi_pay"));
					oi.setOi_spoint(rs.getInt("oi_spoint"));
					oi.setOi_upoint(rs.getInt("oi_upoint"));
					do {
						OrderDetail od = new OrderDetail();
						od.setOd_img(rs.getString("od_img"));
						od.setOd_name(rs.getString("od_name"));
						od.setOd_am_name(rs.getString("od_am_name"));
						od.setOd_cnt(rs.getInt("od_cnt"));
						od.setOd_price(rs.getInt("od_price"));
						od.setPi_id(rs.getString("pi_id"));
						od.setPi_isview(rs.getString("pi_isview"));
						detailList.add(od);
					}while(rs.next());
						oi.setDetailList(detailList);	
				}
			}catch(Exception e) {
				System.out.println("OrderProcDao Ŭ������ getOrderInfo() �޼ҵ� ����");
				e.printStackTrace();
			}finally {
				close(rs);
				close(stmt);
			}
			return oi;
		}
		public ArrayList<OrderInfo> getAdminOrderList(String where, int cpage,int psize){
			
			Statement stmt = null;
			ResultSet rs = null;
			ArrayList<OrderInfo>  orderList=  new ArrayList<OrderInfo>();
			OrderInfo ol = null;
			try {
				
				String sql="select a.oi_id, b.od_idx, a.oi_date , c.pi_name, b.od_am_name, b.od_img, b.od_cnt, b.od_price "
						   +" from t_order_info a, t_order_detail b ,t_product_info c "
						   +" where  a.oi_id = b.oi_id and b.pi_id =c.pi_id "+where
						   		+ " order by a.oi_date desc"+ " limit " + ((cpage-1)*psize) + ", " + psize;
					System.out.println(sql);
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					ol = new OrderInfo();
					ol.setOi_id(rs.getString("oi_id"));
					ol.setOd_idx(rs.getInt("od_idx"));
					ol.setOi_date(rs.getString("oi_date"));
					ol.setPi_name(rs.getString("pi_name"));
					ol.setOd_am_name(rs.getString("od_am_name"));
					ol.setOd_img(rs.getString("od_img"));
					ol.setOd_cnt(rs.getInt("od_cnt"));
					ol.setOd_price(rs.getInt("od_price"));
					
					orderList.add(ol);
				}
			
			}catch(Exception e) {
				System.out.println("CartProcDao Ŭ������ getOrderList() �޼ҵ� ����");
				e.printStackTrace();
			}finally {
				close(rs);
				close(stmt);
			
			}
			return orderList;
		}
		public int getAdminOrderCount() {
			// order_list
			Statement stmt = null;
			ResultSet rs = null;
			int rcnt = 0;
			try {
				String sql="select count(*) from t_order_info";
				System.out.println(sql);
				stmt = conn.createStatement();
			
				rs = stmt.executeQuery(sql);
				if(rs.next()) rcnt = rs.getInt(1);
			}catch(Exception e) {
				System.out.println("ProductProcDao Ŭ������ getProductCount() �޼ҵ� ����");
				e.printStackTrace();
			}finally {
				close(rs);
				close(stmt);
			}
			
			return rcnt;
		}
	
}
