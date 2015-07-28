package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bean.LoginBean;
import bean.LoginResponseBean;
import bean.ResponseBean;
import bean.UserRegisterBean;
import bean.UserSearchBean;
import bean.UserSearchResponseBean;
import controller.UserController;

@Path(value="/user")
public class User {
	
	
	@POST
	@Path("/login")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public LoginResponseBean insertUser(LoginBean login ) {
		UserController msc = new  UserController();
		LoginResponseBean loginResponse = new LoginResponseBean();
		
//		loginResponse=msc.login("SmartConfig",login);
		
	
		return loginResponse;
	}
	
	@POST
	@Path("/search")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public UserSearchResponseBean userSearch(UserSearchBean userSearch ) {
		UserController msc = new  UserController();
		UserSearchResponseBean userSearchResponse = new UserSearchResponseBean();
		
		userSearchResponse=msc.userSearch("SmartConfig",userSearch);
		
	
		return userSearchResponse;
	}
	
	@POST
	@Path("/register")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseBean register(UserRegisterBean userRegister ) {
		UserController msc = new  UserController();
		ResponseBean response = new ResponseBean();
				
		response=msc.register("SmartConfig",userRegister);
		
	
		return response;
	}
	
	@PUT
	@Path("/edit")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseBean editUser(UserRegisterBean userRegister ) {
		UserController msc = new  UserController();
		ResponseBean response = new ResponseBean();
				
		response=msc.userEdit("SmartConfig",userRegister);
		
	
		return response;
	}
	
	

}
