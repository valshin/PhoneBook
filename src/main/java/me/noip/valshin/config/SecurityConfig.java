package me.noip.valshin.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import me.noip.valshin.security.CustomAuthenticationProvider;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)                                                        
    public static class RestWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

    	@Autowired
    	private CustomAuthenticationProvider customAuthenticationProvider;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        	auth.authenticationProvider(customAuthenticationProvider);
        }
        
        @Override
    	public void configure(WebSecurity web) throws Exception {
    		web.ignoring().antMatchers("/components/**", "/app/**");
    	}

        @Override
		protected void configure(HttpSecurity http) throws Exception {
			http.httpBasic().and().authorizeRequests()
					.antMatchers("/adduser", "/register.html", "/index.html", "/home.html", "/log_in.html", "/").permitAll().anyRequest()
					.authenticated().and().logout().logoutSuccessUrl("/").and().csrf().disable();
		}
    }
}