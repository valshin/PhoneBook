package me.noip.valshin.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import me.noip.valshin.db.entities.User;

@Component
public class ActiveUserAccessorImpl implements ActiveUserAccessor{
    public User getActiveUser(){
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}