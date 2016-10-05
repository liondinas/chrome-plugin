package com.rawind.queqiao.web.service;



import com.rawind.queqiao.web.model.QueqiaoUserExtr;

public interface QueqiaoUserExtrService {

	
	
	long insert(QueqiaoUserExtr user);
		
	
	QueqiaoUserExtr getById(long id);
	
	
	QueqiaoUserExtr getByUserId(long userId);
	
	
	
	void updateStatus(long userId, int status);
	
	
	int queryCountByStatus(int status);
	
	
	QueqiaoUserExtr queryListByStatus(int status, int offset, int limit);
	
}
