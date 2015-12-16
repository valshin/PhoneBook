package me.noip.valshin.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import me.noip.valshin.db.entities.User;

@Service
public class ActiveUserAccessorImpl implements ActiveUserAccessor{
    public User getActiveUserName(){
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}