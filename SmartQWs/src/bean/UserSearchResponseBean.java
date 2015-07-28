package bean;

import java.util.List;

import bean.UserBean;

public class UserSearchResponseBean {
	private ResponseBean response;
	private List<UserBean> user;
	
	public UserSearchResponseBean(){}

	public UserSearchResponseBean(ResponseBean response, List<UserBean> user) {
		this.response = response;
		this.user = user;
	}

	public ResponseBean getResponse() {
		return response;
	}

	public void setResponse(ResponseBean response) {
		this.response = response;
	}

	public List<UserBean> getUser() {
		return user;
	}

	public void setUser(List<UserBean> user) {
		this.user = user;
	}
	
	

	
	

}
