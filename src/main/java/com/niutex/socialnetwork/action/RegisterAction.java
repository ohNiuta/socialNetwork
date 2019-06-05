package com.niutex.socialnetwork.action;

import org.apache.commons.lang3.StringUtils;

import com.niutex.socialnetwork.dao.UserDAO;
import com.niutex.socialnetwork.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {
	
	private User user;
	
	@Override
	public void validate() {
		
		if(StringUtils.isEmpty(user.getUserName())) {
			addFieldError("user.userName", "Username Cannot Be Blank");
			return;
		}
		
		if (user.getUserName().length() > 120) {
			addFieldError("user.userName", "Username Too Long");
			return;
		}
				
		UserDAO dao = new UserDAO();
		
		if(!dao.findUserByName(user.getUserName()).isEmpty()) {
			addFieldError("user.userName", "User Already Exsists");
			return;
		}
		
		
		dao.close();
	}
	
	@Override
	public String execute() {
		UserDAO dao = new UserDAO();
		dao.insertUser(user);
		return SUCCESS;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}	
	
	
