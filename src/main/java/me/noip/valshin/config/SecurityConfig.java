package me.noip.valshin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import me.noip.valshin.authentication.CustomAuthenticationProvider;
import me.noip.valshin.entities.constants.Sources;
import me.noip.valshin.entities.constants.UserRoles;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	  web.ignoring().antMatchers("/components/**", "/app/**");
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers(Sources.ROOT).permitAll()
                .antMatchers(Sources.PHONEBOOK_PATH).access(getRoleDescription(UserRoles.USER))
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage(Sources.LOGIN_PATH)
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }
    
    private String getRoleDescription(String role){
    	return "hasRole('" + role + "')";
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
//            .inMemoryAuthentication()
//                .withUser("u").password("p").roles("USER");
    }
}