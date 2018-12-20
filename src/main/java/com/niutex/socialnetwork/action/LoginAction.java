package com.niutex.socialnetwork.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.niutex.socialnetwork.dao.UserDAO;
import com.niutex.socialnetwork.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	
	private User user;
	
	@Override
	public void validate() {
		
		if(StringUtils.isEmpty(user.getUserName())) {
			addFieldError("user.userName", "User Name Cannot Be Blank");
			return;
		}
		
		UserDAO dao = new UserDAO();
		List<User> users = dao.findUserByName(user.getUserName());
		
		if(users.isEmpty()) {
			addFieldError("user.userName", "No User Found");
			return;
		}
		
		if(!users.get(0).
				getPassword().
				equals(user.getPassword())) {
			addFieldError("user.password", "Incorrect Password");
		}
		
	}
	
	public String execute() {
		System.out.println(user.getUserName());
		System.out.println(user.getPassword());
	
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
