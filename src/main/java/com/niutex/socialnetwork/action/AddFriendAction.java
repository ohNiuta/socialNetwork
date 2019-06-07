package com.niutex.socialnetwork.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.niutex.socialnetwork.dao.UserDAO;
import com.niutex.socialnetwork.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class AddFriendAction extends ActionSupport {
	
	private String name;
	private Map<String, Object> userSession;

	
	
	@Override
	public void validate() {
		
		if (StringUtils.isEmpty(name)) {
			addFieldError("name", "Name Cannot Be Empty");
			return;
		}
		
		UserDAO dao = new UserDAO();
		List<User> users = dao.findUserByName(name);
		User currentUser = (User) userSession.get("currentUser");
		
		if (users.isEmpty()) {
			addFieldError("name", "User Does Not Exist");			
		}
		
		if (currentUser.getUserName().equals(users.get(0).getUserName())) {
			addFieldError("name", "You Cannot Add Yourself");
		}
		
		dao.close();
	}

	@Override
	public String execute() throws Exception {
		UserDAO dao = new UserDAO();
		return SUCCESS;
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Map<String, Object> getUserSession() {
		return userSession;
	}



	public void setUserSession(Map<String, Object> userSession) {
		this.userSession = userSession;
	}
	
	

}
