package com.niutex.socialnetwork.action;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.niutex.socialnetwork.dao.UserDAO;
import com.niutex.socialnetwork.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class AddFriendAction extends ActionSupport implements SessionAware {
	
	private String name;
	private Map<String, Object> userSession;
	
	@Override
	public void validate() {
		
		if (StringUtils.isEmpty(name)) {
			addFieldError("name", "Name Cannot Be Empty");
			return;
		}
		
		UserDAO dao = new UserDAO();
		List<User> maybeFriend = dao.findUserByName(name);
		User currentUser = (User) userSession.get("user");
		
		if (maybeFriend.isEmpty()) {
			addFieldError("name", "User Does Not Exist");			
		}
		
		if (currentUser.getUserName().equals(maybeFriend.get(0).getUserName())) {
			addFieldError("name", "You Cannot Add Yourself");
		}
		
		for  (User userToAdd : currentUser.getFriends()) {
			if (userToAdd.getUserName().equals(maybeFriend.get(0).getUserName())) {
				addFieldError("name", "Already your friend");
			}
		}
		
		dao.close();
	}

	@Override
	public String execute() throws Exception {
		UserDAO dao = new UserDAO();
		
		List<User> usersToAdd = dao.findUserByName(name);
		User currentUser = (User) userSession.get("user");
		Set<User> alreadyFriends = currentUser.getFriends();
		alreadyFriends.add(usersToAdd.get(0));
		currentUser.setFriends(alreadyFriends);
		
		dao.update(currentUser);
		dao.close();
				
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

	@Override
	public void setSession(Map<String, Object> session) {
		this.userSession = session;
	}
}
