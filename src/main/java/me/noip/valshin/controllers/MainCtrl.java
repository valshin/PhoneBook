package me.noip.valshin.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import me.noip.valshin.entities.constants.Sources;

@Controller
@RequestMapping("/")
public class MainCtrl {
//	@RequestMapping(method = RequestMethod.GET, value="/", produces = "text/html")
//	public String welcome(){
//		return Templates.INDEX;
//	}
	@RequestMapping(Sources.LOGIN)
	public String login(){
		return Sources.LOGIN;
	}
	
//	@RequestMapping("/register")
//	public String logout(){
//		return "register";
//	}
	
	@RequestMapping(Sources.PHONEBOOK)
	public String phonebook(){
		return Sources.PHONEBOOK;
	}
	@RequestMapping(Sources.REGISTER)
	public String registration(){
		return Sources.REGISTER;
	}
	
	@RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String home(Principal principal) {
        if (principal == null) return "0";
        // logic
        return principal.toString();
    }
}
