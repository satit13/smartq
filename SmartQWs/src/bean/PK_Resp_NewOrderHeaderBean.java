package bean;

public class PK_Resp_NewOrderHeaderBean {
	private boolean success; 
	private int qId;
	private String errorMessage;
	
	
	public PK_Resp_NewOrderHeaderBean() {

	}
	
	public PK_Resp_NewOrderHeaderBean(boolean success, int qId,String errorMessage) {

		this.success = success;
		this.qId = qId;
		this.errorMessage = errorMessage;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getqId() {
		return qId;
	}
	public void setqId(int qId) {
		this.qId = qId;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
