package me.noip.valshin.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import me.noip.valshin.db.Db;
import me.noip.valshin.db.entities.User;
import me.noip.valshin.entities.constants.UserRoles;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	Db db;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
        String password = authentication.getCredentials().toString();
		User user = db.getUser(name, password);
		if (user != null){
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
			grantedAuths.add(new SimpleGrantedAuthority(UserRoles.USER));
	        return new UsernamePasswordAuthenticationToken(user, password, grantedAuths);
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
	
	
}
