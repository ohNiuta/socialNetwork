package com.niutex.socialnetwork.action;

import com.niutex.socialnetwork.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	
	private User user;
	
	@Override
	public void validate() {
		
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
