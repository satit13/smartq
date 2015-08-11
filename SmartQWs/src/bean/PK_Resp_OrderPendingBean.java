package bean;

public class PK_Resp_OrderPendingBean {
	private Boolean success; 
	private String carNumber;
	private int numberOfItem;
	private String carBrand;
	private String timeCreate;
	
	public PK_Resp_OrderPendingBean() {

	}
	public PK_Resp_OrderPendingBean(Boolean success, String carNumber,
			int numberOfItem, String carBrand, String timeCreate) {

		this.success = success;
		this.carNumber = carNumber;
		this.numberOfItem = numberOfItem;
		this.carBrand = carBrand;
		this.timeCreate = timeCreate;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public int getNumberOfItem() {
		return numberOfItem;
	}
	public void setNumberOfItem(int numberOfItem) {
		this.numberOfItem = numberOfItem;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getTimeCreate() {
		return timeCreate;
	}
	public void setTimeCreate(String timeCreate) {
		this.timeCreate = timeCreate;
	}
	
	
}
