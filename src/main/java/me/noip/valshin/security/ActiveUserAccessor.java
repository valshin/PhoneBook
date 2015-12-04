package me.noip.valshin.security;

import me.noip.valshin.db.entities.User;

public interface ActiveUserAccessor {
	public User getActiveUser();
}
