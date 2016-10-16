package com.rawind.queqiao.web.service;



import java.util.Date;

import com.rawind.queqiao.web.model.QueqiaoUserExtr;

public interface QueqiaoUserExtrService {

	
	
	long insert(QueqiaoUserExtr user);
		
	
	QueqiaoUserExtr getById(long id);
	
	
	QueqiaoUserExtr getByUserId(long userId);
	
		
	void updateExpiredTime(long userId, int addDay);
	
	
	/**
	 *  status 0-不区分， 1-正常， 2-过期
	 * @param status
	 * @return
	 */
	int queryCountByStatus(int status);
	
	/**
	 *  status 0-不区分， 1-正常， 2-过期
	 * @param status
	 * @param offset
	 * @param limit
	 * @return
	 */
	QueqiaoUserExtr queryListByStatus(int status, int offset, int limit);
	
}
