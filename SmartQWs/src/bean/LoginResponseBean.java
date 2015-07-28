package bean;

import java.util.Date;

public class LoginResponseBean {
	
	private ResponseBean response;
	private String accessToken;
	private String accessDatetime;
	private UserBean user;
	
	public LoginResponseBean(){}

	public LoginResponseBean(ResponseBean response, String accessToken, String accessDatetime, UserBean user) {
		this.response = response;
		this.accessToken = accessToken;
		this.accessDatetime = accessDatetime;
		this.user = user;
	}

	public ResponseBean getResponse() {
		return response;
	}

	public void setResponse(ResponseBean response) {
		this.response = response;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessDatetime() {
		return accessDatetime;
	}

	public void setAccessDatetime(String accessDatetime) {
		this.accessDatetime = accessDatetime;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}
	
	

}
