package com.rawind.queqiao.web.controllers.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chewen.tools.commons.util.AjaxOutput;
import com.rawind.queqiao.web.controllers.user.LoginController;
import com.rawind.queqiao.web.model.QueqiaoProxy;
import com.rawind.queqiao.web.model.QueqiaoUser;
import com.rawind.queqiao.web.model.QueqiaoUserExtr;
import com.rawind.queqiao.web.service.HostHolderService;
import com.rawind.queqiao.web.service.PassportService;
import com.rawind.queqiao.web.service.QueqiaoProxyService;
import com.rawind.queqiao.web.service.QueqiaoUserExtrService;
import com.rawind.queqiao.web.service.QueqiaoUserService;
import com.rawind.queqiao.web.util.CollectionUtil;

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
	
	@Autowired
	private QueqiaoProxyService queqiaoProxyService;
	
	@Get("list")
	public String showUserList(Invocation inv) {
		
		
		
		inv.addModel("user", hostHolderService.getQueqiaoAdmin());
		
		
		int totalCount = queqiaoUserService.countByStatus(0);
		List<QueqiaoUser> userList = queqiaoUserService.findByStatus(0, 0, totalCount);
		
		inv.addModel("userList", userList);
		
		
		
		List<Long> userIds = new ArrayList<Long>();
		if(CollectionUtil.isNotEmpty(userList)){
			for(QueqiaoUser user : userList){
				userIds.add(user.getId());
			}
		}
		
		Map<Long, QueqiaoUserExtr> userExtrMap = queqiaoUserExtrService.getMapByUserIds(userIds);
		inv.addModel("userExtrMap", userExtrMap);
		
		
		
		return "admin_user_list";
	}
	
	
	
	
	@Get("edit/{userId}")
	public String showUserEdit(Invocation inv, @Param("userId") long userId) {
		
		
		QueqiaoUser user = queqiaoUserService.getQueqiaoUserById(userId);
		if(user==null){
			return "@参数错误";
		}
		logger.info("userId=" + userId);
		inv.addModel("user", user);
		
		int totalCount = queqiaoProxyService.countByType(0);
		List<QueqiaoProxy> proxyList = queqiaoProxyService.getByType(0, 0, totalCount);
		
		inv.addModel("proxyList", proxyList);
		
		return "admin_user_edit";
	}
	
	
	

	@Post("editpost")
	public String postUserEdit(Invocation inv, @Param("userId") long userId, 
			@Param("proxyId") long proxyId, 
			@Param("proxyUrl") String proxyUrl,
			@Param("expireDate") Date expireDate) {
		
		
		QueqiaoUser user = queqiaoUserService.getQueqiaoUserById(userId);
		if(user==null){
			return AjaxOutput.failure("用户不存在");
		}
		
		
		QueqiaoProxy proxy = queqiaoProxyService.getById(proxyId);
		if(proxy==null){
			return AjaxOutput.failure("代理节点不存在");
		}
		
		
		user.setProxyId(proxy.getId());
		user.setProxyStr(proxy.getUrl());
		
		queqiaoUserService.update(user);
				
		return AjaxOutput.success("ok");
	}
	
	
	
	
	
	
	@Get("my_info")
	public String showMyInfo(Invocation inv) {
		
		
		inv.addModel("user", hostHolderService.getQueqiaoAdmin());
		
		
		
		return "admin_user_myinfo";
	}
	
	
	
	@Get("profile_update")
	public String updateProfile(Invocation inv) {
		
		
		inv.addModel("user", hostHolderService.getQueqiaoAdmin());
		
		
		
		return "admin_user_myinfo";
	}
	
	
}
