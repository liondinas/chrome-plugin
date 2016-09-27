package com.rawind.queqiao.web.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.rawind.queqiao.web.model.QueqiaoUser;
import com.rawind.queqiao.web.service.HostHolderService;
import com.rawind.queqiao.web.service.QueqiaoUserService;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;



@Path("")
public class UserPayController {
	
	
	
	@Autowired
	private QueqiaoUserService queqiaoUserService;
	
	
	@Autowired
	private HostHolderService hostHolderService;
	
	
	@Get("user_pay")
	public String goPay(Invocation inv){
		
		QueqiaoUser user = hostHolderService.getQueqiaoUser();
		
		
		inv.addModel("user", user);
		
		return "user_pay";
	}
	
	
}
