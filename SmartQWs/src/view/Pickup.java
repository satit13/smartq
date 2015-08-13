package view;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import controller.PickupController;

import bean.PK_Reqs_ManageItemBean;
import bean.PK_Resp_DeleteOrderBean;
import bean.PK_Reqs_EditOrderBean;
import bean.CT_Reqs_NewDocNoBean;
import bean.PK_Resp_ManageItemBean;
import bean.PK_Resp_NewOrderHeaderBean;
import bean.PK_Resp_OrderPendingBean;
import bean.SearchBean;

@Path(value="/pickup")
public class Pickup {

	@POST
	@Path("/search")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<PK_Resp_OrderPendingBean> searchOrderPending(SearchBean keyword){
		List<PK_Resp_OrderPendingBean> orderList = new ArrayList<PK_Resp_OrderPendingBean>();
		PickupController pcl = new PickupController();
		
		orderList = pcl.searchOrderPending("SmartQ", keyword);
		
		return orderList;
	}
	
	@POST
	@Path("/new")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public PK_Resp_NewOrderHeaderBean newOrder(CT_Reqs_NewDocNoBean reqNew){
		PK_Resp_NewOrderHeaderBean newOrder = new PK_Resp_NewOrderHeaderBean();
		PickupController pcl = new PickupController();
		
		newOrder = pcl.searchNewOrder("SmartQ", reqNew);
		
		return newOrder;
	}
	
	@POST
	@Path("/edit")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public boolean editOrder(PK_Reqs_EditOrderBean reqEdit){
		boolean success;
		PickupController pcl = new PickupController();
		
		success = pcl.editOrder("SmartQ", reqEdit);
		
		return success;
	}
	
	
	@POST
	@Path("/delete")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public boolean deleteOrder(PK_Resp_DeleteOrderBean reqDelete){
		boolean success;
		PickupController pcl = new PickupController();
		
		success = pcl.deleteOrder("SmartQ", reqDelete);
		
		return success;
	}
	
	@POST
	@Path("/manage_product")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public PK_Resp_ManageItemBean manageItemOrder(PK_Reqs_ManageItemBean reqManageItem){
		PK_Resp_ManageItemBean resManageItem = new PK_Resp_ManageItemBean();
		PickupController pcl = new PickupController();
		
		resManageItem = pcl.insertItemPickup("SmartQ", reqManageItem);
		
		return resManageItem;
	}
}
