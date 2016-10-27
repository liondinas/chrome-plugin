package com.rawind.queqiao.web.controllers.user;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.chewen.tools.commons.util.CookieUtils;
import com.rawind.queqiao.web.Constants;
import com.rawind.queqiao.web.model.OrderTypeEnum;
import com.rawind.queqiao.web.model.QueqiaoOrder;
import com.rawind.queqiao.web.model.QueqiaoUser;
import com.rawind.queqiao.web.model.QueqiaoUserExtr;
import com.rawind.queqiao.web.service.HostHolderService;
import com.rawind.queqiao.web.service.QueqiaoOrderService;
import com.rawind.queqiao.web.service.QueqiaoUserExtrService;
import com.rawind.queqiao.web.service.QueqiaoUserService;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;




@Path("")
public class UserController {

	
	@Autowired
	private QueqiaoUserService queqiaoUserService;
	
	
	@Autowired
	private HostHolderService hostHolderService;
	
	@Autowired
	private QueqiaoOrderService queqiaoOrderService;
	
	@Autowired
	private QueqiaoUserExtrService queqiaoUserExtrService;
	
	@Get("")
	public String goUserIndex(Invocation inv){
		
		QueqiaoUser user = hostHolderService.getQueqiaoUser();
		
		
		inv.addModel("user", user);
		
		return "user_index";
	}
	
	
	
	@Get("node_list")
	public String goNodeList(Invocation inv){
		
		QueqiaoUser user = hostHolderService.getQueqiaoUser();
		
		
		inv.addModel("user", user);
		
		return "node_list";
	}
	
	
	@Get("my_info")
	public String goMyInfo(Invocation inv){
		
		QueqiaoUser user = hostHolderService.getQueqiaoUser();
		
		
		inv.addModel("user", user);
		
		
		QueqiaoUserExtr userExtr = queqiaoUserExtrService.getByUserId(user.getId());
		
		
		Date expiredDate = new Date();
		if(userExtr!=null){
			expiredDate = userExtr.getExpiredTime();
		}
		inv.addModel("expiredDate", expiredDate);
		
		
		inv.addModel("orderTypeList", OrderTypeEnum.values());
		
		return "my_info";
	}
	
	
	@Get("profile_update")
	public String goProfileUpdate(Invocation inv){
		
		QueqiaoUser user = hostHolderService.getQueqiaoUser();
		
		
		inv.addModel("user", user);
		
		return "profile_update";
	}
	
	
	
	@Get("invite_list")
	public String goInviteList(Invocation inv){
		
		QueqiaoUser user = hostHolderService.getQueqiaoUser();
		
		
		inv.addModel("user", user);
		
		return "invite_list";
	}
	
	
	@Get("logout")
	public String gologout(Invocation inv){
		
		
		CookieUtils.getInstance().deleteCookie(inv.getResponse(), Constants.userCookie, "/");
		
		
		return "r:/user/login";
	}
	
	
	
	@Get("order_list")
	public String goOrderList(Invocation inv, @Param("pageNo") int pageNo){
		
		QueqiaoUser user = hostHolderService.getQueqiaoUser();
		
		
		inv.addModel("user", user);
		
		
		int totalCount = queqiaoOrderService.countByUser(user.getId());
		
		List<QueqiaoOrder> orderList = queqiaoOrderService.listByUser(user.getId(), 0, totalCount);
		
		
		
		
		
		inv.addModel("orderList", orderList);
		
		return "order_list";
	}
	
}
