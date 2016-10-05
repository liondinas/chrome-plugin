package com.rawind.queqiao.web.service;

import com.rawind.queqiao.web.model.QueqiaoUser;

public interface HostHolderService {
	
	
	void setQueqiaoUser(QueqiaoUser user);
	
	
	QueqiaoUser getQueqiaoUser();
	
	
	boolean isUserLogin();
	
	
	void setQueqiaoAdmin(QueqiaoUser user);
	
	
	QueqiaoUser getQueqiaoAdmin();
	
	
	boolean isAdminLogin();
	
	
}
