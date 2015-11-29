package me.noip.valshin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	@RequestMapping(Sources.PHONEBOOK)
	public String phonebook(){
		return Sources.PHONEBOOK;
	}
	@RequestMapping(Sources.REGISTRATION)
	public String registration(){
		return Sources.REGISTRATION;
	}
}
