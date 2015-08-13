package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.LoginBean;
import bean.PK_Resp_GetDataQueue;
import bean.QueueBean;
import bean.PK_Reqs_EditOrderBean;
import bean.PK_Reqs_ManageItemBean;
import bean.CT_Reqs_NewDocNoBean;
import bean.PK_Resp_GetItemBarcodeBean;
import bean.PK_Resp_ManageItemBean;
import bean.PK_Resp_NewOrderHeaderBean;
import bean.PK_Resp_OrderPendingBean;
import bean.CT_Resp_ResponseBean;
import bean.SearchBean;
import bean.PK_Resp_DeleteOrderBean;
import connect.QueueConnect;

public class PickupController {
	private final QueueConnect ds = QueueConnect.INSTANCE;
	private String Textstring;
	private String vQuery;
	
	CT_Resp_ResponseBean response = new CT_Resp_ResponseBean();
	PK_Resp_OrderPendingBean order = new PK_Resp_OrderPendingBean();
	List<PK_Resp_OrderPendingBean> orderList = new ArrayList<PK_Resp_OrderPendingBean>();
	
	QueueBean que = new QueueBean();
	
	PK_Resp_NewOrderHeaderBean qIdOrder = new PK_Resp_NewOrderHeaderBean();
	GenNewDocnoController genNo = new GenNewDocnoController();
	
	ExcecuteController excecute = new  ExcecuteController();
	
	private java.text.SimpleDateFormat dtDoc= new java.text.SimpleDateFormat();
	private java.text.SimpleDateFormat dt= new java.text.SimpleDateFormat();

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	//dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
	

