package me.noip.valshin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import me.noip.valshin.entities.constants.Templates;

@Controller
@RequestMapping("/")
public class MainCtrl {
	@RequestMapping(method = RequestMethod.GET, value="/")
	public String welcome(){
		return Templates.INDEX;
	}
	@RequestMapping(Templates.LOGIN)
	public String login(){
		return Templates.LOGIN;
	}
	@RequestMapping(Templates.PHONEBOOK)
	public String phonebook(){
		return Templates.PHONEBOOK;
	}
	@RequestMapping(Templates.REGISTRATION)
	public String registration(){
		return Templates.REGISTRATION;
	}
}
