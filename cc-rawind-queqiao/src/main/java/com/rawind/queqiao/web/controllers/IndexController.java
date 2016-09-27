package com.rawind.queqiao.web.controllers;

import com.rawind.queqiao.web.annotation.IgnoreLogin;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;



@IgnoreLogin()
@Path("")
public class IndexController {
	
	
	
	@Get("")
	public String showIndex(Invocation inv) {				
		return "index";
	}
	
	
}