	private boolean isSuccess;
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	
	public List<PK_Resp_OrderPendingBean> searchOrderPending(String dbName,SearchBean keyword){

		
			try {
				
				Statement stmt = ds.getStatement("SmartQ");
			    
				if (keyword.getKeyword().equals("")){
					Textstring="select * from Queue where status = 0 and iscancel = 0 order by docno desc limit 10";
				}else{
					Textstring="select * from Queue where status = 0 and iscancel = 0 and docno like'%"+keyword.getKeyword()+"%' or carLisence like '%"+keyword.getKeyword()+"%' order by docno desc";
				}
				
			   	System.out.println(Textstring);
			    
			    ResultSet rs = stmt.executeQuery(Textstring);
			    
			    
			    int roworder=0;
			    
			    orderList.clear();
			   	while (rs.next()) {
			   		roworder++;
			   		
			   		order = new PK_Resp_OrderPendingBean();	

			   		order.setSuccess(true);
			   		order.setCarNumber(rs.getString("carLicence"));
			   		order.setCarBrand(rs.getString("carBrand"));
			   		order.setNumberOfItem(rs.getInt("numberOfItem"));
			   		order.setTimeCreate(rs.getString("createDate"));

	    		    orderList.add(order);
					
	    		    //System.out.println(order.getCarNumber());
				}
			   	if (roworder==0) { //§ÈπÀ“‰¡Ëæ∫
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
	
	
	public PK_Resp_NewOrderHeaderBean searchNewOrder(String dbName,CT_Reqs_NewDocNoBean reqOrder){
		String docDate;
		String vGenNewDocNo = "";
		boolean success;
		int qId;
		
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		
		CT_Reqs_NewDocNoBean reqNewDocNo = new CT_Reqs_NewDocNoBean();
		
		Date dateNow = new Date();

		vGenNewDocNo = genNo.genDocNo(0);
		qId = genNo.genqId();
		
		qIdOrder = new PK_Resp_NewOrderHeaderBean();
		
		//reqNewDocNo.setCarNumber("4712");
		
		if(reqNewDocNo.getCarNumber()!=null){
		
		vQuery ="insert into Queue(docNo,docDate,status,isCancel,customerId,warehouseId,customerCode,whCode,carLicence,carBrand,creatorCode,createDate,qId) values("+"'"+vGenNewDocNo+"','"+dateFormat.format(dateNow)+"',0,0,'99999',0,'99999','S1-B','"+reqNewDocNo.getCarNumber()+"','"+reqNewDocNo.getCarBrand()+"','admin',CURRENT_TIMESTAMP,"+qId+")";
		System.out.println(vQuery); 
		try {
				isSuccess= excecute.execute(dbName,vQuery);
				qIdOrder.setqId(qId);
				qIdOrder.setSuccess(true);
				qIdOrder.setErrorMessage("Successfull");
			} catch (Exception e) {
				isSuccess=false;
				qIdOrder.setqId(0);
				qIdOrder.setSuccess(false);
				qIdOrder.setErrorMessage(e.getMessage());
			}
		}else{
			
			System.out.println("Error"); 
			qIdOrder.setqId(0);
			qIdOrder.setSuccess(false);
			qIdOrder.setErrorMessage("No Have CarLicence");
		}
		return qIdOrder;
	}
	
	
	public boolean  editOrder(String dbName,PK_Reqs_EditOrderBean reqEdit){

		if (reqEdit.getCarNumber()!=null){
			vQuery ="update Queue set carLicence = '"+reqEdit.getCarNumber()+"',"+"carBrand= '"+reqEdit.getCarBrand()+"',editorCode='admin',editDate = CURRENT_TIMESTAMP where qid ="+reqEdit.getqId();
			System.out.println(vQuery); 
			try {
					isSuccess= excecute.execute(dbName,vQuery);
				} catch (Exception e) {
					isSuccess=false;
				}
			}else{
				
				isSuccess =false;

			}
		
		return isSuccess;
	}
		

	public boolean deleteOrder(String dbName,PK_Resp_DeleteOrderBean delOrder){
		if (delOrder.getqId() != 0){
			vQuery ="update Queue set isCancel = 1,cancelCode='admin',cancelDate = CURRENT_TIMESTAMP where qid ="+delOrder.getqId();
			System.out.println(vQuery); 
			try {
					isSuccess= excecute.execute(dbName,vQuery);
				} catch (Exception e) {
					isSuccess=false;
				}
			}else{
				
				isSuccess=false;

			}
		
		return isSuccess;
	}
	
	
	public PK_Resp_ManageItemBean insertItemPickup(String dbName,PK_Reqs_ManageItemBean reqItem){
		PK_Resp_ManageItemBean resItem = new PK_Resp_ManageItemBean();
		getDataFromData getData = new getDataFromData();
		PK_Resp_GetItemBarcodeBean getBarData = new PK_Resp_GetItemBarcodeBean();
		PK_Resp_GetDataQueue getQueue = new PK_Resp_GetDataQueue();
		LoginBean userCode = new LoginBean();
		int vCheckExistItem=0;
		
		
		if (reqItem.getqId()!=0){
			
			if (reqItem.getBarCode()!=null){
				
				if (reqItem.getQtyBefore()!=0){
					
					getBarData = getData.searchItemCode(reqItem.getBarCode());
					getQueue = getData.searchQueue(reqItem.getqId());
					userCode = getData.searchUserAccessToken(reqItem.getAccessToken());
					vCheckExistItem = getData.checkItemExistQueue(reqItem);
					
					if (vCheckExistItem==0){
						vQuery = "insert into QItem(qId,docNo,itemCode,unitCode,qty,pickQty,loadQty,checkoutQty,rate1,pickerCode,pickDate,isCancel,lineNumber) "+ "values("
							+reqItem.getqId()+",'"+getQueue.getDocNo()+"','"+ getBarData.getCode()+"','"+getBarData.getUnitCode()+"',"+ reqItem.getQtyBefore()+","+reqItem.getQtyBefore()+",0,0,"+getBarData.getRate1()+",'"+userCode.getEmployeeCode()+ "',CURRENT_TIMESTAMP,"+reqItem.getIsCancel()+",0)";
					}else{
						vQuery = "update QItem set qty ="+reqItem.getQtyBefore()+",pickQty="+reqItem.getQtyBefore()+" where qId = "+reqItem.getqId()+" and docNo ='"+getQueue.getDocNo()+"' and itemCode='"+getBarData.getCode()+"' and unitCode ='"+getBarData.getUnitCode()+"'";
					}
					System.out.println(vQuery);
					
					try{
						isSuccess= excecute.execute(dbName,vQuery);
						resItem.setSuccess(isSuccess());
						resItem.setError_message("Sucessfull");
					}catch(Exception e){
						isSuccess = false;
						resItem.setSuccess(isSuccess());
						resItem.setError_message(e.getMessage());
					}
					
				}else{
					//============================
					isSuccess = false;
					resItem.setSuccess(isSuccess());
					resItem.setError_message("QtyBefore not equql 0");
				}
			}else{
				//==============================
				isSuccess = false;
				resItem.setSuccess(isSuccess());
				resItem.setError_message("Barcode not null");
			}
		}else{
			//===================================
			isSuccess = false;
			resItem.setSuccess(isSuccess());
			resItem.setError_message("qID not equql 0");
		}
		
		return resItem;
		
	}

}
