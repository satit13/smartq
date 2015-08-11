package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import bean.LoginBean;
import bean.PK_Reqs_ManageItemBean;
import bean.PK_Resp_GetDataQueue;
import bean.PK_Resp_GetItemBarcodeBean;

import connect.QueueConnect;

public class getDataFromData {
	String vQuery ;
	String vQuerySub ;
	String vDocNo;

	String vGenNewDocNo;
	int getID;
	int getInspectID;
	
	private final QueueConnect ds = QueueConnect.INSTANCE;

	private java.text.SimpleDateFormat dtDoc= new java.text.SimpleDateFormat();
	private java.text.SimpleDateFormat dt= new java.text.SimpleDateFormat();

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	
	PK_Resp_GetItemBarcodeBean resItem = new PK_Resp_GetItemBarcodeBean();
	PK_Resp_GetDataQueue resQue = new PK_Resp_GetDataQueue();
	
	LoginBean userCode = new LoginBean();
	
	
	public PK_Resp_GetItemBarcodeBean searchItemCode(String barcode){

		try{
			Statement st = ds.getStatement("SmartQ");
			
			vQuery = "select code,unitcode,rate1 from itemBarcode where barcode = '"+barcode+"'";

			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				resItem.setCode(rs.getString("code"));
				resItem.setRate1(rs.getInt("rate1"));
				resItem.setUnitCode(rs.getString("unitcode"));

			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return resItem;
	}
	
	public PK_Resp_GetDataQueue searchQueue(int qId){

		try{
			Statement st = ds.getStatement("SmartQ");
			
			vQuery = "select docno from Queue where qId = "+qId;

			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				resQue.setDocNo(rs.getString("docno"));

			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return resQue;
	}
	
	public LoginBean searchUserAccessToken(String accessToken){

		try{
			Statement st = ds.getStatement("SmartConfig");
			
		    vQuery="select u.code,u.role from  User as u inner join UserAccess as ua on u.code=ua.userCode and ua.userUUID='"+accessToken+"'"
		    		+" where u.role=1 order by ua.dateTimeStamp DESC LIMIT 1" ;
		    
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				userCode.setEmployeeCode(rs.getString("code"));

			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return userCode;
	}
	
	public int checkItemExistQueue(PK_Reqs_ManageItemBean reqItem){
		PK_Resp_GetItemBarcodeBean getItemCode = new PK_Resp_GetItemBarcodeBean();
		
		int vExist=0;
		
		getItemCode = this.searchItemCode(reqItem.getBarCode());
		
		try{
			Statement st = ds.getStatement("SmartQ");

		    vQuery = "select ifnull(count(itemcode),0) as vCount from QItem where qId='"+ reqItem.getqId()+"' and itemcode = '"+ getItemCode.getCode() +"' and unitcode = '"+getItemCode.getUnitCode()+"' ";

			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				vExist = rs.getInt("vCount");
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return vExist;
	}
}
