package com.rawind.queqiao.web.controllers;


import org.apache.log4j.Logger;

import com.chewen.tools.commons.util.AjaxOutput;
import com.rawind.queqiao.web.annotation.IgnoreLogin;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;




@IgnoreLogin()
@Path("")
public class IndexController {
	
	private static Logger logger = Logger.getLogger(IndexController.class);
	
	@Get("")
	public String showIndex(Invocation inv) {				
		return "index";
	}
	
	
	
	@Get("ads")
	public String showAds(Invocation inv, @Param("click") String click) {		
		
		logger.info("adUrl=" + click);
		
		return AjaxOutput.success("<a href='http://proxy.xiaochengzi.vip?inviteCode=80001213' target='_blank'>小橙子</a>");
	}
	
	
	
	@Get("user_guide")
	public String showUserGuide(Invocation inv, @Param("click") String click) {		
		
		logger.info("adUrl=" + click);
		
		return "user_guide";
	}
	
	
}
