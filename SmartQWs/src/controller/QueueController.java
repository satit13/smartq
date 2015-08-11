package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.LoginResponseBean;
import bean.PK_Resp_OrderPendingBean;
import bean.QueueBean;
import bean.CT_Resp_ResponseBean;
import bean.UserBean;
import bean.UserSearchBean;
import bean.UserSearchResponseBean;
import connect.QueueConnect;


public class QueueController {
	private final QueueConnect ds = QueueConnect.INSTANCE;
	private String Textstring;
	
	CT_Resp_ResponseBean response = new CT_Resp_ResponseBean();
	PK_Resp_OrderPendingBean order = new PK_Resp_OrderPendingBean();
	List<PK_Resp_OrderPendingBean> orderList = new ArrayList<PK_Resp_OrderPendingBean>();
	ExcecuteController excecute = new  ExcecuteController();
	
	public List<PK_Resp_OrderPendingBean> searchOrderPending(String dbName,String keyword){
		java.text.SimpleDateFormat dt= new java.text.SimpleDateFormat();
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		Date dateNow = new Date();
		
			try {
				
				
				// get connection
				Statement stmt = ds.getStatement(dbName);
			    
				if (keyword.equals("")){
					Textstring="select top 10 * from Queue where status = 0 and iscancel = 0 order by docno desc";
				}else{
					Textstring="select * from Queue where status = 0 and iscancel = 0 and code like'%"+keyword+"%' or name like '%"+keyword+"%' order by docno desc";
				}
				
			   	System.out.println(Textstring);
			    
			    ResultSet rs = stmt.executeQuery(Textstring);
			    
			    
			    int roworder=0;
			    
			    orderList.clear();
			   	while (rs.next()) {
			   		roworder++;
			   		
			   		order = new PK_Resp_OrderPendingBean();	

			   		order.setSuccess(true);
			   		order.setCarNumber(rs.getString("carLicense"));
			   		order.setCarBrand(rs.getString("carBrand"));
			   		order.setNumberOfItem(rs.getInt("numberOfItem"));
			   		order.setTimeCreate(rs.getString("createDate"));

	    		    orderList.add(order);
					
	    		    System.out.println(order.getCarNumber());
				}
			   	if (roworder==0) { //������辺
			   		orderList.clear();
			   		orderList.add(order);
			   	}  
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			    ds.close();
			}
			
			return orderList;
		}
	
}
