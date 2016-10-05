package com.rawind.queqiao.web.controllers.admin;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rawind.queqiao.web.controllers.user.LoginController;
import com.rawind.queqiao.web.model.QueqiaoUser;
import com.rawind.queqiao.web.service.HostHolderService;
import com.rawind.queqiao.web.service.PassportService;
import com.rawind.queqiao.web.service.QueqiaoUserExtrService;
import com.rawind.queqiao.web.service.QueqiaoUserService;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;



@Path("/user")
public class UserMgrController {

	private static final Log logger = LogFactory.getLog(LoginController.class);
	
	@Autowired
	private QueqiaoUserService queqiaoUserService;
	
	@Autowired
	private PassportService passportService;
	
	@Autowired
	private QueqiaoUserExtrService queqiaoUserExtrService;
	
	@Autowired
	private HostHolderService hostHolderService;
	
	@Get("list")
	public String showUserList(Invocation inv) {
		
		
		
		inv.addModel("user", hostHolderService.getQueqiaoAdmin());
		
		
		int totalCount = queqiaoUserService.countByStatus(0);
		List<QueqiaoUser> userList = queqiaoUserService.findByStatus(0, 0, totalCount);
		
		inv.addModel("userList", userList);
		
		
		return "admin_user_list";
	}
	
	
	
	
	@Get("edit/{userId}")
	public String showUserEdit(Invocation inv, @Param("userId") long userId) {
		
		
		QueqiaoUser user = queqiaoUserService.getQueqiaoUserById(userId);
		if(user==null){
			return "@参数错误";
		}
		
		inv.addModel("userList", user);
		
		
		
		return "admin_user_edit";
	}
	
	
	

	@Post("editpost")
	public String postUserEdit(Invocation inv, @Param("userId") long userId, 
			@Param("proxyId") int proxyId, 
			@Param("proxyUrl") String proxyUrl,
			@Param("expireDate") Date expireDate) {
		
		
		
		
		
		
		
		return "admin_user_edit";
	}
	
	
	
	
	
	
	@Get("my_info")
	public String showMyInfo(Invocation inv) {
		
		
		inv.addModel("user", hostHolderService.getQueqiaoAdmin());
		
		
		
		return "admin_user_myinfo";
	}
	
}