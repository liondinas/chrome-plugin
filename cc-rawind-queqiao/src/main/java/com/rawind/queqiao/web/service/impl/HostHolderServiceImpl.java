package com.rawind.queqiao.web.service.impl;

import net.paoding.rose.web.Invocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rawind.queqiao.web.model.QueqiaoUser;
import com.rawind.queqiao.web.service.HostHolderService;



@Component
public class HostHolderServiceImpl implements HostHolderService{
	
	private final String USER_KEY = "_queqiao_user";
	
	
	private final String ADMIN_KEY = "_queqiao_admin";
	
	@Autowired
	private Invocation inv;

	@Override
	public void setQueqiaoUser(QueqiaoUser user) {
		inv.addModel(USER_KEY, user);
	}

	@Override
	public QueqiaoUser getQueqiaoUser() {
		return (QueqiaoUser)inv.getModel(USER_KEY);
	}

	@Override
	public boolean isUserLogin() {
		return getQueqiaoUser()!=null;
	}
	
	

		
	

}
