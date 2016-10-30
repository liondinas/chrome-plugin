package com.rawind.queqiao.web.controllers.user;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.chewen.tools.commons.util.AjaxOutput;
import com.chewen.tools.commons.util.BCrypt;
import com.chewen.tools.commons.util.CookieUtils;
import com.chewen.tools.commons.util.CwDateTimeUtil;
import com.rawind.queqiao.web.Constants;
import com.rawind.queqiao.web.model.OrderTypeEnum;
import com.rawind.queqiao.web.model.QueqiaoOrder;
import com.rawind.queqiao.web.model.QueqiaoUser;
import com.rawind.queqiao.web.model.QueqiaoUserExtr;
import com.rawind.queqiao.web.service.HostHolderService;
import com.rawind.queqiao.web.service.QueqiaoOrderService;
import com.rawind.queqiao.web.service.QueqiaoUserExtrService;
import com.rawind.queqiao.web.service.QueqiaoUserService;
import com.rawind.queqiao.web.util.ProxyUrlEncrypt;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;




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
		
		
		inv.addModel("broadcast", "测试中，测试期间随时删号，不保证可用。<br />所有节点均不支持外发邮件。");
		
		QueqiaoUserExtr userExtr = queqiaoUserExtrService.getByUserId(user.getId());
		
		
		Date expiredDate = new Date();		
		if(userExtr!=null){
			expiredDate = userExtr.getExpiredTime();
			expiredDate = CwDateTimeUtil.cleanHourAndMinutes(expiredDate);
		}
		inv.addModel("expiredDate", expiredDate);
		
		
		
		Date cur = new Date();
		int step = CwDateTimeUtil.daysOfTwo(cur, expiredDate);
		if(step <=0){
			step = 0;
		}
		inv.addModel("cur", cur);
		
		inv.addModel("step", step);
		inv.addModel("orderTypeList", OrderTypeEnum.values());
		
		String proxy = user.getProxyStr();
		inv.addModel("proxy", ProxyUrlEncrypt.encode(proxy));
		
		return "user_index";
	}
	
	
	
	@Get("order_create")
	public String goOrderCreate(Invocation inv){
		
		QueqiaoUser user = hostHolderService.getQueqiaoUser();
		
		
		inv.addModel("user", user);
		
		
		inv.addModel("orderTypeList", OrderTypeEnum.values());
		
		return "order_create";
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
		expiredDate = CwDateTimeUtil.cleanHourAndMinutes(expiredDate);
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
		
		
		QueqiaoUserExtr userExtr = queqiaoUserExtrService.getByUserId(user.getId());
		inv.addModel("userExtr", userExtr);
		
		
		
		return "profile_update";
	}
	
	
	
	@Get("user_contact")
	public String goUserContact(Invocation inv){
		
		QueqiaoUser user = hostHolderService.getQueqiaoUser();
		
		
		inv.addModel("user", user);
		
		return "user_contact";
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
	
	
	@Post("update_userextr")
	public String updateProxyPwd(Invocation inv, @Param("userId") long userId, 
			@Param("userName") String userName, @Param("userPwd") String userPwd){
		
		if(userId<=0){
			return AjaxOutput.failure("参数错误");
		}
		
		if(StringUtils.isBlank(userName)){
			return AjaxOutput.failure("请填写用户名");
		}
		
		
		if(StringUtils.isBlank(userPwd)){
			return AjaxOutput.failure("请填写翻墙密码");
		}
		
		if(userPwd.length()<8){
			return AjaxOutput.failure("翻墙密码最少8位");
		}
		
		
		QueqiaoUser user = hostHolderService.getQueqiaoUser();
		if(user.getId() != userId){
			return AjaxOutput.failure("没有权限修改");
		}
		
		
		
		QueqiaoUserExtr userExtr = queqiaoUserExtrService.getByUserId(user.getId());
		if(userExtr==null){
			userExtr = new QueqiaoUserExtr();
			Date expiredTime = CwDateTimeUtil.cleanMinutes(new Date());
			expiredTime = CwDateTimeUtil.lastHourAndMinutes(expiredTime);
			userExtr.setExpiredTime(expiredTime);
			userExtr.setPasswd(userPwd);
			userExtr.setUserId(userId);
			userExtr.setUserName(userName);
			userExtr.setStatus(QueqiaoUserExtr.STATUS_NORMAL);			
			queqiaoUserExtrService.insert(userExtr);
		}else{
			queqiaoUserExtrService.updateUserInfo(userId, userName, userPwd);			
		}
		
		return AjaxOutput.success("ok");
		
	}
	
	
	
	@Post("update_pwd")
	public String updateUserPwd(Invocation inv, @Param("userId") long userId, 
			@Param("nowpassword") String nowpassword, @Param("password") String password, 
			@Param("repassword") String repassword, @Param("email") String email){
		
		
		if(StringUtils.isNotBlank(email)){
			return AjaxOutput.failure("暂不支持修改油箱，如需修改，请联系客服。");
		}
		
		if(userId<=0){
			return AjaxOutput.failure("参数错误");
		}
		
		if(StringUtils.isBlank(nowpassword)){
			return AjaxOutput.failure("请填当前密码");
		}
		
		
		if(StringUtils.isBlank(password)){
			return AjaxOutput.failure("请填写新密码");
		}
		
		
		if(StringUtils.isBlank(repassword)){
			return AjaxOutput.failure("请填写确认密码");
		}
		
		if(password.length()<8){
			return AjaxOutput.failure("翻墙密码最少8位");
		}
		
		if(!password.endsWith(repassword)){
			return AjaxOutput.failure("两次密码不一致");
		}
		
		
		
		QueqiaoUser user = hostHolderService.getQueqiaoUser();
		if(user.getId() != userId){
			return AjaxOutput.failure("没有权限修改");
		}
		
		
		if (BCrypt.checkpw(nowpassword, user.getPassWord())){
			return AjaxOutput.failure("原密码不正确");
		}
		
		
		
		String passwordBcr = BCrypt.hashpw(repassword, BCrypt.gensalt(Constants.LOG_ROUNDS));
		user.setPassWord(passwordBcr);
		
		queqiaoUserService.update(user);
		
		return AjaxOutput.success("ok");
		
	}
	
	
}
