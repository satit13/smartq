package bean.request;

public class ReqItemSearchBean {
	private String accessToken;
	private String keyword;
	public ReqItemSearchBean(String accessToken, String keyword) {
		super();
		this.accessToken = accessToken;
		this.keyword = keyword;
	}
	
	public ReqItemSearchBean(){}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
