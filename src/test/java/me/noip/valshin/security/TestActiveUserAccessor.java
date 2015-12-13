package me.noip.valshin.security;

import org.springframework.stereotype.Service;

import me.noip.valshin.db.entities.User;
import me.noip.valshin.security.ActiveUserAccessor;

@Service
public class TestActiveUserAccessor implements ActiveUserAccessor{
	User activeUser;
	
	public void setActiveUser(User user){
		activeUser = user;
    }
	public User getActiveUser(){
        return activeUser;
    }
}