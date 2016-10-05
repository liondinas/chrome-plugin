package com.rawind.queqiao.web.controllers.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rawind.queqiao.web.controllers.user.LoginController;
import com.rawind.queqiao.web.service.PassportService;
import com.rawind.queqiao.web.service.QueqiaoUserExtrService;
import com.rawind.queqiao.web.service.QueqiaoUserService;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;



@Path("/user")
public class UserMgrController {

	private static final Log logger = LogFactory.getLog(LoginController.class);
	
	@Autowired
	private QueqiaoUserService queqiaoUserService;
	
	@Autowired
	private PassportService passportService;
	
	@Autowired
	private QueqiaoUserExtrService queqiaoUserExtrService;
	
	@Get("list")
	public String showUserList(Invocation inv) {
		
		return "admin_user_list";
	}
	
	
}
