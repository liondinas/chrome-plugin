package com.rawind.queqiao.web.service;

import java.util.List;
import java.util.Map;

import com.rawind.queqiao.web.model.QueqiaoUserExtr;

public interface QueqiaoUserExtrService {

	
	
	long insert(QueqiaoUserExtr user);
		
	
	QueqiaoUserExtr getById(long id);
	
	
	QueqiaoUserExtr getByUserId(long userId);
	
	
	List<QueqiaoUserExtr> getByUserIds(List<Long> userIds);
	
	
	Map<Long, QueqiaoUserExtr> getMapByUserIds(List<Long> userIds);
	
		
	void updateExpiredTime(long userId, int addDay);
	
	
	void updateUserInfo(long userId, String userName, String userPwd);
	
	
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
