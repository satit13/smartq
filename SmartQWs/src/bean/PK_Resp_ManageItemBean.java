package bean;

public class PK_Resp_ManageItemBean {
	private boolean success;
	private String error_message;
	
	
	public PK_Resp_ManageItemBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PK_Resp_ManageItemBean(boolean success, String error_message) {

		this.success = success;
		this.error_message = error_message;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getError_message() {
		return error_message;
	}


	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	
	
}
